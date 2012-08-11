package com.example.square_battle.geometry;

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
	public void setCellType(int cellType) {
		this.cellType = cellType;
	}
	public Border getRightBorder() {
		return rightBorder;
	}
	public void setRightBorder(Border rightBorder) {
		this.rightBorder = rightBorder;
	}
	public Border getLeftBorder() {
		return leftBorder;
	}
	public void setLeftBorder(Border leftBorder) {
		this.leftBorder = leftBorder;
	}
	public Border getBotBorder() {
		return botBorder;
	}
	public void setBotBorder(Border botBorder) {
		this.botBorder = botBorder;
	}
	public Border getTopBorder() {
		return topBorder;
	}
	public void setTopBorder(Border topBorder) {
		this.topBorder = topBorder;
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
