package com.guestline.game.dto;

import java.util.List;

import com.guestline.game.GameConstant;
import com.guestline.game.ships.Ship;

public class Player {
	private String name;
	private int hits;
	private int misses;
	private List<Ship> ships;
	private GridSquare[][] gridSquares;

	public Player(String name) {
		super();
		this.name = name;
		gridSquares = createGridSquares();
	}

	public boolean isDefeat() {
		int shipsSinkCount = getSinkShipsNumber();
		if (shipsSinkCount == ships.size()) {
			return true;
		}

		return false;
	}

	public int getSinkShipsNumber() {
		int shipsSinkCount = 0;
		for (Ship ship : getShips()) {
			if (ship.isSink()) {
				shipsSinkCount++;
			}
		}
		return shipsSinkCount;
	}

	private GridSquare[][] createGridSquares() {
		GridSquare[][] gridSquares = new GridSquare[GameConstant.OCCEAN_GRID_SQUARES_NUMBERS][GameConstant.OCCEAN_GRID_SQUARES_LETTERS];

		for (int i = 0; i < GameConstant.OCCEAN_GRID_SQUARES_NUMBERS; i++) {
			for (int j = 0; j < GameConstant.OCCEAN_GRID_SQUARES_LETTERS; j++) {
				GridSquare oceanHole = new GridSquare(i, j, false);

				gridSquares[i][j] = oceanHole;
			}
		}
		return gridSquares;
	}

	public int getHits() {
		return hits;
	}

	public int getMisses() {
		return misses;
	}

	public String getName() {
		return name;
	}

	public List<Ship> getShips() {
		return ships;
	}

	public void setShips(List<Ship> ships) {
		this.ships = ships;
	}

	public void increaseMiss() {
		misses++;
	}

	public void increaseHits() {
		hits++;
	}

	public GridSquare[][] getGridSquares() {
		return gridSquares;
	}

}
