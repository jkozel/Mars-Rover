package mars;

import java.awt.Point;

/**
 * Represents a position (location and orientation) of the Rover where the location is an <code>(X, Y)</code> point, 
 * and the orientation is one of the {@link Orientation} directions.
 * 
 * @author jkozel
 */
public class RoverPosition {
	private final Point locn;
	private final Orientation dir;
	
	public RoverPosition(int x, int y, Orientation dir) {
		this.locn = new Point(x, y);
		this.dir = dir;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoverPosition other = (RoverPosition) obj;
		if (dir != other.dir)
			return false;
		if (locn == null) {
			if (other.locn != null)
				return false;
		} else if (!locn.equals(other.locn))
			return false;
		return true;
	}

	public Orientation getDir() {
		return dir;
	}

	public Point getLocn() {
		return locn.getLocation();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dir == null) ? 0 : dir.hashCode());
		result = prime * result + ((locn == null) ? 0 : locn.hashCode());
		return result;
	}

	/**
	 * Return a new RoverPosition which has been moved by the given number of units in the direction which it is facing.
	 * Positive means move forward, negative means move backward.
	 * @param n
	 */
	public RoverPosition move(int n) {
		int deltaX = 0;
		int deltaY = 0;
		if (this.dir == Orientation.NORTH) {
			deltaY = n;
		} else if (this.dir == Orientation.EAST) {
			deltaX = n;
		} else if (this.dir == Orientation.SOUTH) {
			deltaY = -n;
		} else if (this.dir == Orientation.WEST) {
			deltaX = -n;
		}
		return new RoverPosition(this.locn.x + deltaX, this.locn.y + deltaY, this.dir);
	}

	/**
	 * Return a new RoverPosition which has been rotated by the given number of units, where positive is clockwise.
	 * @param n
	 */
	public RoverPosition rotate(int n) {
		return new RoverPosition(this.locn.x, this.locn.y, this.dir.translate(n));
	}

	@Override
	public String toString() {
		return "RoverPosition [locn=(" + locn.x + ", " + locn.y + "), dir=" + dir + "]";
	}
}
