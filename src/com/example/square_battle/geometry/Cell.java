package com.example.square_battle.geometry;

import java.util.ArrayList;

public class Cell {
	private Border topBorder;
	private Border botBorder;
	private Border leftBorder;
	private Border rightBorder;
	private int cellType;
	public Cell(){
		setCellType(0);
	}
	public int getCellType() {
		return cellType;
	}
	public ArrayList getCellOrientation(){
		ArrayList messege = new ArrayList();
		if (this.getTopBorder().getBorderType() == 0)
			messege.add(Orientation.top);
		if (this.getBotBorder().getBorderType() == 0)
			messege.add(Orientation.bot);	
		if (this.getLeftBorder().getBorderType() == 0)
			messege.add(Orientation.left);
		if (this.getRightBorder().getBorderType() == 0)
			messege.add(Orientation.right);
		if (messege.size() == 0)
			messege.add(Orientation.none);
		return messege;
	}
	public void setCellType(int cellType) {
		this.cellType = cellType;
	}
	public Border getRightBorder() {
		return rightBorder;
	}
	public void setRightBorder(int rightBorder) {
		this.rightBorder.setBorderType(rightBorder);
	}
	public Border getLeftBorder() {
		return leftBorder;
	}
	public void setLeftBorder(int leftBorder) {
		this.leftBorder.setBorderType(leftBorder);
	}
	public Border getBotBorder() {
		return botBorder;
	}
	public void setBotBorder(int botBorder) {
		this.botBorder.setBorderType(botBorder);
	}
	public Border getTopBorder() {
		return topBorder;
	}
	public void setTopBorder(int topBorder) {
		this.topBorder.setBorderType(topBorder);
	}
	public int[] getBorders(){
		int[] borders = new int[4];
		borders[0] = getTopBorder().getBorderType();
		borders[1] = getLeftBorder().getBorderType();
		borders[2] = getBotBorder().getBorderType();
		borders[3] = getRightBorder().getBorderType();
		return borders;
	}
}
