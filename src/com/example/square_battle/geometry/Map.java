package com.example.square_battle.geometry;

import android.annotation.SuppressLint;

@SuppressLint({ "ParserError", "ParserError", "ParserError" }) public class Map {
	private int mapType;
	private Cell[][] cells;
	public Map(){
		mapType = 7;
		cells = new Cell[7][7];
	}
	public MapErrors setBorder(int coll, int row, Orientation orientation, int borderType) {
		MapErrors mapErrors = null;
		if ( isAngleCell(coll, row) !=  DiagonalOrientation.none){
			return setAngleBorder(orientation, isAngleCell(coll, row), borderType);
		}
		if ( isEdgeCell(coll , row) != Orientation.none){
			return setEdgeBorder(orientation, isEdgeCell(coll , row), coll, row, borderType);
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
				if (cells[0][0].getBotBorder().getBorderType() != 0 && 
					cells[1][0].getTopBorder().getBorderType() != 0){
					cells[0][0].setBotBorder(new Border(borderType));
					cells[1][0].setTopBorder(new Border(borderType));
					return MapErrors.none;
				}else return MapErrors.alreadySetted;
			}
			if (orientation == Orientation.left){
				return MapErrors.wrongPosition;
			}
			if (orientation == Orientation.right){
				if (cells[0][0].getRightBorder().getBorderType() != 0 &&
					cells[1][0].getLeftBorder().getBorderType() != 0){
					cells[0][0].setRightBorder(new Border(borderType));
					cells[0][1].setLeftBorder(new Border(borderType));
					return MapErrors.none;
				}else return MapErrors.alreadySetted;
			}
		}
		if (diagonalOrientation == DiagonalOrientation.topright){
			if (orientation == Orientation.top ){
				return MapErrors.wrongPosition;
			}
			if (orientation == Orientation.bot){
				if (cells[0][mapType-1].getBotBorder().getBorderType() != 0 && 
					cells[1][mapType-1].getTopBorder().getBorderType() != 0){
					cells[0][mapType-1].setBotBorder(new Border(borderType));
					cells[1][mapType-1].setTopBorder(new Border(borderType));
					return MapErrors.none;
				}else return MapErrors.alreadySetted;
			}
			if (orientation == Orientation.left){
				if (cells[0][mapType-1].getLeftBorder().getBorderType() != 0 && 
					cells[0][mapType-2].getRightBorder().getBorderType() != 0){
					cells[0][mapType-1].setLeftBorder(new Border(borderType));
					cells[0][mapType-2].setRightBorder(new Border(borderType));
					return MapErrors.none;
				}else return MapErrors.alreadySetted;
			}
			if (orientation == Orientation.right){
				return MapErrors.wrongPosition;
			}
		}
		if (diagonalOrientation == DiagonalOrientation.botright){
			if (orientation == Orientation.top ){
				if (cells[mapType-2][mapType-1].getBotBorder().getBorderType() != 0 && 
					cells[mapType-1][mapType-1].getTopBorder().getBorderType() != 0){
					cells[mapType-2][mapType-1].setBotBorder(new Border(borderType));
					cells[mapType-1][mapType-1].setTopBorder(new Border(borderType));
					return MapErrors.none;
				}else return MapErrors.alreadySetted;
			}
			if (orientation == Orientation.bot){
				return MapErrors.wrongPosition;
			}
			if (orientation == Orientation.left){
				if (cells[mapType-1][mapType-1].getLeftBorder().getBorderType() != 0 && 
					cells[mapType-1][mapType-2].getRightBorder().getBorderType() != 0){
					cells[mapType-1][mapType-1].setLeftBorder(new Border(borderType));
					cells[mapType-1][mapType-2].setRightBorder(new Border(borderType));
					return MapErrors.none;
				}else return MapErrors.alreadySetted;
			}
			if (orientation == Orientation.right){
				return MapErrors.wrongPosition;
			}
		}
		if (diagonalOrientation == DiagonalOrientation.botleft){
			if (orientation == Orientation.top ){
				if (cells[mapType-2][0].getBotBorder().getBorderType() != 0 && 
					cells[mapType-1][0].getTopBorder().getBorderType() != 0){
					cells[mapType-2][0].setBotBorder(new Border(borderType));
					cells[mapType-1][0].setTopBorder(new Border(borderType));
					return MapErrors.none;
				}else return MapErrors.alreadySetted;
			}
			if (orientation == Orientation.bot){
				return MapErrors.wrongPosition;
			}
			if (orientation == Orientation.left){
				return MapErrors.wrongPosition;
			}
			if (orientation == Orientation.right){
				if (cells[mapType-1][0].getLeftBorder().getBorderType() != 0 && 
					cells[mapType-1][1].getRightBorder().getBorderType() != 0){
					cells[mapType-1][0].setLeftBorder(new Border(borderType));
					cells[mapType-1][1].setRightBorder(new Border(borderType));
					return MapErrors.none;
				}else return MapErrors.alreadySetted;
			}
		}
		return MapErrors.notInTheMap;
	}
	private MapErrors setEdgeBorder(Orientation orientation ,Orientation cellOrientation, int coll, int row, int borderType){
		if (cellOrientation == Orientation.bot){
			if (orientation == Orientation.top){
				if (cells[row][coll].getTopBorder().getBorderType() != 0 && 
				    cells[row - 1][coll].getBotBorder().getBorderType() != 0){
					cells[row][coll].setTopBorder(new Border(borderType));
					cells[row - 1][coll].setBotBorder(new Border(borderType));
					return MapErrors.none;	
				}else return MapErrors.alreadySetted;	
			}
			if (orientation == Orientation.right){
				if (cells[row][coll].getRightBorder().getBorderType() != 0 && 
				    cells[row][coll + 1].getLeftBorder().getBorderType() != 0){
					cells[row][coll].setRightBorder(new Border(borderType));
					cells[row][coll + 1].setLeftBorder(new Border(borderType));
					return MapErrors.none;	
				}else return MapErrors.alreadySetted;	
			}
			if (orientation == Orientation.left){
				if (cells[row][coll].getLeftBorder().getBorderType() != 0 && 
				    cells[row][coll - 1].getRightBorder().getBorderType() != 0){
					cells[row][coll].setLeftBorder(new Border(borderType));
					cells[row][coll - 1].setRightBorder(new Border(borderType));
					return MapErrors.none;	
				}else return MapErrors.alreadySetted;	
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
				if (cells[row][coll].getRightBorder().getBorderType() != 0 && 
				    cells[row][coll + 1].getLeftBorder().getBorderType() != 0){
					cells[row][coll].setRightBorder(new Border(borderType));
					cells[row][coll + 1].setLeftBorder(new Border(borderType));
					return MapErrors.none;	
				}else return MapErrors.alreadySetted;	
			}
			if (orientation == Orientation.left){
				if (cells[row][coll].getLeftBorder().getBorderType() != 0 && 
				    cells[row][coll - 1].getRightBorder().getBorderType() != 0){
					cells[row][coll].setLeftBorder(new Border(borderType));
					cells[row][coll - 1].setRightBorder(new Border(borderType));
					return MapErrors.none;	
				}else return MapErrors.alreadySetted;	
			}
			if (orientation == Orientation.bot){
				if (cells[row][coll].getBotBorder().getBorderType() != 0 && 
					cells[row + 1][coll].getTopBorder().getBorderType() != 0){
					cells[row][coll].setBotBorder(new Border(borderType));
					cells[row + 1][coll].setTopBorder(new Border(borderType));
					return MapErrors.none;	
				}else return MapErrors.alreadySetted;	
			}
		}
		if (cellOrientation == Orientation.left){
			if (orientation == Orientation.top){
				if (cells[row][coll].getTopBorder().getBorderType() != 0 && 
						cells[row - 1][coll].getBotBorder().getBorderType() != 0){
					cells[row][coll].setTopBorder(new Border(borderType));
					cells[row - 1][coll].setBotBorder(new Border(borderType));
					return MapErrors.none;	
				}else return MapErrors.alreadySetted;
			}
			if (orientation == Orientation.right){
				if (cells[row][coll].getRightBorder().getBorderType() != 0 && 
				    cells[row][coll + 1].getLeftBorder().getBorderType() != 0){
					cells[row][coll].setRightBorder(new Border(borderType));
					cells[row][coll + 1].setLeftBorder(new Border(borderType));
					return MapErrors.none;	
				}else return MapErrors.alreadySetted;	
			}
			if (orientation == Orientation.left){
				return MapErrors.wrongPosition;	
			}
			if (orientation == Orientation.bot){
				if (cells[row][coll].getBotBorder().getBorderType() != 0 && 
					cells[row + 1][coll].getTopBorder().getBorderType() != 0){
					cells[row][coll].setBotBorder(new Border(borderType));
					cells[row + 1][coll].setTopBorder(new Border(borderType));
					return MapErrors.none;	
				}else return MapErrors.alreadySetted;	
			}
		}
		if (cellOrientation == Orientation.right){
			if (orientation == Orientation.top){
				if (cells[row][coll].getTopBorder().getBorderType() != 0 && 
						cells[row - 1][coll].getBotBorder().getBorderType() != 0){
					cells[row][coll].setTopBorder(new Border(borderType));
					cells[row - 1][coll].setBotBorder(new Border(borderType));
					return MapErrors.none;	
				}else return MapErrors.alreadySetted;
			}
			if (orientation == Orientation.right){
				return MapErrors.wrongPosition;	
			}
			if (orientation == Orientation.left){
				if (cells[row][coll].getLeftBorder().getBorderType() != 0 && 
				    cells[row][coll - 1].getRightBorder().getBorderType() != 0){
					cells[row][coll].setLeftBorder(new Border(borderType));
					cells[row][coll - 1].setRightBorder(new Border(borderType));
					return MapErrors.none;	
				}else return MapErrors.alreadySetted;		
			}
			if (orientation == Orientation.bot){
				if (cells[row][coll].getBotBorder().getBorderType() != 0 && 
					cells[row + 1][coll].getTopBorder().getBorderType() != 0){
					cells[row][coll].setBotBorder(new Border(borderType));
					cells[row + 1][coll].setTopBorder(new Border(borderType));
					return MapErrors.none;	
				}else return MapErrors.alreadySetted;	
			}
		}
		return MapErrors.notInTheMap;
	}
}
