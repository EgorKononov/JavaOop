package ru.academits.java.kononov.minesweeper.model;

public enum Difficulty {
    BEGINNER("Beginner", 9, 9, 10,
            "task3/src/main/records_table/beginner_difficulty_records_table.txt",
            "task3/src/main/records_table/beginner_difficulty_records_table_parameters.txt"),
    INTERMEDIATE("Intermediate", 16, 16, 40,
            "task3/src/main/records_table/intermediate_difficulty_records_table.txt",
            "task3/src/main/records_table/intermediate_difficulty_records_table_parameters.txt"),
    EXPERT("Expert", 16, 30, 99,
            "task3/src/main/records_table/expert_difficulty_records_table.txt",
            "task3/src/main/records_table/expert_difficulty_records_table_parameters.txt");

    private final String name;
    private final int gameFieldRowsCount;
    private final int gameFieldColumnsCount;
    private final int minesCount;
    private final int openedCellsToWinCount;
    private final String recordsTableFilePath;
    private final String recordsTableParametersFilePath;

    Difficulty(String name, int gameFieldRowsCount, int gameFieldColumnsCount, int minesCount,
               String recordsTableFilePath, String recordsTableParametersFilePath) {
        this.name = name;
        this.gameFieldRowsCount = gameFieldRowsCount;
        this.gameFieldColumnsCount = gameFieldColumnsCount;
        this.minesCount = minesCount;
        this.openedCellsToWinCount = gameFieldRowsCount * gameFieldColumnsCount - minesCount;
        this.recordsTableFilePath = recordsTableFilePath;
        this.recordsTableParametersFilePath = recordsTableParametersFilePath;
    }

    public String getName() {
        return name;
    }

    public int getGameFieldRowsCount() {
        return gameFieldRowsCount;
    }

    public int getGameFieldColumnsCount() {
        return gameFieldColumnsCount;
    }

    public int getMinesCount() {
        return minesCount;
    }

    public int getOpenedCellsToWinCount() {
        return openedCellsToWinCount;
    }

    public String getRecordsTableFilePath() {
        return recordsTableFilePath;
    }

    public String getRecordsTableParametersFilePath() {
        return recordsTableParametersFilePath;
    }
}
