package ru.academits.java.kononov.minesweeper.dto;

public class CellData {
    private final int row;
    private final int column;

    public CellData(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
