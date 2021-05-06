package ru.academits.java.kononov.minesweeper.eventmanager.events;

import ru.academits.java.kononov.minesweeper.dto.eventsdto.EventData;
import ru.academits.java.kononov.minesweeper.dto.eventsdto.MarkCellEventData;

public class MarkCellEvent implements Event {
    private static final EventType eventType = EventType.MARK_CELL;

    private final MarkCellEventData markCellEventData;

    public MarkCellEvent(MarkCellEventData markCellEventData) {
        this.markCellEventData = markCellEventData;
    }

    @Override
    public EventType getType() {
        return eventType;
    }

    @Override
    public EventData getData() {
        return markCellEventData;
    }
}
