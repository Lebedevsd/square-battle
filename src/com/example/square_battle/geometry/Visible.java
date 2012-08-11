package com.example.square_battle.geometry;

public class Visible {
	private boolean visible;

	public Visible(boolean visible){
		setVisible(visible);
	}
	
	public Visible(){
		setVisible(true);
	}
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	

}
