package ru.academits.java.kononov.minesweeper.dto.eventsdto;

public class UpdateTimerEvenData implements EventData{
    private final int timerValue;

    public UpdateTimerEvenData(int timerValue) {
        this.timerValue = timerValue;
    }

    public int getTimerValue() {
        return timerValue;
    }
}
