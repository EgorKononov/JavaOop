package ru.academits.java.kononov.minesweeper.eventmanager.events;

import ru.academits.java.kononov.minesweeper.dto.eventsdto.EventData;
import ru.academits.java.kononov.minesweeper.dto.eventsdto.ShowRecordsTableEventData;

public class ShowRecordsTableEvent implements Event{
    private static final EventType eventType = EventType.SHOW_RECORDS_TABLE;

    private final ShowRecordsTableEventData showRecordsTableEventData;

    public ShowRecordsTableEvent(ShowRecordsTableEventData showRecordsTableEventData) {
        this.showRecordsTableEventData = showRecordsTableEventData;
    }

    @Override
    public EventType getType() {
        return eventType;
    }

    @Override
    public EventData getData() {
        return showRecordsTableEventData;
    }
}
