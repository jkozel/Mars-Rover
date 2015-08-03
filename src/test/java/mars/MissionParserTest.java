package mars;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Point;
import java.util.Arrays;

import org.junit.Test;

public class MissionParserTest {
	private MissionParser parser = new MissionParser();

	@Test
	public final void testFormatRoverPosition() {
		assertEquals("1 2 N", parser.formatRoverPosition(new RoverPosition(1, 2, Orientation.NORTH)));
		assertEquals("1 2 E", parser.formatRoverPosition(new RoverPosition(1, 2, Orientation.EAST)));
		assertEquals("1 2 S", parser.formatRoverPosition(new RoverPosition(1, 2, Orientation.SOUTH)));
		assertEquals("1 2 W", parser.formatRoverPosition(new RoverPosition(1, 2, Orientation.WEST)));

		assertEquals("-1 -2 N", parser.formatRoverPosition(new RoverPosition(-1, -2, Orientation.NORTH)));
		assertEquals("-1 -2 E", parser.formatRoverPosition(new RoverPosition(-1, -2, Orientation.EAST)));
		assertEquals("-1 -2 S", parser.formatRoverPosition(new RoverPosition(-1, -2, Orientation.SOUTH)));
		assertEquals("-1 -2 W", parser.formatRoverPosition(new RoverPosition(-1, -2, Orientation.WEST)));

		assertEquals("[invalid position]", parser.formatRoverPosition(new RoverPosition(1, 2, null)).toLowerCase());
		assertEquals("[invalid position]", parser.formatRoverPosition(null).toLowerCase());
	}

	@Test
	public final void testParseCommands() {
		assertEquals(Arrays.asList(RoverMovement.LEFT), parser.parseCommands("L"));
		assertEquals(Arrays.asList(RoverMovement.LEFT), parser.parseCommands("l"));
		assertEquals(Arrays.asList(RoverMovement.RIGHT), parser.parseCommands("R"));
		assertEquals(Arrays.asList(RoverMovement.RIGHT), parser.parseCommands("r"));
		assertEquals(Arrays.asList(RoverMovement.FORWARD), parser.parseCommands("M"));
		assertEquals(Arrays.asList(RoverMovement.FORWARD), parser.parseCommands("m"));
		assertEquals(Arrays.asList(RoverMovement.LEFT, RoverMovement.RIGHT, RoverMovement.FORWARD), parser.parseCommands("LRM"));
		assertEquals(Arrays.asList(RoverMovement.LEFT, RoverMovement.LEFT, RoverMovement.RIGHT, RoverMovement.RIGHT, RoverMovement.FORWARD, RoverMovement.FORWARD,
				RoverMovement.RIGHT, RoverMovement.LEFT, RoverMovement.FORWARD), parser.parseCommands("LLRRMMRLM"));

		assertTrue(parser.parseCommands(null).isEmpty());

		try {
			String seq = "X";
			parser.parseCommands(seq);
			fail("Invalid command sequence should throw IllegalArgumentException: " + seq);
		} catch (IllegalArgumentException e) {
			// Expected
		}
		try {
			String seq = "LRMX";
			parser.parseCommands(seq);
			fail("Invalid command sequence should throw IllegalArgumentException: " + seq);
		} catch (IllegalArgumentException e) {
			// Expected
		}
		try {
			String seq = "XLRM";
			parser.parseCommands(seq);
			fail("Invalid command sequence should throw IllegalArgumentException: " + seq);
		} catch (IllegalArgumentException e) {
			// Expected
		}
	}

	@Test
	public final void testParseOrientation() {
		assertEquals(Orientation.NORTH, parser.parseOrientation("N"));
		assertEquals(Orientation.NORTH, parser.parseOrientation("n"));
		assertEquals(Orientation.EAST, parser.parseOrientation("E"));
		assertEquals(Orientation.EAST, parser.parseOrientation("e"));
		assertEquals(Orientation.SOUTH, parser.parseOrientation("S"));
		assertEquals(Orientation.SOUTH, parser.parseOrientation("s"));
		assertEquals(Orientation.WEST, parser.parseOrientation("W"));
		assertEquals(Orientation.WEST, parser.parseOrientation("w"));

		try {
			parser.parseOrientation("x");
			fail("Should have thrown an IllegalArgumentException for \"x\"");
		} catch (IllegalArgumentException e) {
			// Expected
		}
		try {
			parser.parseOrientation("NORTH");
			fail("Should have thrown an IllegalArgumentException for \"NORTH\"");
		} catch (IllegalArgumentException e) {
			// Expected
		}
		try {
			parser.parseOrientation("1");
			fail("Should have thrown an IllegalArgumentException for \"1\"");
		} catch (IllegalArgumentException e) {
			// Expected
		}
	}

