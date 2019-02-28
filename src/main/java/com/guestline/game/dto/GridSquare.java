package com.guestline.game.dto;

public class GridSquare extends Coordinates {

	private boolean isBusy;
	private boolean isHit;
	private boolean isMiss;
	
	public GridSquare(int x, int y, boolean isBusy) {
		super(x, y);
		this.isBusy = isBusy;
		
	}

	public boolean isBusy() {
		return isBusy;
	}

	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}

	public boolean isHit() {
		return isHit;
	}

	public void setHit(boolean isHit) {
		this.isHit = isHit;
	}

	
	public boolean isMiss() {
		return isMiss;
	}

	public void setMiss(boolean isMiss) {
		this.isMiss = isMiss;
	}
	

	@Override
	public String toString() {
		return "";// [B=" + isBusy + ", X=" + getX() + ", Y=" + getY() + "]";
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		GridSquare gridSquare = (GridSquare) object;

		return this.getX() == gridSquare.getX() && this.getY() == gridSquare.getY();
	}

}
