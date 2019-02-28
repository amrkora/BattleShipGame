package com.guestline.game.ships;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.guestline.game.GameConstant;
import com.guestline.game.dto.Coordinates;
import com.guestline.game.dto.Player;

@FixMethodOrder(MethodSorters.DEFAULT)
public class ShipImplTest {
	Player player = new Player("Test1");
	ShipFactory shipFactory = new ShipFactory();
	Ship battleShip = new BattleShip();
	Ship destroyer = new Destroyer();

	@Test
	public void createShip() {
		battleShip = shipFactory.getShip(GameConstant.SHIP_TYPE.BattleShip);
		assertEquals(5, battleShip.getNumberOfSquaresLength());

		destroyer = shipFactory.getShip(GameConstant.SHIP_TYPE.Destroyer);
		assertEquals(4, destroyer.getNumberOfSquaresLength());
	}

	@Test
	public void positionShip() {
		battleShip.setPosition(player, GameConstant.SHIP_POSITION.HORIZONTAL, new Coordinates(1, 1));
		assertEquals(5, battleShip.getActiveSquares().size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void positionShipInBusyLocation() {
		battleShip.setPosition(player, GameConstant.SHIP_POSITION.HORIZONTAL, new Coordinates(1, 1));
		destroyer.setPosition(player, GameConstant.SHIP_POSITION.HORIZONTAL, new Coordinates(1, 1));
	}

	@Test
	public void sinkShip() {
		assertEquals(false, battleShip.isSink());
	}
	
}