	@Test
	public final void testParsePoint() {
		Point upperRight;

		upperRight = parser.parsePoint("0 0");
		assertEquals(new Point(0, 0), upperRight);
		upperRight = parser.parsePoint("5 5");
		assertEquals(new Point(5, 5), upperRight);
		upperRight = parser.parsePoint("-5 -5");
		assertEquals(new Point(-5, -5), upperRight);

		try {
			upperRight = parser.parsePoint(null);
			fail("NULL input should throw a NullPointerException");
		} catch (NullPointerException e) {
			// Expected
		}
		try {
			upperRight = parser.parsePoint("");
			fail("Less than 2 values should throw a IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Expected
		}
		try {
			upperRight = parser.parsePoint("0");
			fail("Less than 2 values should throw a IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Expected
		}
		try {
			upperRight = parser.parsePoint("A 0");
			fail("Non-numeric X should throw a IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Expected
		}
		try {
			upperRight = parser.parsePoint("0 B");
			fail("Non-numeric Y should throw a IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Expected
		}
		try {
			upperRight = parser.parsePoint("5.5 5.5");
			fail("Non-integer values should throw a IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Expected
		}
	}

	@Test
	public final void testParseRoverPosition() {
		RoverPosition posn;

		posn = parser.parseRoverPosition("0 0 N");
		assertEquals(new RoverPosition(0, 0, Orientation.NORTH), posn);
		posn = parser.parseRoverPosition("0 0 E");
		assertEquals(new RoverPosition(0, 0, Orientation.EAST), posn);
		posn = parser.parseRoverPosition("0 0 S");
		assertEquals(new RoverPosition(0, 0, Orientation.SOUTH), posn);
		posn = parser.parseRoverPosition("0 0 W");
		assertEquals(new RoverPosition(0, 0, Orientation.WEST), posn);

		posn = parser.parseRoverPosition("9 9 N");
		assertEquals(new RoverPosition(9, 9, Orientation.NORTH), posn);
		posn = parser.parseRoverPosition("9 9 E");
		assertEquals(new RoverPosition(9, 9, Orientation.EAST), posn);
		posn = parser.parseRoverPosition("9 9 S");
		assertEquals(new RoverPosition(9, 9, Orientation.SOUTH), posn);
		posn = parser.parseRoverPosition("9 9 W");
		assertEquals(new RoverPosition(9, 9, Orientation.WEST), posn);

		posn = parser.parseRoverPosition("-9 -9 N");
		assertEquals(new RoverPosition(-9, -9, Orientation.NORTH), posn);
		posn = parser.parseRoverPosition("-9 -9 E");
		assertEquals(new RoverPosition(-9, -9, Orientation.EAST), posn);
		posn = parser.parseRoverPosition("-9 -9 S");
		assertEquals(new RoverPosition(-9, -9, Orientation.SOUTH), posn);
		posn = parser.parseRoverPosition("-9 -9 W");
		assertEquals(new RoverPosition(-9, -9, Orientation.WEST), posn);

		try {
			posn = parser.parseRoverPosition(null);
			fail("NULL input should have resulted in a NullPointerException");
		} catch (NullPointerException e) {
			// Expected
		}

		try {
			posn = parser.parseRoverPosition("0 0");
			fail("Less than 3 values should throw IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Expected
		}

		try {
			posn = parser.parseRoverPosition("0 0 X");
			fail("Invalid direction should throw IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Expected
		}

		try {
			posn = parser.parseRoverPosition("A 0 N");
			fail("Non-numeric X should throw IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Expected
		}

		try {
			posn = parser.parseRoverPosition("0 A N");
			fail("Non-numeric Y should throw IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Expected
		}

		try {
			posn = parser.parseRoverPosition("0.1 0 N");
			fail("Non-integer X should throw IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Expected
		}
	}
}
