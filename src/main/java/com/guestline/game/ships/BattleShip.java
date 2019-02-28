package com.guestline.game.ships;

public class BattleShip extends ShipImpl {
	public BattleShip() {
		super(5);
	}

	@Override
	public String getName() {
		return getClass().getSimpleName();
	}

}
