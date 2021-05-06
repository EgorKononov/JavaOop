package ru.academits.java.kononov.minesweeper.dto.eventsdto;

import ru.academits.java.kononov.minesweeper.model.Difficulty;

public class WriteRecordEventData implements EventData {
    private final Difficulty difficulty;
    private final String recordOwner;
    private final int gameResult;
    private final String recordDate;
    private final int recordsCount;
    private final int minRecordGameResult;

    public WriteRecordEventData(Difficulty difficulty, String recordOwner, int gameResult, String recordDate,
                                int recordsCount, int minRecordGameResult) {
        this.difficulty = difficulty;
        this.recordOwner = recordOwner;
        this.gameResult = gameResult;
        this.recordDate = recordDate;
        this.recordsCount = recordsCount;
        this.minRecordGameResult = minRecordGameResult;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public String getRecordOwner() {
        return recordOwner;
    }

    public int getGameResult() {
        return gameResult;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public int getRecordsCount() {
        return recordsCount;
    }

    public int getMinRecordGameResult() {
        return minRecordGameResult;
    }
}
