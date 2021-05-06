package ru.academits.java.kononov.minesweeper.model;

public class Cell {
    private final int row;
    private final int column;

    private int adjacentMinedCellsCount;
    private boolean isOpened = false;
    private boolean isMined = false;
    private boolean isMarked = false;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getAdjacentMinedCellsCount() {
        assert this.isOpened;

        return adjacentMinedCellsCount;
    }

    public void setAdjacentMinedCellsCount(int adjacentMinedCellsCount) {
        this.adjacentMinedCellsCount = adjacentMinedCellsCount;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public boolean isMined() {
        return isMined;
    }

    public void setMined(boolean isMined) {
        this.isMined = isMined;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean isFlagged) {
        this.isMarked = isFlagged;
    }
}
