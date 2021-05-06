package ru.academits.java.kononov.minesweeper.eventmanager.events;

import ru.academits.java.kononov.minesweeper.dto.eventsdto.EventData;

public class PressOnCellEvent implements Event {
    private static final EventType eventType = EventType.PRESS_ON_CELL;

    public PressOnCellEvent() {
    }

    @Override
    public EventType getType() {
        return eventType;
    }

    @Override
    public EventData getData() {
        return null;
    }
}
