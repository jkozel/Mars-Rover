package mars;

import static org.junit.Assert.*;

import org.junit.Test;

public class RoverMovementTest {
	@Test
	public final void testExec() {
		RoverPosition posn, newPosn;

		assertNull(RoverMovement.LEFT.exec(null));
		assertNull(RoverMovement.RIGHT.exec(null));
		assertNull(RoverMovement.FORWARD.exec(null));

		// Test at (0, 0)
		posn = new RoverPosition(0, 0, Orientation.NORTH);
		newPosn = RoverMovement.LEFT.exec(posn);
		assertEquals(new RoverPosition(0, 0, Orientation.NORTH), posn);
		assertEquals(new RoverPosition(0, 0, Orientation.WEST), newPosn);
		newPosn = RoverMovement.RIGHT.exec(posn);
		assertEquals(new RoverPosition(0, 0, Orientation.NORTH), posn);
		assertEquals(new RoverPosition(0, 0, Orientation.EAST), newPosn);
		newPosn = RoverMovement.FORWARD.exec(posn);
		assertEquals(new RoverPosition(0, 0, Orientation.NORTH), posn);
		assertEquals(new RoverPosition(0, 1, Orientation.NORTH), newPosn);

		posn = new RoverPosition(0, 0, Orientation.EAST);
		newPosn = RoverMovement.LEFT.exec(posn);
		assertEquals(new RoverPosition(0, 0, Orientation.EAST), posn);
		assertEquals(new RoverPosition(0, 0, Orientation.NORTH), newPosn);
		newPosn = RoverMovement.RIGHT.exec(posn);
		assertEquals(new RoverPosition(0, 0, Orientation.EAST), posn);
		assertEquals(new RoverPosition(0, 0, Orientation.SOUTH), newPosn);
		newPosn = RoverMovement.FORWARD.exec(posn);
		assertEquals(new RoverPosition(0, 0, Orientation.EAST), posn);
		assertEquals(new RoverPosition(1, 0, Orientation.EAST), newPosn);

		posn = new RoverPosition(0, 0, Orientation.SOUTH);
		newPosn = RoverMovement.LEFT.exec(posn);
		assertEquals(new RoverPosition(0, 0, Orientation.SOUTH), posn);
		assertEquals(new RoverPosition(0, 0, Orientation.EAST), newPosn);
		newPosn = RoverMovement.RIGHT.exec(posn);
		assertEquals(new RoverPosition(0, 0, Orientation.SOUTH), posn);
		assertEquals(new RoverPosition(0, 0, Orientation.WEST), newPosn);
		newPosn = RoverMovement.FORWARD.exec(posn);
		assertEquals(new RoverPosition(0, 0, Orientation.SOUTH), posn);
		assertEquals(new RoverPosition(0, -1, Orientation.SOUTH), newPosn);

		posn = new RoverPosition(0, 0, Orientation.WEST);
		newPosn = RoverMovement.LEFT.exec(posn);
		assertEquals(new RoverPosition(0, 0, Orientation.WEST), posn);
		assertEquals(new RoverPosition(0, 0, Orientation.SOUTH), newPosn);
		newPosn = RoverMovement.RIGHT.exec(posn);
		assertEquals(new RoverPosition(0, 0, Orientation.WEST), posn);
		assertEquals(new RoverPosition(0, 0, Orientation.NORTH), newPosn);
		newPosn = RoverMovement.FORWARD.exec(posn);
		assertEquals(new RoverPosition(0, 0, Orientation.WEST), posn);
		assertEquals(new RoverPosition(-1, 0, Orientation.WEST), newPosn);


		// Test at (0, 9)
		posn = new RoverPosition(0, 9, Orientation.NORTH);
		newPosn = RoverMovement.LEFT.exec(posn);
		assertEquals(new RoverPosition(0, 9, Orientation.NORTH), posn);
		assertEquals(new RoverPosition(0, 9, Orientation.WEST), newPosn);
		newPosn = RoverMovement.RIGHT.exec(posn);
		assertEquals(new RoverPosition(0, 9, Orientation.NORTH), posn);
		assertEquals(new RoverPosition(0, 9, Orientation.EAST), newPosn);
		newPosn = RoverMovement.FORWARD.exec(posn);
		assertEquals(new RoverPosition(0, 9, Orientation.NORTH), posn);
		assertEquals(new RoverPosition(0, 10, Orientation.NORTH), newPosn);

		posn = new RoverPosition(0, 9, Orientation.EAST);
		newPosn = RoverMovement.LEFT.exec(posn);
		assertEquals(new RoverPosition(0, 9, Orientation.EAST), posn);
		assertEquals(new RoverPosition(0, 9, Orientation.NORTH), newPosn);
		newPosn = RoverMovement.RIGHT.exec(posn);
		assertEquals(new RoverPosition(0, 9, Orientation.EAST), posn);
		assertEquals(new RoverPosition(0, 9, Orientation.SOUTH), newPosn);
		newPosn = RoverMovement.FORWARD.exec(posn);
		assertEquals(new RoverPosition(0, 9, Orientation.EAST), posn);
		assertEquals(new RoverPosition(1, 9, Orientation.EAST), newPosn);

		posn = new RoverPosition(0, 9, Orientation.SOUTH);
		newPosn = RoverMovement.LEFT.exec(posn);
		assertEquals(new RoverPosition(0, 9, Orientation.SOUTH), posn);
		assertEquals(new RoverPosition(0, 9, Orientation.EAST), newPosn);
		newPosn = RoverMovement.RIGHT.exec(posn);
		assertEquals(new RoverPosition(0, 9, Orientation.SOUTH), posn);
		assertEquals(new RoverPosition(0, 9, Orientation.WEST), newPosn);
		newPosn = RoverMovement.FORWARD.exec(posn);
		assertEquals(new RoverPosition(0, 9, Orientation.SOUTH), posn);
		assertEquals(new RoverPosition(0, 8, Orientation.SOUTH), newPosn);

		posn = new RoverPosition(0, 9, Orientation.WEST);
		newPosn = RoverMovement.LEFT.exec(posn);
		assertEquals(new RoverPosition(0, 9, Orientation.WEST), posn);
		assertEquals(new RoverPosition(0, 9, Orientation.SOUTH), newPosn);
		newPosn = RoverMovement.RIGHT.exec(posn);
		assertEquals(new RoverPosition(0, 9, Orientation.WEST), posn);
		assertEquals(new RoverPosition(0, 9, Orientation.NORTH), newPosn);
		newPosn = RoverMovement.FORWARD.exec(posn);
		assertEquals(new RoverPosition(0, 9, Orientation.WEST), posn);
		assertEquals(new RoverPosition(-1, 9, Orientation.WEST), newPosn);


		// Test at (9, 0)
		posn = new RoverPosition(9, 0, Orientation.NORTH);
		newPosn = RoverMovement.LEFT.exec(posn);
		assertEquals(new RoverPosition(9, 0, Orientation.NORTH), posn);
		assertEquals(new RoverPosition(9, 0, Orientation.WEST), newPosn);
		newPosn = RoverMovement.RIGHT.exec(posn);
		assertEquals(new RoverPosition(9, 0, Orientation.NORTH), posn);
		assertEquals(new RoverPosition(9, 0, Orientation.EAST), newPosn);
		newPosn = RoverMovement.FORWARD.exec(posn);
		assertEquals(new RoverPosition(9, 0, Orientation.NORTH), posn);
		assertEquals(new RoverPosition(9, 1, Orientation.NORTH), newPosn);

		posn = new RoverPosition(9, 0, Orientation.EAST);
		newPosn = RoverMovement.LEFT.exec(posn);
		assertEquals(new RoverPosition(9, 0, Orientation.EAST), posn);
		assertEquals(new RoverPosition(9, 0, Orientation.NORTH), newPosn);
		newPosn = RoverMovement.RIGHT.exec(posn);
		assertEquals(new RoverPosition(9, 0, Orientation.EAST), posn);
		assertEquals(new RoverPosition(9, 0, Orientation.SOUTH), newPosn);
		newPosn = RoverMovement.FORWARD.exec(posn);
		assertEquals(new RoverPosition(9, 0, Orientation.EAST), posn);
		assertEquals(new RoverPosition(10, 0, Orientation.EAST), newPosn);

		posn = new RoverPosition(9, 0, Orientation.SOUTH);
		newPosn = RoverMovement.LEFT.exec(posn);
		assertEquals(new RoverPosition(9, 0, Orientation.SOUTH), posn);
		assertEquals(new RoverPosition(9, 0, Orientation.EAST), newPosn);
		newPosn = RoverMovement.RIGHT.exec(posn);
		assertEquals(new RoverPosition(9, 0, Orientation.SOUTH), posn);
		assertEquals(new RoverPosition(9, 0, Orientation.WEST), newPosn);
		newPosn = RoverMovement.FORWARD.exec(posn);
		assertEquals(new RoverPosition(9, 0, Orientation.SOUTH), posn);
		assertEquals(new RoverPosition(9, -1, Orientation.SOUTH), newPosn);

		posn = new RoverPosition(9, 0, Orientation.WEST);
		newPosn = RoverMovement.LEFT.exec(posn);
		assertEquals(new RoverPosition(9, 0, Orientation.WEST), posn);
		assertEquals(new RoverPosition(9, 0, Orientation.SOUTH), newPosn);
		newPosn = RoverMovement.RIGHT.exec(posn);
		assertEquals(new RoverPosition(9, 0, Orientation.WEST), posn);
		assertEquals(new RoverPosition(9, 0, Orientation.NORTH), newPosn);
		newPosn = RoverMovement.FORWARD.exec(posn);
		assertEquals(new RoverPosition(9, 0, Orientation.WEST), posn);
		assertEquals(new RoverPosition(8, 0, Orientation.WEST), newPosn);


		// Test at (9, 9)
		posn = new RoverPosition(9, 9, Orientation.NORTH);
		newPosn = RoverMovement.LEFT.exec(posn);
		assertEquals(new RoverPosition(9, 9, Orientation.NORTH), posn);
		assertEquals(new RoverPosition(9, 9, Orientation.WEST), newPosn);
		newPosn = RoverMovement.RIGHT.exec(posn);
		assertEquals(new RoverPosition(9, 9, Orientation.NORTH), posn);
		assertEquals(new RoverPosition(9, 9, Orientation.EAST), newPosn);
		newPosn = RoverMovement.FORWARD.exec(posn);
		assertEquals(new RoverPosition(9, 9, Orientation.NORTH), posn);
		assertEquals(new RoverPosition(9, 10, Orientation.NORTH), newPosn);

		posn = new RoverPosition(9, 9, Orientation.EAST);
		newPosn = RoverMovement.LEFT.exec(posn);
		assertEquals(new RoverPosition(9, 9, Orientation.EAST), posn);
		assertEquals(new RoverPosition(9, 9, Orientation.NORTH), newPosn);
		newPosn = RoverMovement.RIGHT.exec(posn);
		assertEquals(new RoverPosition(9, 9, Orientation.EAST), posn);
		assertEquals(new RoverPosition(9, 9, Orientation.SOUTH), newPosn);
		newPosn = RoverMovement.FORWARD.exec(posn);
		assertEquals(new RoverPosition(9, 9, Orientation.EAST), posn);
		assertEquals(new RoverPosition(10, 9, Orientation.EAST), newPosn);

		posn = new RoverPosition(9, 9, Orientation.SOUTH);
		newPosn = RoverMovement.LEFT.exec(posn);
		assertEquals(new RoverPosition(9, 9, Orientation.SOUTH), posn);
		assertEquals(new RoverPosition(9, 9, Orientation.EAST), newPosn);
		newPosn = RoverMovement.RIGHT.exec(posn);
		assertEquals(new RoverPosition(9, 9, Orientation.SOUTH), posn);
		assertEquals(new RoverPosition(9, 9, Orientation.WEST), newPosn);
		newPosn = RoverMovement.FORWARD.exec(posn);
		assertEquals(new RoverPosition(9, 9, Orientation.SOUTH), posn);
		assertEquals(new RoverPosition(9, 8, Orientation.SOUTH), newPosn);

		posn = new RoverPosition(9, 9, Orientation.WEST);
		newPosn = RoverMovement.LEFT.exec(posn);
		assertEquals(new RoverPosition(9, 9, Orientation.WEST), posn);
		assertEquals(new RoverPosition(9, 9, Orientation.SOUTH), newPosn);
		newPosn = RoverMovement.RIGHT.exec(posn);
		assertEquals(new RoverPosition(9, 9, Orientation.WEST), posn);
		assertEquals(new RoverPosition(9, 9, Orientation.NORTH), newPosn);
		newPosn = RoverMovement.FORWARD.exec(posn);
		assertEquals(new RoverPosition(9, 9, Orientation.WEST), posn);
		assertEquals(new RoverPosition(8, 9, Orientation.WEST), newPosn);


		// Test at (-9, -9)
		posn = new RoverPosition(-9, -9, Orientation.NORTH);
		newPosn = RoverMovement.LEFT.exec(posn);
		assertEquals(new RoverPosition(-9, -9, Orientation.NORTH), posn);
		assertEquals(new RoverPosition(-9, -9, Orientation.WEST), newPosn);
		newPosn = RoverMovement.RIGHT.exec(posn);
		assertEquals(new RoverPosition(-9, -9, Orientation.NORTH), posn);
		assertEquals(new RoverPosition(-9, -9, Orientation.EAST), newPosn);
		newPosn = RoverMovement.FORWARD.exec(posn);
		assertEquals(new RoverPosition(-9, -9, Orientation.NORTH), posn);
		assertEquals(new RoverPosition(-9, -8, Orientation.NORTH), newPosn);

		posn = new RoverPosition(-9, -9, Orientation.EAST);
		newPosn = RoverMovement.LEFT.exec(posn);
		assertEquals(new RoverPosition(-9, -9, Orientation.EAST), posn);
		assertEquals(new RoverPosition(-9, -9, Orientation.NORTH), newPosn);
		newPosn = RoverMovement.RIGHT.exec(posn);
		assertEquals(new RoverPosition(-9, -9, Orientation.EAST), posn);
		assertEquals(new RoverPosition(-9, -9, Orientation.SOUTH), newPosn);
		newPosn = RoverMovement.FORWARD.exec(posn);
		assertEquals(new RoverPosition(-9, -9, Orientation.EAST), posn);
		assertEquals(new RoverPosition(-8, -9, Orientation.EAST), newPosn);

		posn = new RoverPosition(-9, -9, Orientation.SOUTH);
		newPosn = RoverMovement.LEFT.exec(posn);
		assertEquals(new RoverPosition(-9, -9, Orientation.SOUTH), posn);
		assertEquals(new RoverPosition(-9, -9, Orientation.EAST), newPosn);
		newPosn = RoverMovement.RIGHT.exec(posn);
		assertEquals(new RoverPosition(-9, -9, Orientation.SOUTH), posn);
		assertEquals(new RoverPosition(-9, -9, Orientation.WEST), newPosn);
		newPosn = RoverMovement.FORWARD.exec(posn);
		assertEquals(new RoverPosition(-9, -9, Orientation.SOUTH), posn);
		assertEquals(new RoverPosition(-9, -10, Orientation.SOUTH), newPosn);

		posn = new RoverPosition(-9, -9, Orientation.WEST);
		newPosn = RoverMovement.LEFT.exec(posn);
		assertEquals(new RoverPosition(-9, -9, Orientation.WEST), posn);
		assertEquals(new RoverPosition(-9, -9, Orientation.SOUTH), newPosn);
		newPosn = RoverMovement.RIGHT.exec(posn);
		assertEquals(new RoverPosition(-9, -9, Orientation.WEST), posn);
		assertEquals(new RoverPosition(-9, -9, Orientation.NORTH), newPosn);
		newPosn = RoverMovement.FORWARD.exec(posn);
		assertEquals(new RoverPosition(-9, -9, Orientation.WEST), posn);
		assertEquals(new RoverPosition(-10, -9, Orientation.WEST), newPosn);
	}

}
