package com.guestline.game.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.guestline.game.GameConstant;
import com.guestline.game.GameConstant.SHIP_POSITION;
import com.guestline.game.dto.Coordinates;
import com.guestline.game.dto.Player;
import com.guestline.game.ships.Ship;
import com.guestline.game.ships.ShipFactory;
import com.guestline.game.ships.ShipImpl;

public class PlayerManager {
	private static Logger logger = LogManager.getLogger(PlayerManager.class);
	ShipFactory shipFactory = new ShipFactory();

	public void createShips(Player player) {
		List<Ship> playerShips = new ArrayList<Ship>();
		playerShips.add(shipFactory.getShip(GameConstant.SHIP_TYPE.BattleShip));
		playerShips.add(shipFactory.getShip(GameConstant.SHIP_TYPE.Destroyer));

		player.setShips(playerShips);
	}

	public void postionShips(Player player) {
		List<Ship> playerShips = player.getShips();

		for (Ship ship : playerShips) {

			boolean isValidPostion = false;
			int numberOfTrials = 0;
			do {
				GameConstant.SHIP_POSITION randomPosition = (new Random().nextInt() % 2 == 0)
						? GameConstant.SHIP_POSITION.VERTICAL
						: GameConstant.SHIP_POSITION.HORIZONTAL;
				Coordinates coordinates = getRandomCoordinates(randomPosition, ship.getNumberOfSquaresLength());
				numberOfTrials++;
				try {
					ship.setPosition(player, randomPosition, coordinates);
					isValidPostion = true;
//					logger.info(player.getName() + "'s " + ship.getName() + " is positioned " + randomPosition
//							+ " at x=" + coordinates.getX() + ",y=" + coordinates.getY());
				} catch (Exception e) {
					if (!(e instanceof IllegalArgumentException)) {
						throw e;
					}
				}

			} while (!(isValidPostion || numberOfTrials >= GameConstant.MAX_RETRIES_COUNT_TO_POSTION_SHIP));
			if (numberOfTrials >= GameConstant.MAX_RETRIES_COUNT_TO_POSTION_SHIP && !isValidPostion) {
				System.out.println("Internal Error occured, can't auto postion ship..");
				System.exit(1);
			}
		}
	}

	private Coordinates getRandomCoordinates(SHIP_POSITION randomPosition, int shipSize) {
		Coordinates rondomCoordinates = new Coordinates(0, 0);
		if (randomPosition == GameConstant.SHIP_POSITION.VERTICAL) {
			rondomCoordinates = new Coordinates(new Random().nextInt(GameConstant.OCCEAN_GRID_SQUARES_NUMBERS),
					new Random().nextInt(GameConstant.OCCEAN_GRID_SQUARES_LETTERS - shipSize));
		}

		else {
			rondomCoordinates = new Coordinates(
					new Random().nextInt(GameConstant.OCCEAN_GRID_SQUARES_NUMBERS - shipSize),
					new Random().nextInt(GameConstant.OCCEAN_GRID_SQUARES_LETTERS));
		}

		return rondomCoordinates;
	}
}
