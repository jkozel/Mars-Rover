package mars;

import static org.junit.Assert.*;

import org.junit.Test;

public class OrientationTest {
	@Test
	public final void testTranslate() {
		assertEquals(Orientation.NORTH, Orientation.NORTH.translate(0));
		assertEquals(Orientation.EAST, Orientation.NORTH.translate(1));
		assertEquals(Orientation.SOUTH, Orientation.NORTH.translate(2));
		assertEquals(Orientation.WEST, Orientation.NORTH.translate(3));
		assertEquals(Orientation.NORTH, Orientation.NORTH.translate(4));
		assertEquals(Orientation.EAST, Orientation.NORTH.translate(5));
		assertEquals(Orientation.WEST, Orientation.NORTH.translate(-1));
		assertEquals(Orientation.SOUTH, Orientation.NORTH.translate(-2));
		assertEquals(Orientation.EAST, Orientation.NORTH.translate(-3));
		assertEquals(Orientation.NORTH, Orientation.NORTH.translate(-4));
		assertEquals(Orientation.WEST, Orientation.NORTH.translate(-5));

		assertEquals(Orientation.EAST, Orientation.EAST.translate(0));
		assertEquals(Orientation.SOUTH, Orientation.EAST.translate(1));
		assertEquals(Orientation.WEST, Orientation.EAST.translate(2));
		assertEquals(Orientation.NORTH, Orientation.EAST.translate(3));
		assertEquals(Orientation.EAST, Orientation.EAST.translate(4));
		assertEquals(Orientation.SOUTH, Orientation.EAST.translate(5));
		assertEquals(Orientation.NORTH, Orientation.EAST.translate(-1));
		assertEquals(Orientation.WEST, Orientation.EAST.translate(-2));
		assertEquals(Orientation.SOUTH, Orientation.EAST.translate(-3));
		assertEquals(Orientation.EAST, Orientation.EAST.translate(-4));
		assertEquals(Orientation.NORTH, Orientation.EAST.translate(-5));

		assertEquals(Orientation.SOUTH, Orientation.SOUTH.translate(0));
		assertEquals(Orientation.WEST, Orientation.SOUTH.translate(1));
		assertEquals(Orientation.NORTH, Orientation.SOUTH.translate(2));
		assertEquals(Orientation.EAST, Orientation.SOUTH.translate(3));
		assertEquals(Orientation.SOUTH, Orientation.SOUTH.translate(4));
		assertEquals(Orientation.WEST, Orientation.SOUTH.translate(5));
		assertEquals(Orientation.EAST, Orientation.SOUTH.translate(-1));
		assertEquals(Orientation.NORTH, Orientation.SOUTH.translate(-2));
		assertEquals(Orientation.WEST, Orientation.SOUTH.translate(-3));
		assertEquals(Orientation.SOUTH, Orientation.SOUTH.translate(-4));
		assertEquals(Orientation.EAST, Orientation.SOUTH.translate(-5));

		assertEquals(Orientation.WEST, Orientation.WEST.translate(0));
		assertEquals(Orientation.NORTH, Orientation.WEST.translate(1));
		assertEquals(Orientation.EAST, Orientation.WEST.translate(2));
		assertEquals(Orientation.SOUTH, Orientation.WEST.translate(3));
		assertEquals(Orientation.WEST, Orientation.WEST.translate(4));
		assertEquals(Orientation.NORTH, Orientation.WEST.translate(5));
		assertEquals(Orientation.SOUTH, Orientation.WEST.translate(-1));
		assertEquals(Orientation.EAST, Orientation.WEST.translate(-2));
		assertEquals(Orientation.NORTH, Orientation.WEST.translate(-3));
		assertEquals(Orientation.WEST, Orientation.WEST.translate(-4));
		assertEquals(Orientation.SOUTH, Orientation.WEST.translate(-5));
	}
}
