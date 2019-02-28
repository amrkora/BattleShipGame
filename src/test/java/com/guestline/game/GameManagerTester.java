package com.guestline.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.guestline.game.dto.Coordinates;
import com.guestline.game.dto.GridSquare;
import com.guestline.game.dto.Player;
import com.guestline.game.handler.PlayerManager;

public class GameManagerTester {
	PlayerManager playerManager = new PlayerManager();
	Player human = new Player("Human");
	Player computer = new Player("Computer");
	GameManager gameManager = new GameManager();

	@Test
	public void attackTest() {
		playerManager.createShips(computer);
		gameManager.managePlayer(computer);
		GridSquare gridSquare = computer.getGridSquares()[0][0];

		boolean result = gameManager.attack(computer, new Coordinates(0, 0));
		assertEquals(gridSquare.isBusy(), result);

	}
}
