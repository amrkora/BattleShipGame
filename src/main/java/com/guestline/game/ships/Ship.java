package com.guestline.game.ships;

import java.util.List;

import com.guestline.game.GameConstant;
import com.guestline.game.dto.Coordinates;
import com.guestline.game.dto.GridSquare;
import com.guestline.game.dto.Player;

public interface Ship {
	public void setPosition(Player player, GameConstant.SHIP_POSITION position, Coordinates coordinates);

	public List<GridSquare> getActiveSquares();

	public void updateShipStatus();

	public String getName();

	public int getNumberOfSquaresLength();

	public boolean isSink();

}
