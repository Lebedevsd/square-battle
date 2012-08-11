package com.example.square_battle.geometry;

public class Border extends Visible {
	private int borderType;
	
	public Border(){
		setBorderType(0);
	}
	
	public int getBorderType() {
		return borderType;
	}

	public void setBorderType(int borderType) {
		this.borderType = borderType;
	}
	
}
