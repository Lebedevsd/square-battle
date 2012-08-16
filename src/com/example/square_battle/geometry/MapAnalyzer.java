package com.example.square_battle.geometry;

import java.util.ArrayList;

public class MapAnalyzer extends Map{
	public void MapAnalyzar(){
	}
	private ArrayList findedPosibleWays;
	private ArrayList<Cell> findedWay;
	public boolean updateWays(int borderType){
		analyzeWays();
		Cell currentCell;
		int count = findedWay.size();
		if (count != 0){
			for (int i = 0; i <count ; i ++){
				currentCell = findedWay.get(i);
				currentCell.setCellType(borderType);
				if (currentCell.getBotBorder().getBorderType() != 0)
					currentCell.setBotBorder(borderType);
				if (currentCell.getLeftBorder().getBorderType() != 0)
					currentCell.setLeftBorder(borderType);
				if (currentCell.getRightBorder().getBorderType() != 0)
					currentCell.setRightBorder(borderType);
				if (currentCell.getTopBorder().getBorderType() != 0)
					currentCell.setTopBorder(borderType);
			}
		}
		findedWay.clear();
		return true;
	}
	private void analyzeWays(){
		Cell currentCell;
		int i,j;
		for(int iter = 0; iter < findedPosibleWays.size(); iter+=3){
			currentCell = (Cell) findedPosibleWays.get(iter);
			i = (Integer) findedPosibleWays.get(iter + 1);
			j = (Integer) findedPosibleWays.get(iter + 2);
			if (checkWayFor(currentCell, i, j))
				break;
		}
	}
	private boolean checkWayFor(Cell currentCell, int i, int j){
		findedWay.clear();
		Orientation cellOrientation;
		ArrayList orientedList;
		orientedList = currentCell.getCellOrientation();
		if (orientedList.get(0) == Orientation.none){
			findedWay.add(currentCell);
			return true;
		}else {
			findedWay.add(currentCell);
			cellOrientation = (Orientation)orientedList.get(0);
			checkOpositOrientation(cellOrientation,i,j);
		}
		while(true){
			orientedList.clear();
			currentCell = cells[i][j];
			orientedList = currentCell.getCellOrientation();
			if (orientedList.size() == 2){
				if ((Orientation)orientedList.get(0) == cellOrientation){
					findedWay.add(currentCell);
					cellOrientation = (Orientation)orientedList.get(1);
					checkOpositOrientation(cellOrientation,i,j);
				}else{
					findedWay.add(currentCell);
					cellOrientation = (Orientation)orientedList.get(0);
					checkOpositOrientation(cellOrientation,i,j);
				}
			}else if (orientedList.size() == 1){
				if ((Orientation)orientedList.get(0) == cellOrientation){
					findedWay.add(currentCell);
					orientedList.clear();
					return true;
				}else return false;
			}
		}
	}
	private boolean checkOpositOrientation(Orientation cellOrientation, int i, int j){
		if (cellOrientation == Orientation.bot){
			cellOrientation=Orientation.top;
			i --;
			return true;
		}
		if (cellOrientation == Orientation.top){
			cellOrientation=Orientation.bot;
			i ++;
			return true;
		}
		if (cellOrientation == Orientation.left){
			cellOrientation=Orientation.right;
			j ++;
			return true;
		}
		if (cellOrientation == Orientation.right){
			cellOrientation=Orientation.left;
			j --;
			return true;
		}
		return false;
	}
	private void findWays(){
		findedPosibleWays.clear();
		int count;
		for (int i = 0; i < mapType; i++){
			for (int j = 0; j < mapType; j++){
				count = 0;
				if (cells[i][j].getCellType() == 0 ){
					if (cells[i][j].getBotBorder().getBorderType() != 0)
						count ++;
					if (cells[i][j].getTopBorder().getBorderType() != 0)
						count ++;
					if (cells[i][j].getLeftBorder().getBorderType() != 0)
						count ++;
					if (cells[i][j].getRightBorder().getBorderType() != 0)
						count ++;
					if (count >= 3){
						findedPosibleWays.add(cells[i][j]);
						findedPosibleWays.add(i);
						findedPosibleWays.add(j);
					}
				}
			}
		}
	}
}
