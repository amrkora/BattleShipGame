package com.guestline.game.ships;

public class Destroyer extends ShipImpl {
	public Destroyer() {
		super(4);
	}

	@Override
	public String getName() {
		return getClass().getSimpleName();
	}
}
