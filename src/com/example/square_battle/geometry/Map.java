package com.example.square_battle.geometry;

import android.annotation.SuppressLint;

@SuppressLint("ParserError") public class Map {
	private int mapType;
	private Cell[][] cells;
	public Map(){
		mapType = 7;
		cells = new Cell[7][7];
	}
	public void setBorder(int coll, int row, Orientation orientation) {
		
	}
	private Orientation isEdgeCell(int coll, int row){
		if (coll == mapType - 1 && row > 0 && row < mapType - 1){
			return Orientation.bot;
		}
		if (coll == 0 && row > 0 && row < mapType - 1){
			return Orientation.top;
		}
		if (row == mapType - 1  && coll > 0 && coll < mapType - 1){
			return Orientation.right;
		}
		if (row == 0  && coll > 0 && coll < mapType - 1){
			return Orientation.left;
		}
		return Orientation.none;
	}
	private DiagonalOrientation isAngleCell(int coll, int row){
		if (coll == 0  && row == 0 ){
			return DiagonalOrientation.topleft;
		}
		if (coll == 0  && row == mapType - 1 ){
			return DiagonalOrientation.botleft;
		}
		if (coll == mapType - 1  && row == 0 ){
			return DiagonalOrientation.topright;
		}
		if (coll == mapType - 1  && row == mapType - 1 ){
			return DiagonalOrientation.botright;
		}
		return DiagonalOrientation.none;
	}
}
