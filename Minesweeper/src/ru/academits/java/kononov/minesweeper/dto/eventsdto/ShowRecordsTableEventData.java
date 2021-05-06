package ru.academits.java.kononov.minesweeper.dto.eventsdto;

import ru.academits.java.kononov.minesweeper.recordstable.MinesweeperRecord;

import java.util.List;

public class ShowRecordsTableEventData implements EventData{
    private final List<List<MinesweeperRecord>> recordsLists;
    private final int recordsTableSize;

    public ShowRecordsTableEventData(List<List<MinesweeperRecord>> recordsLists, int recordsTableSize) {
        this.recordsLists = recordsLists;
        this.recordsTableSize = recordsTableSize;
    }

    public List<List<MinesweeperRecord>> getRecordsLists() {
        return recordsLists;
    }

    public int getRecordsTableSize() {
        return recordsTableSize;
    }
}
