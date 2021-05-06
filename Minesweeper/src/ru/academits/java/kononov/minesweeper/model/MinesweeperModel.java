package ru.academits.java.kononov.minesweeper.model;

import ru.academits.java.kononov.minesweeper.recordstable.MinesweeperRecord;
import ru.academits.java.kononov.minesweeper.timer.MinesweeperTimer;

import java.util.List;

public interface MinesweeperModel {
    void setMinesweeperTimer(MinesweeperTimer minesweeperTimer);

    void createAndShowGameGui();

    void startCurrentDifficultyNewGame();

    void startAnotherDifficultyNewGame(Difficulty difficulty);

    void pressOnCell(int row, int column);

    void openCell(int row, int column);

    void finishGameWithWin();

    void finishGameWithLose(Cell cell);

    void openAdjacentCells(int row, int column);

    void markOrUnmarkCell(int row, int column);

    void updateTimer(int timerValue);

    void requestRecordOwnerName(int recordsCount, int minRecord);

    void writeRecord(String recordOwnerName);

    void requestRecordsLists();

    void showRecordsTable(List<List<MinesweeperRecord>> recordsLists, int recordsTableSize);

    void showAbout();

    void exitGame();
}
