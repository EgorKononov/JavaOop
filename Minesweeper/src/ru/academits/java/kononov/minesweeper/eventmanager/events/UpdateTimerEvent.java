package ru.academits.java.kononov.minesweeper.eventmanager.events;

import ru.academits.java.kononov.minesweeper.dto.eventsdto.EventData;
import ru.academits.java.kononov.minesweeper.dto.eventsdto.UpdateTimerEvenData;

public class UpdateTimerEvent implements Event{
    private static final EventType eventType = EventType.UPDATE_TIMER;

    private final UpdateTimerEvenData updateTimerEvenData;

    public UpdateTimerEvent(UpdateTimerEvenData updateTimerEvenData) {
        this.updateTimerEvenData = updateTimerEvenData;
    }

    @Override
    public EventType getType() {
        return eventType;
    }

    @Override
    public EventData getData() {
        return updateTimerEvenData;
    }
}
