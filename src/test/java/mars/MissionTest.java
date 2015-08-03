package mars;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MissionTest {
	Mission mission = new Mission(new Point(5, 5));

	@Test
	public final void testRunRover() {
		RoverPosition startPosn, endPosn;
		List<RoverMovement> commands;

		startPosn = new RoverPosition(0, 0, Orientation.NORTH);
		commands = Arrays.asList(RoverMovement.FORWARD);
		endPosn = mission.runRover(new Rover(startPosn, commands));
		assertEquals(new RoverPosition(0, 1, Orientation.NORTH), endPosn);
		commands = Arrays.asList(RoverMovement.LEFT);
		endPosn = mission.runRover(new Rover(startPosn, commands));
		assertEquals(new RoverPosition(0, 0, Orientation.WEST), endPosn);
		commands = Arrays.asList(RoverMovement.RIGHT);
		endPosn = mission.runRover(new Rover(startPosn, commands));
		assertEquals(new RoverPosition(0, 0, Orientation.EAST), endPosn);

		startPosn = new RoverPosition(0, 0, Orientation.EAST);
		commands = Arrays.asList(RoverMovement.FORWARD);
		endPosn = mission.runRover(new Rover(startPosn, commands));
		assertEquals(new RoverPosition(1, 0, Orientation.EAST), endPosn);
		commands = Arrays.asList(RoverMovement.LEFT);
		endPosn = mission.runRover(new Rover(startPosn, commands));
		assertEquals(new RoverPosition(0, 0, Orientation.NORTH), endPosn);
		commands = Arrays.asList(RoverMovement.RIGHT);
		endPosn = mission.runRover(new Rover(startPosn, commands));
		assertEquals(new RoverPosition(0, 0, Orientation.SOUTH), endPosn);

		startPosn = new RoverPosition(0, 0, Orientation.SOUTH);
		commands = Arrays.asList(RoverMovement.FORWARD);
		try {
			endPosn = mission.runRover(new Rover(startPosn, commands));
			fail("Rover is out of bounds, should have thrown an IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			// Expected
		}
		commands = Arrays.asList(RoverMovement.LEFT);
		endPosn = mission.runRover(new Rover(startPosn, commands));
		assertEquals(new RoverPosition(0, 0, Orientation.EAST), endPosn);
		commands = Arrays.asList(RoverMovement.RIGHT);
		endPosn = mission.runRover(new Rover(startPosn, commands));
		assertEquals(new RoverPosition(0, 0, Orientation.WEST), endPosn);

		startPosn = new RoverPosition(0, 0, Orientation.WEST);
		commands = Arrays.asList(RoverMovement.FORWARD);
		try {
			endPosn = mission.runRover(new Rover(startPosn, commands));
			fail("Rover is out of bounds, should have thrown an IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			// Expected
		}
		commands = Arrays.asList(RoverMovement.LEFT);
		endPosn = mission.runRover(new Rover(startPosn, commands));
		assertEquals(new RoverPosition(0, 0, Orientation.SOUTH), endPosn);
		commands = Arrays.asList(RoverMovement.RIGHT);
		endPosn = mission.runRover(new Rover(startPosn, commands));
		assertEquals(new RoverPosition(0, 0, Orientation.NORTH), endPosn);


		// Now test sequences of movements
		startPosn = new RoverPosition(2, 2, Orientation.NORTH);
		commands = Arrays.asList(RoverMovement.FORWARD, RoverMovement.RIGHT, RoverMovement.FORWARD);
		endPosn = mission.runRover(new Rover(startPosn, commands));
		assertEquals(new RoverPosition(3, 3, Orientation.EAST), endPosn);
	}

	@Test
	public final void testRun_sample() {
		System.out.println("Test Run: Sample from description");
		List<String> entries = Arrays.asList(
				"5 5",
				"1 2 N",
				"LMLMLMLMM",
				"3 3 E",
				"MMRMMRMRRM"
				);
		Mission mission = Mission.create(entries);
		mission.run();
		assertEquals(new RoverPosition(1, 3, Orientation.NORTH), mission.getRovers().get(0).getFinalPosn());
		assertEquals(new RoverPosition(5, 1, Orientation.EAST), mission.getRovers().get(1).getFinalPosn());
	}

	@Test
	public final void testRun_sampleOutOfBounds() {
		System.out.println("Test Run: Sample from description plus third out of bounds");
		List<String> entries = Arrays.asList(
				"5 5",
				"1 2 N",
				"LMLMLMLMM",
				"3 3 E",
				"MMRMMRMRRM",
				"4 4 N",
				"MM"
				);
		Mission mission = Mission.create(entries);
		mission.run();
		assertEquals(new RoverPosition(1, 3, Orientation.NORTH), mission.getRovers().get(0).getFinalPosn());
		assertEquals(new RoverPosition(5, 1, Orientation.EAST), mission.getRovers().get(1).getFinalPosn());
		assertNull(mission.getRovers().get(2).getFinalPosn());
	}

	@Test
	public final void testRun_invalidNumEntries() {
		System.out.println("Test Run: Too few entries");
		// Enough to test only one case, since parsePoint() is tested in MissionParserTest
		List<String> entries = Arrays.asList(
				"X Y",
				"1 2 N"
				);
		try {
			Mission mission = Mission.create(entries);
			fail("Too few entriesshould throw an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Expected
		}
	}

	@Test
	public final void testRun_invalidUpperRight() {
		System.out.println("Test Run: Invalid upper right bounds");
		// Enough to test only one case, since parsePoint() is tested in MissionParserTest
		List<String> entries = Arrays.asList(
				"X Y",
				"1 2 N",
				"M"
				);
		try {
			Mission mission = Mission.create(entries);
			fail("Invalid upper right bounds should throw an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Expected
		}
	}

	@Test
	public final void testRun_invalidStartPosn() {
		System.out.println("Test Run: Invalid start position");
		// Enough to test only one case, since parseRoverPosition() is tested in MissionParserTest
		List<String> entries = Arrays.asList(
				"5 5",
				"1 2 NORTH",
				"M"
				);
		try {
			Mission mission = Mission.create(entries);
			fail("Invalid start position should throw an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Expected
		}
	}

	@Test
	public final void testRun_invalidCommand() {
		System.out.println("Test Run: Invalid command sequence");
		// An invalid command sequence results in the rover being dropped from the mission. Other rovers proceed normally.
		List<String> entries = Arrays.asList(
				"5 5",
				"1 2 N",
				"MLXM"
				);
		Mission mission = Mission.create(entries);
		assertTrue(mission.getRovers().isEmpty());

		entries = Arrays.asList(
				"5 5",
				"1 2 N",
				"MXM",
				"3 3 E",
				"M"
				);
		mission = Mission.create(entries);
		mission.run();
		assertEquals(1, mission.getRovers().size());
		assertEquals(new RoverPosition(4, 3, Orientation.EAST), mission.getRovers().get(0).getFinalPosn());
	}
}
