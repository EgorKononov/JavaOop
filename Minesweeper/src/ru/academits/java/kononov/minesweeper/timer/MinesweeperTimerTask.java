package ru.academits.java.kononov.minesweeper.timer;

import ru.academits.java.kononov.minesweeper.model.MinesweeperModel;

import java.util.TimerTask;

import static ru.academits.java.kononov.minesweeper.main.Constants.TIMER_VALUE_LIMIT;


public class MinesweeperTimerTask extends TimerTask {
    private final MinesweeperTimer minesweeperTimer;
    private final MinesweeperModel minesweeperModel;
    private int timerValue;

    public MinesweeperTimerTask(MinesweeperTimer minesweeperTimer, MinesweeperModel minesweeperModel) {
        this.minesweeperTimer = minesweeperTimer;
        this.minesweeperModel = minesweeperModel;
    }

    @Override
    public void run() {
        ++timerValue;
        minesweeperModel.updateTimer(timerValue);

        if (timerValue == TIMER_VALUE_LIMIT){
            minesweeperTimer.stop();
        }
    }

    public int getTimerValue() {
        return timerValue;
    }
}
