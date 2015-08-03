package mars;

public enum RoverMovement {
	LEFT(0, -1), RIGHT(0, 1), FORWARD(1, 0);

	private final int deltaForward;
	private final int deltaRotate;

	private RoverMovement(int deltaForward, int deltaRotate) {
		this.deltaForward = deltaForward;
		this.deltaRotate = deltaRotate;
	}

	public int getDeltaForward() {
		return deltaForward;
	}

	public int getDeltaRotate() {
		return deltaRotate;
	}

	public RoverPosition exec(RoverPosition startPosn) {
		if (startPosn != null) {
			return startPosn.move(getDeltaForward()).rotate(getDeltaRotate());
		} else {
			return null;
		}
	}
}
