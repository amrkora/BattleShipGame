package com.guestline.game.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class GridSquareTest {
	GridSquare gridSquareTest = new GridSquare(2, 3, false);
	Player player = new Player("Test2");

	@Test
	public void equalsTest() {

		assertEquals(gridSquareTest, player.getGridSquares()[2][3]);
		assertNotEquals(gridSquareTest, player.getGridSquares()[0][0]);

	}
}
