package ru.academits.java.kononov.minesweeper.controller;

import ru.academits.java.kononov.minesweeper.model.Difficulty;
import ru.academits.java.kononov.minesweeper.model.MinesweeperModel;
import ru.academits.java.kononov.minesweeper.recordstable.MinesweeperRecord;

import java.util.List;

public class BaseMinesweeperController implements MinesweeperController {
    private final MinesweeperModel minesweeperModel;

    public BaseMinesweeperController(MinesweeperModel minesweeperModel) {
        this.minesweeperModel = minesweeperModel;
    }

    @Override
    public void startCurrentDifficultyNewGame() {
        minesweeperModel.startCurrentDifficultyNewGame();
    }

    @Override
    public void startAnotherDifficultyNewGame(Difficulty difficulty) {
        minesweeperModel.startAnotherDifficultyNewGame(difficulty);
    }

    @Override
    public void pressOnCell(int row, int column) {
        minesweeperModel.pressOnCell(row, column);
    }

    @Override
    public void openCell(int row, int column) {
        minesweeperModel.openCell(row, column);
    }

    @Override
    public void openAdjacentCells(int row, int column) {
        minesweeperModel.openAdjacentCells(row, column);
    }

    @Override
    public void markOrUnmarkCell(int row, int column) {
        minesweeperModel.markOrUnmarkCell(row, column);
    }

    @Override
    public void requestRecordOwnerName(int recordsCount, int minRecord) {
        minesweeperModel.requestRecordOwnerName(recordsCount, minRecord);
    }

    @Override
    public void writeRecord(String recordOwnerName) {
        minesweeperModel.writeRecord(recordOwnerName);
    }

    @Override
    public void requestRecordsLists() {
        minesweeperModel.requestRecordsLists();
    }

    @Override
    public void showRecordsTable(List<List<MinesweeperRecord>> recordsLists, int recordsTableSize) {
        minesweeperModel.showRecordsTable(recordsLists, recordsTableSize);
    }

    @Override
    public void showAbout() {
        minesweeperModel.showAbout();
    }

    @Override
    public void exitGame() {
        minesweeperModel.exitGame();
    }
}
