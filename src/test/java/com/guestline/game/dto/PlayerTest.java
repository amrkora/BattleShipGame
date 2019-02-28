package com.guestline.game.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.guestline.game.GameConstant;

public class PlayerTest {
	Player player = new Player("Test1");

	@Test
	public void createGridSquares() {
		assertNotNull(player.getGridSquares());
		assertEquals(player.getGridSquares().length, GameConstant.OCCEAN_GRID_SQUARES_LETTERS);
		assertEquals(player.getGridSquares()[0].length, GameConstant.OCCEAN_GRID_SQUARES_NUMBERS);
		
	}
}
