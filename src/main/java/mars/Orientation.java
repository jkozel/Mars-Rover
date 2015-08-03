package mars;

/**
 * Represents the cardinal orientations (N, E, S, W)
 * @author jkozel
 */
public enum Orientation {
	NORTH(0),
	EAST(1),
	SOUTH(2),
	WEST(3);
	
	private int posn;

	private Orientation(int newPosn) {
		this.posn = newPosn;
	}
	
	private int getPosn() {
		return posn;
	}

	/**
	 * Translate the orientation (i.e. rotate) by the given number of increments, 
	 * where each increment is 90 degrees.
	 * @param delta Number of increments to rotate. Positive is clockwise, negative is counter-clockwise.
	 * @return The new orientation.
	 */
	public Orientation translate(int delta) {
		int newPosn = (getPosn() + delta) % 4;
		if  (newPosn < 0) {
			newPosn += 4;
		}
		return Orientation.values()[newPosn];
	}
}
