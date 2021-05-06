package ru.academits.java.kononov.minesweeper.eventmanager.events;

import ru.academits.java.kononov.minesweeper.dto.eventsdto.EventData;

public class ShowAboutEvent implements Event {
    private static final EventType eventType = EventType.SHOW_ABOUT;

    public ShowAboutEvent() {
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
