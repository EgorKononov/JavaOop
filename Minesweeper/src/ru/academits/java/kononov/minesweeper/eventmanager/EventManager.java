package ru.academits.java.kononov.minesweeper.eventmanager;

import ru.academits.java.kononov.minesweeper.eventmanager.events.Event;

public interface EventManager {
    void subscribeListener(ListenersType listenersType, Listener listener);

    void unsubscribeListener(ListenersType listenersType, Listener listener);

    void notify(ListenersType listenersType, Event event);
}
