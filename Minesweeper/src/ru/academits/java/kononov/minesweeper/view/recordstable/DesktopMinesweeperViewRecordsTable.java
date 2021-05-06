package ru.academits.java.kononov.minesweeper.view.recordstable;

import ru.academits.java.kononov.minesweeper.recordstable.MinesweeperRecord;

import javax.swing.*;
import java.util.List;

public interface DesktopMinesweeperViewRecordsTable {
    JPanel getRecordsTable(List<List<MinesweeperRecord>> recordsLists, int recordsTableSize);
}
