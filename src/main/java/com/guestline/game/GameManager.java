package com.guestline.game;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.guestline.game.dto.Coordinates;
import com.guestline.game.dto.GridSquare;
import com.guestline.game.dto.Player;
import com.guestline.game.handler.PlayerManager;
import com.guestline.game.ships.Ship;

public class GameManager {
	private static Logger logger = LogManager.getLogger(GameManager.class);

	private static int numberOfGamePlayAttacks = 0;
	PlayerManager playerManager = new PlayerManager();

	public static void main(String[] args) {

		GameManager game = new GameManager();
		System.out.println("Game has started.. ");
		game.startGame();

	}

	private void startGame() {
		System.out.print("Enter the fisrt player Name: ");
		Scanner input = new Scanner(System.in);
		String player1Name = input.next();
		Player player1 = new Player(player1Name);
//		managePlayer(player1);

		Player player2 = new Player("Computer");
		managePlayer(player2);
		printOutHoles(player2);
		boolean exitCondition = false;
		do {
			Coordinates coordinates = getUserInputsForPlay(player1);

			boolean attackResult = attack(player2, coordinates);
			if (attackResult) {
				player1.increaseHits();
			} else {
				player1.increaseMiss();
			}

			numberOfGamePlayAttacks++;

			exitCondition = !(player2.isDefeat());
			printOutHoles(player2);
			printGameStatistics(player1, player2);

		} while (exitCondition);

		System.out.println("Well Done, Game has finished ... ");
	}

	public void managePlayer(Player player) {
		playerManager.createShips(player);
		playerManager.postionShips(player);
	}

	public boolean attack(Player player, Coordinates coordinates) {
		boolean result = false;

		GridSquare gridSquare = player.getGridSquares()[coordinates.getX()][coordinates.getY()];
		if (gridSquare.isBusy() && !gridSquare.isHit()) {

			for (Ship ship : player.getShips()) {
				for (GridSquare activeSquare : ship.getActiveSquares()) {
					if (gridSquare.equals(activeSquare)) {
						gridSquare.setHit(true);
						ship.updateShipStatus();
						result = true;
					}
				}
			}

		} else {
			gridSquare.setMiss(true);
		}
		System.out.println("Attack " + (result ? "hits" : "miss"));
		return result;
	}

	private Coordinates getUserInputsForPlay(Player player) {
		Coordinates playerInputCoordinates = new Coordinates(0, 0);
		Scanner input = new Scanner(System.in);
		boolean condition = false;
		do {
			try {
				System.out.println(player.getName() + ", Please enter attack location like D3  ");
				String location = input.next();
				if (location.matches("^[a-jA-J][0-1]?[0-9]$")) {
					playerInputCoordinates = new Coordinates(getXLocation(location), getYLocation(location));
					condition = false;
				} else {
					System.out.println("Inavlid input...  ");

					condition = true;
				}
			} catch (Exception e) {
				if (e instanceof IllegalArgumentException) {
					System.out.println("Inavlid input...  ");
					condition = true;

				}
			}
		} while (condition);
		return playerInputCoordinates;
	}

	private int getXLocation(String userInput) {

		if (userInput == null) {
			throw new IllegalArgumentException("Null value.");
		}

		if (userInput.length() < 2) {
			throw new IllegalArgumentException("Invalid Value.");
		}

		int result = Integer.parseInt(userInput.substring(1)) - 1;

		if (result < 0 || result > GameConstant.OCCEAN_GRID_SQUARES_NUMBERS - 1) {
			throw new IllegalArgumentException("Out of boundries");
		}
		return result;

	}

	private int getYLocation(String yLocation) {
		if (yLocation == null || yLocation.length() == 0) {
			throw new IllegalArgumentException();
		}

		if (yLocation.charAt(0) == 'A') {
			return 0;
		} else if (yLocation.charAt(0) == 'B') {
			return 1;
		} else if (yLocation.charAt(0) == 'C') {
			return 2;
		} else if (yLocation.charAt(0) == 'D') {
			return 3;
		} else if (yLocation.charAt(0) == 'E') {
			return 4;
		} else if (yLocation.charAt(0) == 'F') {
			return 5;
		} else if (yLocation.charAt(0) == 'G') {
			return 6;
		} else if (yLocation.charAt(0) == 'H') {
			return 7;
		} else if (yLocation.charAt(0) == 'I') {
			return 8;
		} else if (yLocation.charAt(0) == 'J') {
			return 9;
		} else
			throw new IllegalArgumentException();
	}

	private void printOutHoles(Player player) {

		System.out.print("  ");
		// Draw Numbers in Grid
		for (int c = 1; c <= GameConstant.OCCEAN_GRID_SQUARES_NUMBERS; c++)
			System.out.print(" " + c + " ");

		for (int i = 0; i < GameConstant.OCCEAN_GRID_SQUARES_NUMBERS; i++) {
			System.out.println();
			for (int j = 0; j <= GameConstant.OCCEAN_GRID_SQUARES_LETTERS; j++) {
				// Draw Letters in Grid
				if (j == 0) {
					System.out.print((char) (65 + i) + " ");

					continue;
				}
//				if (player.getGridSquares()[j - 1][i].isBusy()) {
//					System.out.print(" █ " + player.getGridSquares()[j - 1][i]);
//				} else

				if (player.getGridSquares()[j - 1][i].isHit()) {
					System.out.print(" √ " + player.getGridSquares()[j - 1][i]);
				} else if (player.getGridSquares()[j - 1][i].isMiss()) {
					System.out.print(" ▓ " + player.getGridSquares()[j - 1][i]);
				} else {
					System.out.print(" ░ " + player.getGridSquares()[j - 1][i]);
				}

			}
			System.out.println();

		}
		System.out.println();
		System.out.println();

	}

	private void printGameStatistics(Player player1, Player player2) {
		System.out.println("Sink=" + player2.getSinkShipsNumber() + " , Hits=" + player1.getHits() + " , Miss="
				+ player1.getMisses());

		System.out.println("No of Plays:" + numberOfGamePlayAttacks);
	}

}
