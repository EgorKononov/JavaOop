package ru.academits.java.kononov.minesweeper.timer;

import ru.academits.java.kononov.minesweeper.model.MinesweeperModel;

import java.util.Timer;

public class BaseMinesweeperTimer implements MinesweeperTimer{
    private final MinesweeperModel minesweeperModel;
    private Timer timer;
    private MinesweeperTimerTask minesweeperTimerTask;

    public BaseMinesweeperTimer(MinesweeperModel minesweeperModel) {
        this.minesweeperModel = minesweeperModel;
    }

    @Override
    public void start() {
        timer = new Timer();
        minesweeperTimerTask = new MinesweeperTimerTask(this, minesweeperModel);
        timer.scheduleAtFixedRate(minesweeperTimerTask,
                1000, 1000);
    }

    @Override
    public void stop() {
        timer.cancel();
    }

    @Override
    public int getTimerValue() {
        return minesweeperTimerTask.getTimerValue();
    }
}
