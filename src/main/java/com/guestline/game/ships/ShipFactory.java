package com.guestline.game.ships;

import com.guestline.game.GameConstant;

public class ShipFactory {

	public Ship getShip(GameConstant.SHIP_TYPE shipType) {

		if (shipType == GameConstant.SHIP_TYPE.BattleShip) {
			return new BattleShip();
		} else if (shipType == GameConstant.SHIP_TYPE.Destroyer) {
			return new Destroyer();
		} else {
			throw new IllegalArgumentException();

		}

	}
}
