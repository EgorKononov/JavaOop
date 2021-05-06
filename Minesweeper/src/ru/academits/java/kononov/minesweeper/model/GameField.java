package ru.academits.java.kononov.minesweeper.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameField {
    private final int rowsCount;
    private final int columnsCount;
    private final int minesCount;
    private final Cell[][] cells;
    private final List<Cell> minedCells = new ArrayList<>();
    private final Random random = new Random();

    public GameField(Difficulty difficulty) {
        rowsCount = difficulty.getGameFieldRowsCount();
        columnsCount = difficulty.getGameFieldColumnsCount();
        minesCount = difficulty.getMinesCount();
        cells = new Cell[rowsCount][columnsCount];
    }

    public Cell getCell(int row, int column) {
        return cells[row][column];
    }

    public List<Cell> getMinedCells() {
        return minedCells;
    }

    public void createGameField() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public void mineGameField(int openedCellRow, int openedCellColumn) {
        while (minedCells.size() != minesCount) {
            int row = random.nextInt(rowsCount);
            int column = random.nextInt(columnsCount);
            Cell cell = this.getCell(row, column);

            if (!(row == openedCellRow && column == openedCellColumn) && !cell.isMined()) {
                cell.setMined(true);
                minedCells.add(cell);
            }
        }
    }

    public int openNotMinedCell(int row, int column) {
        Cell cell = this.getCell(row, column);
        cell.setOpened(true);

        int adjacentMinedCellsCount = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (i == row && j == column
                        || i < 0 || j < 0 || i >= rowsCount || j >= columnsCount) {
                    continue;
                }

                if (cells[i][j].isMined()) {
                    ++adjacentMinedCellsCount;
                }
            }
        }

        cell.setAdjacentMinedCellsCount(adjacentMinedCellsCount);

        return adjacentMinedCellsCount;
    }
}
