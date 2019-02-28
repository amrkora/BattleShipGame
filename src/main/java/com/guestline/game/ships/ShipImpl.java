package com.guestline.game.ships;

import java.util.ArrayList;
import java.util.List;

import com.guestline.game.GameConstant;
import com.guestline.game.GameConstant.SHIP_POSITION;
import com.guestline.game.dto.Coordinates;
import com.guestline.game.dto.GridSquare;
import com.guestline.game.dto.Player;

public abstract class ShipImpl implements Ship {
	private String name;
	private int numberOfSquaresLength;
	private boolean isSink;
	private List<GridSquare> shipLocationSquares = new ArrayList<GridSquare>();

	protected ShipImpl(int numberOfSquaresLength) {
		this.numberOfSquaresLength = numberOfSquaresLength;
	}

	public void setPosition(Player player, GameConstant.SHIP_POSITION position, Coordinates coordinates) {
		
		
		GridSquare[][] playerGridSquares = player.getGridSquares();
		if (coordinates.getX() < 0 || coordinates.getX() > GameConstant.OCCEAN_GRID_SQUARES_NUMBERS
				|| coordinates.getY() < 0 || coordinates.getY() > GameConstant.OCCEAN_GRID_SQUARES_LETTERS) {
			throw new IllegalArgumentException(
					"Out of boundries exception: x=" + coordinates.getX() + ", y=" + coordinates.getY());
		}

		if (position == SHIP_POSITION.VERTICAL) {
			if ((numberOfSquaresLength + coordinates.getY()) > GameConstant.OCCEAN_GRID_SQUARES_NUMBERS) {
				throw new IllegalArgumentException("Out of boundries exception: Location does not fit to the ship: x="
						+ coordinates.getX() + ", y=" + coordinates.getY());

			}
			

			for (int i = coordinates.getY(); i < numberOfSquaresLength + coordinates.getY(); i++) {

				if (playerGridSquares[coordinates.getX()][i].isBusy()) {
					throw new IllegalArgumentException(
							"Square is busy with another ship " + playerGridSquares[coordinates.getX()][i]);
				}
			}
			for (int i = coordinates.getY(); i < numberOfSquaresLength + coordinates.getY(); i++) {
				assignSquareForShip(playerGridSquares, new Coordinates(coordinates.getX(), i));

			}

		} else {
			
			if ((numberOfSquaresLength + coordinates.getX()) > GameConstant.OCCEAN_GRID_SQUARES_LETTERS) {
				throw new IllegalArgumentException("Out of boundries exception: Location does not fit to the ship: x="
						+ coordinates.getX() + ", y=" + coordinates.getY());

			}
			for (int i = coordinates.getX(); i < numberOfSquaresLength + coordinates.getX(); i++) {
				if (playerGridSquares[i][coordinates.getY()].isBusy()) {
					throw new IllegalArgumentException(
							"Square is busy with another ship " + playerGridSquares[i][coordinates.getY()]);
				}
			}
			for (int i = coordinates.getX(); i < numberOfSquaresLength + coordinates.getX(); i++) {

				assignSquareForShip(playerGridSquares, new Coordinates(i, coordinates.getY()));

			}
		}

	}

	

	private void assignSquareForShip(GridSquare[][] gridSquares, Coordinates coordinates) {
		GridSquare gridSquare = new GridSquare(coordinates.getX(), coordinates.getY(), true);
		gridSquares[coordinates.getX()][coordinates.getY()] = gridSquare;
		this.shipLocationSquares.add(gridSquare);
	}

	public List<GridSquare> getActiveSquares() {
		List<GridSquare> activeSquares = new ArrayList<>();
		for (GridSquare oceanSquare : shipLocationSquares) {
			if (!oceanSquare.isHit()) {
				activeSquares.add(oceanSquare);
			}
		}
		return activeSquares;
	}

	public void updateShipStatus() {
		if (getActiveSquares().size() == 0) {
			isSink = true;
		}
	}

	public int getNumberOfSquaresLength() {
		return numberOfSquaresLength;
	}

	public boolean isSink() {
		return isSink;
	}

	public List<GridSquare> getShipLocationSquares() {
		return shipLocationSquares;
	}

	public String getName() {
		return name;
	}

}
