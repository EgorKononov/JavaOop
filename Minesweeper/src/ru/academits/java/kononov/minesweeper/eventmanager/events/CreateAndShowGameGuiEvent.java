package ru.academits.java.kononov.minesweeper.eventmanager.events;

import ru.academits.java.kononov.minesweeper.dto.eventsdto.CreateGuiEventData;
import ru.academits.java.kononov.minesweeper.dto.eventsdto.EventData;

public class CreateAndShowGameGuiEvent implements Event {
    private static final EventType eventType = EventType.CREATE_AND_SHOW_GAME_GUI;

    private final CreateGuiEventData createGuiEventData;

    public CreateAndShowGameGuiEvent(CreateGuiEventData createGuiEventData) {
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
