package ru.academits.java.kononov.minesweeper.eventmanager.events;

import ru.academits.java.kononov.minesweeper.dto.eventsdto.EventData;
import ru.academits.java.kononov.minesweeper.dto.eventsdto.WriteRecordEventData;

public class WriteRecordEvent implements Event{
    private static final EventType eventType = EventType.WRITE_RECORD;

    private final WriteRecordEventData writeRecordEventData;

    public WriteRecordEvent(WriteRecordEventData writeRecordEventData) {
        this.writeRecordEventData = writeRecordEventData;
    }

    @Override
    public EventType getType() {
        return eventType;
    }

    @Override
    public EventData getData() {
        return writeRecordEventData;
    }
}
