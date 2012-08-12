package com.example.square_battle.geometry;

import java.util.ArrayList;

public class MapAnalyzer extends Map{
	public void MapAnalyzar(){
	}
	private ArrayList findedPosibleWays;
	private ArrayList findedWay;
	private void analyzeWays(int borderType){
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
			orientedList = currentCell.getCellOrientation();
			
		}
		return false;
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
