package ru.academits.java.kononov.minesweeper.eventmanager.events;

import ru.academits.java.kononov.minesweeper.dto.eventsdto.EventData;

public class RequestRecordOwnerNameEvent implements Event{
    private static final EventType eventType = EventType.REQUEST_RECORD_OWNER_NAME;

    @Override
    public EventType getType() {
        return eventType;
    }

    @Override
    public EventData getData() {
        return null;
    }
}
