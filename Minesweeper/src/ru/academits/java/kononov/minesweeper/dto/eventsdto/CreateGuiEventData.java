package ru.academits.java.kononov.minesweeper.dto.eventsdto;

public class CreateGuiEventData implements EventData {
    private final int rowsCount;
    private final int columnsCount;
    private final int minesCount;

    public CreateGuiEventData(int rowsCount, int columnsCount, int minesCount) {
        this.rowsCount = rowsCount;
        this.columnsCount = columnsCount;
        this.minesCount = minesCount;
    }

    public int getRowsCount() {
        return rowsCount;
    }

    public int getColumnsCount() {
        return columnsCount;
    }

    public int getMinesCount() {
        return minesCount;
    }
}
