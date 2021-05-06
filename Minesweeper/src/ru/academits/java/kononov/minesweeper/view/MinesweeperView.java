package ru.academits.java.kononov.minesweeper.view;

import ru.academits.java.kononov.minesweeper.dto.CellData;
import ru.academits.java.kononov.minesweeper.dto.OpenedCellData;
import ru.academits.java.kononov.minesweeper.recordstable.MinesweeperRecord;

import java.util.List;

public interface MinesweeperView {
    void createAndShowGameGui(int rowsCount, int columnsCount, int minesCount);

    void startNewGame(int rowsCount, int columnsCount, int minesCount);

    void pressOnCell();

    void openNotMinedCell(List<OpenedCellData> openedCells);

    void markCell(int row, int column, int minesLeftCountIndicatorValue);

    void unmarkCell(int row, int column, int minesLeftCountIndicatorValue);

    void finishGameWithWin(List<CellData> notMarkedMinedCells);

    void finishGameWithLose(int row, int column, List<CellData> notOpenedNotMarkedMinedCells, List<CellData> wrongMarkedCells);

    void updateTimer(int timerValue);

    void requestRecordOwnerName();

    void sendRecordOwnerName(String recordOwnerName);

    void showRecordTable(List<List<MinesweeperRecord>> recordsLists, int recordsTableSize);

    void showAbout();

    void exitGame();
}
