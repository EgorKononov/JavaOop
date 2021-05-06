package ru.academits.java.kononov.minesweeper.model;

import ru.academits.java.kononov.minesweeper.dto.CellData;
import ru.academits.java.kononov.minesweeper.dto.OpenedCellData;
import ru.academits.java.kononov.minesweeper.dto.eventsdto.*;
import ru.academits.java.kononov.minesweeper.eventmanager.EventManager;
import ru.academits.java.kononov.minesweeper.eventmanager.ListenersType;
import ru.academits.java.kononov.minesweeper.eventmanager.events.*;
import ru.academits.java.kononov.minesweeper.recordstable.MinesweeperRecord;
import ru.academits.java.kononov.minesweeper.timer.MinesweeperTimer;

import java.util.*;

import static ru.academits.java.kononov.minesweeper.main.Constants.DATE_FORMAT;

public class BaseMinesweeperModel implements MinesweeperModel {
    private final EventManager eventManager;

    private MinesweeperTimer minesweeperTimer;
    private Difficulty currentDifficulty = Difficulty.BEGINNER;
    private boolean isGameStarted = false;
    private boolean isGameFinished = false;
    private GameField gameField;
    private List<Cell> markedCells;
    private int openedCellsToWinLeftCount;
    private int minesLeftCountIndicatorValue;
    private int gameResult;
    private String gameFinishDate;
    private int recordsCount;
    private int minRecord;
    private int rowsCount;
    private int columnsCount;

