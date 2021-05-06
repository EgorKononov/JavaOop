package ru.academits.java.kononov.minesweeper.eventmanager.events;

import ru.academits.java.kononov.minesweeper.dto.eventsdto.EventData;
import ru.academits.java.kononov.minesweeper.dto.eventsdto.FinishGameWithWinEventData;

public class FinishGameWithWinEvent implements Event {
    private static final EventType eventType = EventType.FINISH_GAME_WITH_WIN;

    private final FinishGameWithWinEventData finishGameWithWinEventData;

    public FinishGameWithWinEvent(FinishGameWithWinEventData finishGameWithWinEventData) {
        this.finishGameWithWinEventData = finishGameWithWinEventData;
    }

    @Override
    public EventType getType() {
        return eventType;
    }

    @Override
    public EventData getData() {
        return finishGameWithWinEventData;
    }
}
