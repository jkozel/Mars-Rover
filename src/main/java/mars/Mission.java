package mars;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Runs the Mars Rover Mission using the configuration as described in the exercise.
 *
 * @author jkozel
 */
public class Mission {
	private static final String CRLF = System.getProperty("line.separator");
	private static MissionParser parser = new MissionParser();

	/**
	 * Create a Mars Rover mission with the given configuration entries
	 * @param entries
	 * @return
	 */
	public static Mission create(List<String> entries) {
		if (entries == null || entries.size() < 3) {
			throw new IllegalArgumentException("There must be at least 3 lines in the mission configuration.");
		}

		Point upperRight = parser.parsePoint(entries.get(0));
		if (upperRight.x < 0 || upperRight.y < 0) {
			throw new IllegalArgumentException("Upper right bounds of grid must be positive: " + upperRight);
		}

		Mission mission = new Mission(upperRight);
		for (int i = 2; i < entries.size(); i += 2) {
			RoverPosition posn = parser.parseRoverPosition(entries.get(i - 1));
			try {
				List<RoverMovement> commands = parser.parseCommands(entries.get(i));
				mission.addRover(posn, commands);
			} catch (IllegalArgumentException e) {
				System.out.println("Skipping rover with start position (posn) due to invalid command sequence: " + e);
			}
		}
		return mission;
	}

	/**
	 * Read the mission configuration from a local file, and run the complete mission.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			showUsage();
			return;
		}

		FileReader fr;
		try {
			fr = new FileReader(args[0]);
		} catch (FileNotFoundException e) {
			System.out.println("Could not open input file " + args[0] + ": " + e);
			return;
		}

		BufferedReader br = new BufferedReader(fr);
		String line = null;
		List<String> lines = new ArrayList<String>();
		while ((line = br.readLine()) != null) {
			lines.add(line);
		}
		br.close();

		Mission mission = null;
		try {
			mission = Mission.create(lines);
		} catch (IllegalArgumentException e) {
			System.out.println("Error creating the Mars Rover mission from the configuration. Aborting: " + e);
		}
		if (mission != null) {
			mission.run();
		}
	}

	private static void showUsage() {
		System.out.println("Runs the Mars Rover Mission" + CRLF);
		System.out.println("Arguments:" + CRLF);
		System.out.println("1. Relative path to text file containing the mission setup, as described in the exercise.");
	}

	private final Point upperRight;
	private final List<Rover> rovers = new ArrayList<Rover>();

	public Mission(Point upperRight) {
		this.upperRight = upperRight;
	}

	public void addRover(RoverPosition posn, List<RoverMovement> commands) {
		this.getRovers().add(new Rover(posn, commands));
	}

	public List<Rover> getRovers() {
		return rovers;
	}

	/**
	 * Execute the mission based on the given collection of configuration entries.
	 * The configuration format is described in the exercise.
	 * @param lines
	 */
	public void run() {
		for (Rover rover : getRovers()) {
			if (rover != null) {
				try {
					RoverPosition finalPosn = runRover(rover);
					if (finalPosn != null) {
						System.out.println(parser.formatRoverPosition(finalPosn));
					} else {
						System.out.println("Could not calculate final position.");
					}
				} catch (IndexOutOfBoundsException e) {
					System.out.println("Rover has travelled out of bounds:\n" + e);
				}
			}
		}
	}

	/**
	 * Run the commands for the given Rover.
	 * @return
	 */
	public RoverPosition runRover(Rover rover) {
		if (rover == null || rover.getStartPosn() == null) {
			return null;
		}
		if (rover.getCommands() == null) {
			rover.setFinalPosn(rover.getStartPosn());
			return rover.getStartPosn();
		}

		RoverPosition thisPosn = rover.getStartPosn();
		for (RoverMovement cmd : rover.getCommands()) {
			thisPosn = cmd.exec(thisPosn);
			if (thisPosn.getLocn().x < 0 || thisPosn.getLocn().x > upperRight.x || thisPosn.getLocn().y < 0 || thisPosn.getLocn().y > upperRight.y) {
				throw new IndexOutOfBoundsException("The rover position (" + thisPosn + ") has moved outside the grid (0,0) to (" + upperRight.x + "," + upperRight.y + ")");
			}
		}
		rover.setFinalPosn(thisPosn);
		return thisPosn;
	}
}
