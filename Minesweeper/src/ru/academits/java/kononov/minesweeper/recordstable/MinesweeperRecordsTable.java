package ru.academits.java.kononov.minesweeper.recordstable;

import ru.academits.java.kononov.minesweeper.dto.eventsdto.CheckGameResultForRecordEventData;
import ru.academits.java.kononov.minesweeper.dto.eventsdto.WriteRecordEventData;

public interface MinesweeperRecordsTable {
    void checkGameResultForRecord(CheckGameResultForRecordEventData checkGameResultForRecordEventData);

    void writeRecord(WriteRecordEventData writeRecordEventData);

    void sendRecordsLists();
}
