package ru.academits.java.kononov.minesweeper.eventmanager.events;

import ru.academits.java.kononov.minesweeper.dto.eventsdto.EventData;
import ru.academits.java.kononov.minesweeper.dto.eventsdto.ShowMinedCellsEventData;

public class ShowMinedCellsEvent implements Event{
    private static final EventType eventType = EventType.SHOW_MINED_CELLS;

    private final ShowMinedCellsEventData showMinedCellsEventData;

    public ShowMinedCellsEvent(ShowMinedCellsEventData showMinedCellsEventData) {
        this.showMinedCellsEventData = showMinedCellsEventData;
    }

    @Override
    public EventType getType() {
        return eventType;
    }

    @Override
    public EventData getData() {
        return showMinedCellsEventData;
    }
}
