package ru.academits.java.kononov.minesweeper.eventmanager.events;

import ru.academits.java.kononov.minesweeper.dto.eventsdto.CreateGuiEventData;
import ru.academits.java.kononov.minesweeper.dto.eventsdto.EventData;

public class StartNewGameEvent implements Event{
    private static final EventType eventType = EventType.START_NEW_GAME;

    private final CreateGuiEventData createGuiEventData;

    public StartNewGameEvent(CreateGuiEventData createGuiEventData) {
        this.createGuiEventData = createGuiEventData;
    }

    @Override
    public EventType getType() {
        return eventType;
    }

    @Override
    public EventData getData() {
        return createGuiEventData;
    }
}
