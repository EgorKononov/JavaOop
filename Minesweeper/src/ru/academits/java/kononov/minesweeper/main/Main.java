package ru.academits.java.kononov.minesweeper.main;

import ru.academits.java.kononov.minesweeper.controller.BaseMinesweeperController;
import ru.academits.java.kononov.minesweeper.controller.MinesweeperController;
import ru.academits.java.kononov.minesweeper.eventmanager.BaseEventManager;
import ru.academits.java.kononov.minesweeper.eventmanager.EventManager;
import ru.academits.java.kononov.minesweeper.eventmanager.Listener;
import ru.academits.java.kononov.minesweeper.eventmanager.ListenersType;
import ru.academits.java.kononov.minesweeper.model.BaseMinesweeperModel;
import ru.academits.java.kononov.minesweeper.model.MinesweeperModel;
import ru.academits.java.kononov.minesweeper.recordstable.FileMinesweeperRecordsTable;
import ru.academits.java.kononov.minesweeper.recordstable.MinesweeperRecordsTable;
import ru.academits.java.kononov.minesweeper.timer.BaseMinesweeperTimer;
import ru.academits.java.kononov.minesweeper.view.DesktopMinesweeperView;
import ru.academits.java.kononov.minesweeper.view.MinesweeperView;

public class Main {
    public static void main(String[] args) {
        EventManager eventManager = new BaseEventManager(ListenersType.values());

        MinesweeperModel minesweeperModel = new BaseMinesweeperModel(eventManager);
        minesweeperModel.setMinesweeperTimer(new BaseMinesweeperTimer(minesweeperModel));

        MinesweeperController minesweeperController = new BaseMinesweeperController(minesweeperModel);
        MinesweeperView minesweeperView = new DesktopMinesweeperView(minesweeperController);
        MinesweeperRecordsTable minesweeperRecordsTable = new FileMinesweeperRecordsTable(minesweeperController);

        eventManager.subscribeListener(ListenersType.GUI_LISTENERS, (Listener) minesweeperView);
        eventManager.subscribeListener(ListenersType.RECORDS_TABLE_LISTENERS, (Listener) minesweeperRecordsTable);

        minesweeperModel.createAndShowGameGui();
    }
}
