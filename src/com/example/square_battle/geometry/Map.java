package com.example.square_battle.geometry;

import android.annotation.SuppressLint;

@SuppressLint({ "ParserError", "ParserError", "ParserError", "ParserError" }) public class Map {
	protected int mapType;
	protected Cell[][] cells;
	public int getMapType(){
		return mapType;
	}
	public Cell[][] getCells(){
		Cell[][] tmp = new Cell[mapType][mapType];
		for (int i = 0; i < mapType ; i++){
			for (int j = 0; j < mapType ; j++){
				tmp[i][j].setCellType(cells[i][j].getCellType());
				tmp[i][j].setBotBorder(cells[i][j].getBotBorder().getBorderType());
				tmp[i][j].setTopBorder(cells[i][j].getTopBorder().getBorderType());
				tmp[i][j].setLeftBorder(cells[i][j].getLeftBorder().getBorderType());
				tmp[i][j].setRightBorder(cells[i][j].getRightBorder().getBorderType());
			}
		}
		return tmp;
	}
	public Map(){
		mapType = 7;
		cells = new Cell[7][7];
		for (int i = 0 ; i < 7 ; i++ ){
			cells[0][i].setTopBorder(-1);
			cells[mapType - 1][i].setBotBorder(-1);
			cells[i][0].setLeftBorder(-1);
			cells[i][mapType - 1].setRightBorder(-1);
		}
	}
	public MapErrors setBorder(int coll, int row, Orientation orientation, int borderType) {
		MapErrors mapErrors = null;
		if ( isAngleCell(coll, row) !=  DiagonalOrientation.none){
			return setAngleBorder(orientation, isAngleCell(coll, row), borderType);
		}
		if ( isEdgeCell(coll , row) != Orientation.none){
			return setEdgeBorder(orientation, isEdgeCell(coll , row), coll, row, borderType);
		}
		if ( isInMiddle(coll, row) ){
			return setMiddleBorder(orientation, coll, row, borderType);
		}
		return mapErrors.notSetted;
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
	private boolean isInMiddle(int coll, int row){
		if(coll >0 && coll < mapType - 1 && row > 0 && row < mapType - 1)
			return true;
		return false;
	}
	private MapErrors setAngleBorder(Orientation orientation ,DiagonalOrientation diagonalOrientation, int borderType){
		if (diagonalOrientation == DiagonalOrientation.topleft){
			if (orientation == Orientation.top ){
				return MapErrors.wrongPosition;
			}
			if (orientation == Orientation.bot){
				return checkAndSetBot(orientation, 0, 0, borderType);
			}
			if (orientation == Orientation.left){
				return MapErrors.wrongPosition;
			}
			if (orientation == Orientation.right){
				return checkAndSetRight(orientation, 0, 0, borderType);
			}
		}
		if (diagonalOrientation == DiagonalOrientation.topright){
			if (orientation == Orientation.top ){
				return MapErrors.wrongPosition;
			}
			if (orientation == Orientation.bot){
				return checkAndSetBot(orientation, mapType-1, 0, borderType);
			}
			if (orientation == Orientation.left){
				return checkAndSetLeft(orientation, mapType-1, 0, borderType);
			}
			if (orientation == Orientation.right){
				return MapErrors.wrongPosition;
			}
		}
		if (diagonalOrientation == DiagonalOrientation.botright){
			if (orientation == Orientation.top ){
				return checkAndSetTop(orientation, mapType-1, mapType-1, borderType);
			}
			if (orientation == Orientation.bot){
				return MapErrors.wrongPosition;
			}
			if (orientation == Orientation.left){
				return checkAndSetLeft(orientation, mapType-1, mapType-1, borderType);
			}
			if (orientation == Orientation.right){
				return MapErrors.wrongPosition;
			}
		}
		if (diagonalOrientation == DiagonalOrientation.botleft){
			if (orientation == Orientation.top ){
				return checkAndSetTop(orientation, 0, mapType-1, borderType);
			}
			if (orientation == Orientation.bot){
				return MapErrors.wrongPosition;
			}
			if (orientation == Orientation.left){
				return MapErrors.wrongPosition;
			}
			if (orientation == Orientation.right){
				return checkAndSetRight(orientation, 0, mapType-1, borderType);
			}
		}
		return MapErrors.notInTheMap;
	}
	private MapErrors setEdgeBorder(Orientation orientation ,Orientation cellOrientation, int coll, int row, int borderType){
		if (cellOrientation == Orientation.bot){
			if (orientation == Orientation.top){
				return checkAndSetTop(orientation, coll, row, borderType);	
			}
			if (orientation == Orientation.right){
				return checkAndSetRight(orientation, coll, row, borderType);	
			}
			if (orientation == Orientation.left){
				return checkAndSetLeft(orientation, coll, row, borderType);	
			}
			if (orientation == Orientation.bot){
				return MapErrors.wrongPosition;
			}
		}
		if (cellOrientation == Orientation.top){
			if (orientation == Orientation.top){
				return MapErrors.wrongPosition;
			}
			if (orientation == Orientation.right){
				return checkAndSetRight(orientation, coll, row, borderType);	
			}
			if (orientation == Orientation.left){
				return checkAndSetLeft(orientation, coll, row, borderType);	
			}
			if (orientation == Orientation.bot){
				return checkAndSetBot(orientation, coll, row, borderType);	
			}
		}
		if (cellOrientation == Orientation.left){
			if (orientation == Orientation.top){
				return checkAndSetTop(orientation, coll, row, borderType);
			}
			if (orientation == Orientation.right){
				return checkAndSetRight(orientation, coll, row, borderType);	
			}
			if (orientation == Orientation.left){
				return MapErrors.wrongPosition;	
			}
			if (orientation == Orientation.bot){
				return checkAndSetBot(orientation, coll, row, borderType);	
			}
		}
		if (cellOrientation == Orientation.right){
			if (orientation == Orientation.top){
				return checkAndSetTop(orientation, coll, row, borderType);
			}
			if (orientation == Orientation.right){
				return MapErrors.wrongPosition;	
			}
			if (orientation == Orientation.left){
				return checkAndSetLeft(orientation, coll, row, borderType);		
			}
			if (orientation == Orientation.bot){
				return checkAndSetBot(orientation, coll, row, borderType);	
			}
		}
		return MapErrors.notInTheMap;
	}
	private MapErrors setMiddleBorder(Orientation orientation, int coll, int row, int borderType){
		if (orientation == Orientation.top){
			return checkAndSetTop(orientation, coll, row, borderType);	
		}
		if (orientation == Orientation.right){
			return checkAndSetRight(orientation, coll, row, borderType);	
		}
		if (orientation == Orientation.left){
			return checkAndSetLeft(orientation, coll, row, borderType);	
		}
		if (orientation == Orientation.bot){
			return checkAndSetBot(orientation, coll, row, borderType);	
		}
		return MapErrors.notInTheMap;
	}
	private MapErrors checkAndSetTop(Orientation orientation, int coll, int row, int borderType){
		if (cells[row][coll].getCellType() == 0){
			if (cells[row][coll].getTopBorder().getBorderType() != 0 && 
				cells[row - 1][coll].getBotBorder().getBorderType() != 0){
				cells[row][coll].setTopBorder(borderType);
				cells[row - 1][coll].setBotBorder(borderType);
				return MapErrors.none;	
			}else return MapErrors.alreadySetted;
		}else return MapErrors.notInUse;
	}
	private MapErrors checkAndSetBot(Orientation orientation, int coll, int row, int borderType){
		if (cells[row][coll].getCellType() == 0){
			if (cells[row][coll].getBotBorder().getBorderType() != 0 && 
				cells[row + 1][coll].getTopBorder().getBorderType() != 0){
				cells[row][coll].setBotBorder(borderType);
				cells[row + 1][coll].setTopBorder(borderType);
				return MapErrors.none;	
			}else return MapErrors.alreadySetted;	
		}else return MapErrors.notInUse;
	}
	private MapErrors checkAndSetLeft(Orientation orientation, int coll, int row, int borderType){
		if (cells[row][coll].getCellType() == 0){	
			if (cells[row][coll].getLeftBorder().getBorderType() != 0 && 
				cells[row][coll - 1].getRightBorder().getBorderType() != 0){
				cells[row][coll].setLeftBorder(borderType);
				cells[row][coll - 1].setRightBorder(borderType);
				return MapErrors.none;	
			}else return MapErrors.alreadySetted;
		}else return MapErrors.notInUse;
	}
	private MapErrors checkAndSetRight(Orientation orientation, int coll, int row, int borderType){
		if (cells[row][coll].getCellType() == 0){	
			if (cells[row][coll].getRightBorder().getBorderType() != 0 && 
			    cells[row][coll + 1].getLeftBorder().getBorderType() != 0){
				cells[row][coll].setRightBorder(borderType);
				cells[row][coll + 1].setLeftBorder(borderType);
				return MapErrors.none;	
			}else return MapErrors.alreadySetted;
		}else return MapErrors.notInUse;
	}
}
