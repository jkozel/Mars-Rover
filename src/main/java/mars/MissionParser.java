package mars;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Parse configuration input and format mission output.
 *
 * @author jkozel
 */
public class MissionParser {
	/**
	 * Render the RoverPosition in hte required output format.
	 * @param posn
	 * @return
	 */
	public String formatRoverPosition(RoverPosition posn) {
		if (posn == null || posn.getDir() == null || posn.getLocn() == null) {
			return "[Invalid Position]";
		} else {
			String dir;
			if (posn.getDir() == Orientation.NORTH) {
				dir = "N";
			} else if (posn.getDir() == Orientation.EAST) {
				dir = "E";
			} else if (posn.getDir() == Orientation.SOUTH) {
				dir = "S";
			} else if (posn.getDir() == Orientation.WEST) {
				dir = "W";
			} else {
				dir = "[Invalid Orientation]";
			}
			return posn.getLocn().x + " " + posn.getLocn().y + " " + dir;
		}
	}

	/**
	 * Parse the given String of characters into a set of rover movement commands.
	 * @param str
	 * @return
	 * @throws IllegalArgumentException if any of the command characters is not supported.
	 */
	public List<RoverMovement> parseCommands(String str) {
		List<RoverMovement> commands = new ArrayList<RoverMovement>();
		if (str == null) {
			return commands;
		}

		char[] chars = str.toUpperCase().toCharArray();
		for (char c : chars) {
			if (c == 'L') {
				commands.add(RoverMovement.LEFT);
			} else if (c == 'R') {
				commands.add(RoverMovement.RIGHT);
			} else if (c == 'M') {
				commands.add(RoverMovement.FORWARD);
			} else {
				throw new IllegalArgumentException("The command sequence contains at least one unsupported character: " + str);
			}
		}
		return commands;
	}

	/**
	 * Parse the given String into a cardinal orientation.
	 * @param str
	 * @return
	 * @throws IllegalArgumentException if not a valid direction character
	 */
	public Orientation parseOrientation(String str) {
		if (str.equalsIgnoreCase("N")) {
			return Orientation.NORTH;
		} else if (str.equalsIgnoreCase("E")) {
			return Orientation.EAST;
		} else if (str.equalsIgnoreCase("S")) {
			return Orientation.SOUTH;
		} else if (str.equalsIgnoreCase("W")) {
			return Orientation.WEST;
		} else {
			throw new IllegalArgumentException("The value \"" + str + "\" is not a valid direction.");
		}
	}

	/**
	 * Parse the given String to give a Point.
	 * @param str Two integers separated by whitespace.
	 */
	public Point parsePoint(String str) {
		String[] split = str.split("\\s");
		if (split.length < 2) {
			throw new IllegalArgumentException("The point input \"" + str + "\" must contain at least 2 values.");
		}
		try {
			int x = Integer.parseInt(split[0]);
			int y = Integer.parseInt(split[1]);
			return new Point(x, y);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("The point input \"" + str + "\" cannot be parsed as integer coordinates: " + e, e);
		}
	}

	/**
	 * Parse the Rover position (location and orientation) from the given text.
	 * @param str Contains the elements: x (int), y (int), direction (N | S | E | W). Elements are whitespace-delimited.
	 * @return
	 * @throws IllegalArgumentException when any of the values cannot be parsed, or the wrong number of values is detected
	 */
	public RoverPosition parseRoverPosition(String str) {
		String[] split = str.split("\\s");
		if (split.length < 3) {
			throw new IllegalArgumentException("The position input \"" + str + "\" must contain at least 3 values.");
		}
		try {
			int x = Integer.parseInt(split[0]);
			int y = Integer.parseInt(split[1]);
			Orientation dir = parseOrientation(split[2]);
			return new RoverPosition(x, y, dir);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("The position input \"" + e + "\" contains at least one value that cannot be parsed: " + e, e);
		}
	}
}
