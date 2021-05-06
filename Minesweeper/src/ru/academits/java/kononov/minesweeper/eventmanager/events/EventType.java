package ru.academits.java.kononov.minesweeper.eventmanager.events;

public enum EventType {
    CREATE_AND_SHOW_GAME_GUI,
    START_NEW_GAME,
    PRESS_ON_CELL,
    OPEN_NOT_MINED_CELL,
    MARK_CELL,
    UNMARK_CELL,
    FINISH_GAME_WITH_WIN,
    FINISH_GAME_WITH_LOSE,
    UPDATE_TIMER,
    REQUEST_RECORD_OWNER_NAME,
    SHOW_RECORDS_TABLE,
    SHOW_ABOUT,
    SHOW_MINED_CELLS,
    CHECK_GAME_RESULT_FOR_RECORD,
    WRITE_RECORD,
    REQUEST_RECORDS_LISTS;
}