    public BaseMinesweeperModel(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    @Override
    public void setMinesweeperTimer(MinesweeperTimer minesweeperTimer) {
        this.minesweeperTimer = minesweeperTimer;
    }

    @Override
    public void createAndShowGameGui() {
        configureGameModel();

        eventManager.notify(ListenersType.GUI_LISTENERS, new CreateAndShowGameGuiEvent
                (new CreateGuiEventData(currentDifficulty.getGameFieldRowsCount(), currentDifficulty.getGameFieldColumnsCount(),
                        currentDifficulty.getMinesCount())));
    }

    private void configureGameModel() {
        gameField = new GameField(currentDifficulty);
        gameField.createGameField();
        rowsCount = currentDifficulty.getGameFieldRowsCount();
        columnsCount = currentDifficulty.getGameFieldColumnsCount();
        openedCellsToWinLeftCount = currentDifficulty.getOpenedCellsToWinCount();
        minesLeftCountIndicatorValue = currentDifficulty.getMinesCount();
        markedCells = new ArrayList<>();
    }

    @Override
    public void startCurrentDifficultyNewGame() {
        if (isGameStarted) {
            minesweeperTimer.stop();
        }

        isGameStarted = false;
        isGameFinished = false;

        configureGameModel();

        eventManager.notify(ListenersType.GUI_LISTENERS, new StartNewGameEvent
                (new CreateGuiEventData(currentDifficulty.getGameFieldRowsCount(), currentDifficulty.getGameFieldColumnsCount(),
                        currentDifficulty.getMinesCount())));
    }

    @Override
    public void startAnotherDifficultyNewGame(Difficulty difficulty) {
        currentDifficulty = difficulty;
        startCurrentDifficultyNewGame();
    }

    @Override
    public void pressOnCell(int row, int column) {
        Cell cell = gameField.getCell(row, column);

        if (!isGameFinished && !cell.isOpened() && !cell.isMarked()) {
            eventManager.notify(ListenersType.GUI_LISTENERS, new PressOnCellEvent());
        }
    }

    @Override
    public void openCell(int row, int column) {
        Cell cell = gameField.getCell(row, column);

        if (!isGameFinished && !cell.isMarked() && !cell.isOpened()) {
            if (!isGameStarted) {
                openFirstInTheGameCell(row, column);
            }

            if (!cell.isMined()) {
                openNotMinedCell(row, column);

                if (openedCellsToWinLeftCount == 0) {
                    finishGameWithWin();
                }
            } else {
                finishGameWithLose(cell);
            }
        }
    }

    private void openFirstInTheGameCell(int row, int column) {
        gameField.mineGameField(row, column);
        minesweeperTimer.start();
        isGameStarted = true;
    }

    private void openNotMinedCell(int row, int column) {
        int adjacentMinedCellsCount = gameField.openNotMinedCell(row, column);
        --openedCellsToWinLeftCount;

        OpenCellsEventData openCellsEventData = new OpenCellsEventData();
        openCellsEventData.addOpenedCell(new OpenedCellData(row, column, adjacentMinedCellsCount));

        if (adjacentMinedCellsCount == 0) {
            openCellsEventData.addOpenedCells(openCellWithNoAdjacentMinedCells(row, column));
        }

        eventManager.notify(ListenersType.GUI_LISTENERS, new OpenCellEvent(openCellsEventData));
    }

    private List<OpenedCellData> openCellWithNoAdjacentMinedCells(int row, int column) {
        List<OpenedCellData> openedCellsDataList = new ArrayList<>();

        List<Cell> cellsWithNoAdjacentMinedCells = new ArrayList<>();
        cellsWithNoAdjacentMinedCells.add(new Cell(row, column));

        for (ListIterator<Cell> iterator = cellsWithNoAdjacentMinedCells.listIterator();
             iterator.hasNext(); ) {
            Cell cellWithNoAdjacentMinedCells = iterator.next();
            int currentCellRow = cellWithNoAdjacentMinedCells.getRow();
            int currentCellColumn = cellWithNoAdjacentMinedCells.getColumn();

            for (int i = currentCellRow - 1; i <= currentCellRow + 1; i++) {
                for (int j = currentCellColumn - 1; j <= currentCellColumn + 1; j++) {
                    if (i == currentCellRow && j == currentCellColumn ||
                            i < 0 || j < 0 ||
                            i >= rowsCount || j >= columnsCount) {
                        continue;
                    }

                    Cell cell = gameField.getCell(i, j);

                    if (!cell.isOpened() && !cell.isMarked()) {
                        int adjacentMinedCellsCount = gameField.openNotMinedCell(i, j);
                        --openedCellsToWinLeftCount;
                        openedCellsDataList.add(new OpenedCellData(i, j, adjacentMinedCellsCount));

                        if (adjacentMinedCellsCount == 0) {
                            iterator.add(new Cell(i, j));
                            iterator.previous();
                        }
                    }
                }
            }
        }

        return openedCellsDataList;
    }

    @Override
    public void finishGameWithWin() {
        gameResult = minesweeperTimer.getTimerValue();
        minesweeperTimer.stop();
        Calendar calendar = new GregorianCalendar();
        gameFinishDate = DATE_FORMAT.format(calendar.getTime());
        isGameFinished = true;

        List<Cell> minedCells = gameField.getMinedCells();
        List<CellData> notMarkedMinedCells = new ArrayList<>();

        for (Cell minedCell : minedCells) {
            if (!minedCell.isMarked()) {
                notMarkedMinedCells.add(new CellData(minedCell.getRow(), minedCell.getColumn()));
            }
        }

        eventManager.notify(ListenersType.GUI_LISTENERS, new FinishGameWithWinEvent(
                new FinishGameWithWinEventData(notMarkedMinedCells)));

        eventManager.notify(ListenersType.RECORDS_TABLE_LISTENERS, new CheckGameResultForRecordEvent(
                new CheckGameResultForRecordEventData(currentDifficulty, gameResult)));
    }

    @Override
    public void finishGameWithLose(Cell cell) {
        minesweeperTimer.stop();
        isGameFinished = true;

        List<CellData> notOpenedNotMarkedMinedCells = getNotOpenedNotMarkedMinedCells(cell);
        List<CellData> wrongMarkedCells = getWrongMarkedCells();
        List<List<CellData>> cellLists = new ArrayList<>(Arrays.asList(notOpenedNotMarkedMinedCells,
                wrongMarkedCells));

        eventManager.notify(ListenersType.GUI_LISTENERS, new FinishGameWithLoseEvent(
                new FinishGameWithLoseEventData(cell.getRow(), cell.getColumn(), cellLists)));
    }

    private List<CellData> getNotOpenedNotMarkedMinedCells(Cell cell) {
        List<Cell> minedCells = gameField.getMinedCells();
        List<CellData> notOpenedNotMarkedMinedCells = new ArrayList<>();

        for (Cell minedCell : minedCells) {
            if (!minedCell.isMarked() && minedCell != cell) {
                notOpenedNotMarkedMinedCells.add(new CellData(minedCell.getRow(), minedCell.getColumn()));
            }
        }

        return notOpenedNotMarkedMinedCells;
    }

    private List<CellData> getWrongMarkedCells() {
        List<CellData> wrongMarkedCells = new ArrayList<>();

        for (Cell markedCell : markedCells) {
            if (!markedCell.isMined()) {
                wrongMarkedCells.add(new CellData(markedCell.getRow(), markedCell.getColumn()));
            }
        }

        return wrongMarkedCells;
    }

    @Override
    public void openAdjacentCells(int row, int column) {
        Cell cell = gameField.getCell(row, column);

        if (!isGameFinished && cell.isOpened()) {
            int adjacentMarkedCellsCount = 0;
            int notOpenedCellsCount = 0;

            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = column - 1; j <= column + 1; j++) {
                    if (i == row && j == column ||
                            i < 0 || j < 0 ||
                            i >= rowsCount || j >= columnsCount) {
                        continue;
                    }

                    Cell adjacentCell = gameField.getCell(i, j);

                    if (adjacentCell.isMarked()) {
                        ++adjacentMarkedCellsCount;
                    }

                    if (!adjacentCell.isOpened()) {
                        ++notOpenedCellsCount;
                    }
                }
            }

            if ((notOpenedCellsCount - adjacentMarkedCellsCount) != 0 &&
                    cell.getAdjacentMinedCellsCount() == adjacentMarkedCellsCount) {
                openCheckedCellAdjacentCells(row, column);
            }
        }
    }

    private void openCheckedCellAdjacentCells(int row, int column) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (i == row && j == column ||
                        i < 0 || j < 0 ||
                        i >= rowsCount || j >= columnsCount) {
                    continue;
                }

                Cell currentCell = gameField.getCell(i, j);

                if (!currentCell.isOpened() && !currentCell.isMarked()) {
                    openCell(i, j);
                }
            }
        }
    }

    @Override
    public void markOrUnmarkCell(int row, int column) {
        if (!isGameFinished) {
            Cell cell = gameField.getCell(row, column);

            if (!cell.isOpened()) {
                Event event;

                if (!cell.isMarked()) {
                    cell.setMarked(true);
                    markedCells.add(cell);
                    --minesLeftCountIndicatorValue;
                    event = new MarkCellEvent(new MarkCellEventData(row, column, minesLeftCountIndicatorValue));
                } else {
                    cell.setMarked(false);
                    markedCells.remove(cell);
                    ++minesLeftCountIndicatorValue;
                    event = new UnmarkCellEvent(new MarkCellEventData(row, column, minesLeftCountIndicatorValue));
                }

                eventManager.notify(ListenersType.GUI_LISTENERS, event);
            }
        }
    }

    @Override
    public void updateTimer(int timerValue) {
        eventManager.notify(ListenersType.GUI_LISTENERS, new UpdateTimerEvent(new UpdateTimerEvenData(timerValue)));
    }

    @Override
    public void requestRecordOwnerName(int recordsCount, int minRecord) {
        this.recordsCount = recordsCount;
        this.minRecord = minRecord;
        eventManager.notify(ListenersType.GUI_LISTENERS, new RequestRecordOwnerNameEvent());
    }

    @Override
    public void writeRecord(String recordOwnerName) {
        eventManager.notify(ListenersType.RECORDS_TABLE_LISTENERS, new WriteRecordEvent(
                new WriteRecordEventData(currentDifficulty, recordOwnerName, gameResult, gameFinishDate,
                        recordsCount, minRecord)));

        requestRecordsLists();
    }

    @Override
    public void requestRecordsLists() {
        eventManager.notify(ListenersType.RECORDS_TABLE_LISTENERS, new RequestRecordsListsEvent());
    }

    @Override
    public void showRecordsTable(List<List<MinesweeperRecord>> recordsLists, int recordsTableSize) {
        eventManager.notify(ListenersType.GUI_LISTENERS, new ShowRecordsTableEvent(
                new ShowRecordsTableEventData(recordsLists, recordsTableSize)));
    }

    @Override
    public void showAbout() {
        eventManager.notify(ListenersType.GUI_LISTENERS, new ShowAboutEvent());
    }

    @Override
    public void exitGame() {
        System.exit(0);
    }

    /**
     * Вспомогательный метод для отладки
     */
    public void showMinedCells() {
        List<CellData> minedCellsData = new ArrayList<>();
        List<Cell> minedCells = gameField.getMinedCells();

        for (Cell minedCell : minedCells) {
            minedCellsData.add(new CellData(minedCell.getRow(), minedCell.getColumn()));
        }

        eventManager.notify(ListenersType.GUI_LISTENERS, new ShowMinedCellsEvent(
                new ShowMinedCellsEventData(minedCellsData)));
    }
}
