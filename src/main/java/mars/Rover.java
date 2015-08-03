package mars;

import java.util.List;

/**
 * Represents a Rover and its movements.
 * Includes the starting position (location and orientation), and a series of movement commands.
 * @author jkozel
 *
 */
public class Rover {
	private final RoverPosition startPosn;
	private final List<RoverMovement> commands;
	private RoverPosition finalPosn;

	public Rover(RoverPosition startPosn, List<RoverMovement> commands) {
		this.startPosn = startPosn;
		this.commands = commands;
	}

	public List<RoverMovement> getCommands() {
		return commands;
	}

	public RoverPosition getStartPosn() {
		return startPosn;
	}

	public RoverPosition getFinalPosn() {
		return finalPosn;
	}

	protected void setFinalPosn(RoverPosition finalPosn) {
		this.finalPosn = finalPosn;
	}
}
