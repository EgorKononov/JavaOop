package ru.academits.java.kononov.minesweeper.dto.eventsdto;

public class MarkCellEventData implements EventData {
    private final int row;
    private final int column;
    private final int minesLeftCountIndicatorValue;

    public MarkCellEventData(int row, int column, int minesLeftCountIndicatorValue) {
        this.row = row;
        this.column = column;
        this.minesLeftCountIndicatorValue = minesLeftCountIndicatorValue;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getMinesLeftCountIndicatorValue() {
        return minesLeftCountIndicatorValue;
    }
}
