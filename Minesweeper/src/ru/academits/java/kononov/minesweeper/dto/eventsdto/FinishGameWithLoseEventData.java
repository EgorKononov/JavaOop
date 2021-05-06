package ru.academits.java.kononov.minesweeper.dto.eventsdto;

import ru.academits.java.kononov.minesweeper.dto.CellData;

import java.util.List;

public class FinishGameWithLoseEventData implements EventData {
    private final int row;
    private final int column;
    private final List<List<CellData>> cellLists;

    public FinishGameWithLoseEventData(int row, int column, List<List<CellData>> cellLists) {
        this.row = row;
        this.column = column;
        this.cellLists = cellLists;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public List<CellData> getNotOpenedNotMarkedMinedCells() {
        return cellLists.get(0);
    }

    public List<CellData> getWrongMarkedCells() {
        return cellLists.get(1);
    }
}
