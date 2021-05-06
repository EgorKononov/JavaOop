package ru.academits.java.kononov.minesweeper.eventmanager.events;

import ru.academits.java.kononov.minesweeper.dto.eventsdto.EventData;

public class RequestRecordsListsEvent implements Event{
    private static final EventType eventType = EventType.REQUEST_RECORDS_LISTS;

    @Override
    public EventType getType() {
        return eventType;
    }

    @Override
    public EventData getData() {
        return null;
    }
}
