package ru.academits.java.kononov.minesweeper.eventmanager.events;

import ru.academits.java.kononov.minesweeper.dto.eventsdto.CheckGameResultForRecordEventData;
import ru.academits.java.kononov.minesweeper.dto.eventsdto.EventData;

public class CheckGameResultForRecordEvent implements Event{
    private static final EventType eventType = EventType.CHECK_GAME_RESULT_FOR_RECORD;
    private final CheckGameResultForRecordEventData checkGameResultForRecordEventData;

    public CheckGameResultForRecordEvent(CheckGameResultForRecordEventData checkGameResultForRecordEventData) {
        this.checkGameResultForRecordEventData = checkGameResultForRecordEventData;
    }

    @Override
    public EventType getType() {
        return eventType;
    }

    @Override
    public EventData getData() {
        return checkGameResultForRecordEventData;
    }
}
