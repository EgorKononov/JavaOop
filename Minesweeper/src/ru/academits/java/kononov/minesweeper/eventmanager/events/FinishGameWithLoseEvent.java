package ru.academits.java.kononov.minesweeper.eventmanager.events;

import ru.academits.java.kononov.minesweeper.dto.eventsdto.EventData;
import ru.academits.java.kononov.minesweeper.dto.eventsdto.FinishGameWithLoseEventData;

public class FinishGameWithLoseEvent implements Event{
    private static final EventType eventType = EventType.FINISH_GAME_WITH_LOSE;

    private final FinishGameWithLoseEventData finishGameWithLoseEventData;

    public FinishGameWithLoseEvent(FinishGameWithLoseEventData finishGameWithLoseEventData) {
        this.finishGameWithLoseEventData = finishGameWithLoseEventData;
    }

    @Override
    public EventType getType() {
        return eventType;
    }

    @Override
    public EventData getData() {
        return finishGameWithLoseEventData;
    }
}
