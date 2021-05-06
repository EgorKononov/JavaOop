package ru.academits.java.kononov.minesweeper.eventmanager.events;

import ru.academits.java.kononov.minesweeper.dto.eventsdto.EventData;
import ru.academits.java.kononov.minesweeper.dto.eventsdto.OpenCellsEventData;

public class OpenCellEvent implements Event{
    private static final EventType eventType = EventType.OPEN_NOT_MINED_CELL;

    private final OpenCellsEventData openCellsEventData;

    public OpenCellEvent(OpenCellsEventData openCellsEventData) {
        this.openCellsEventData = openCellsEventData;
    }

    @Override
    public EventType getType() {
        return eventType;
    }

    @Override
    public EventData getData() {
        return openCellsEventData;
    }
}
