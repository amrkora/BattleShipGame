package com.guestline.game.handler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.guestline.game.GameConstant;
import com.guestline.game.dto.Player;
import com.guestline.game.ships.Ship;

public class PlayerManagerTest {
	PlayerManager playerManager = new PlayerManager();
	Player player = new Player("Test Player");

	@Test
	public void createShips() {
		playerManager.createShips(player);
		assertEquals(2, player.getShips().size());
	}

	@Test
	public void createShipsTestBusyLocationCountAfterPostioning() {
		playerManager.createShips(player);
		playerManager.postionShips(player);
		int actualCountOfGridSquaresForAllShips = 0;

		for (Ship ship : player.getShips()) {
			actualCountOfGridSquaresForAllShips += ship.getNumberOfSquaresLength();
		}

		int busyLocationCountAfterPostioning = 0;
		for (int i = 0; i < GameConstant.OCCEAN_GRID_SQUARES_NUMBERS; i++) {
			for (int j = 0; j < GameConstant.OCCEAN_GRID_SQUARES_LETTERS; j++) {
				if (player.getGridSquares()[i][j].isBusy())
					busyLocationCountAfterPostioning++;
			}
		}

		assertEquals(actualCountOfGridSquaresForAllShips, busyLocationCountAfterPostioning);
	}
}
