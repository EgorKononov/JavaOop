package ru.academits.java.kononov.minesweeper.dto.eventsdto;

import ru.academits.java.kononov.minesweeper.model.Difficulty;

public class CheckGameResultForRecordEventData implements EventData{
    private final Difficulty difficulty;
    private final int gameResult;

    public CheckGameResultForRecordEventData(Difficulty difficulty, int gameResult) {
        this.difficulty = difficulty;
        this.gameResult = gameResult;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getGameResult() {
        return gameResult;
    }
}
