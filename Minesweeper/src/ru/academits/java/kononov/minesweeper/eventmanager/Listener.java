package ru.academits.java.kononov.minesweeper.eventmanager;

import ru.academits.java.kononov.minesweeper.eventmanager.events.Event;

public interface Listener {
    void update(Event event);
}
