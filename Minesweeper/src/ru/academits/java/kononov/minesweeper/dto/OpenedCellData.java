package ru.academits.java.kononov.minesweeper.dto;

public class OpenedCellData {
    private final int row;
    private final int column;
    private final int adjacentMinedCellsCount;

    public OpenedCellData(int row, int column, int adjacentMinedCellsCount) {
        this.row = row;
        this.column = column;
        this.adjacentMinedCellsCount = adjacentMinedCellsCount;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getAdjacentMinedCellsCount() {
        return adjacentMinedCellsCount;
    }
}
