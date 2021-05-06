package ru.academits.java.kononov.minesweeper.controller;

import ru.academits.java.kononov.minesweeper.model.Difficulty;
import ru.academits.java.kononov.minesweeper.recordstable.MinesweeperRecord;

import java.util.List;

public interface MinesweeperController {
    void startCurrentDifficultyNewGame();

    void startAnotherDifficultyNewGame(Difficulty difficulty);

    void pressOnCell(int row, int column);

    void openCell(int row, int column);

    void openAdjacentCells(int row, int column);

    void markOrUnmarkCell(int row, int column);

    void requestRecordOwnerName(int recordsCount, int minRecord);

    void writeRecord(String recordOwnerName);

    void requestRecordsLists();

    void showRecordsTable(List<List<MinesweeperRecord>> recordsLists, int recordsTableSize);

    void showAbout();

    void exitGame();
}
