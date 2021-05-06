package ru.academits.java.kononov.minesweeper.eventmanager;

import ru.academits.java.kononov.minesweeper.eventmanager.events.Event;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class BaseEventManager implements EventManager {
    Map<ListenersType, List<Listener>> listeners = new EnumMap<>(ListenersType.class);

    public BaseEventManager(ListenersType[] listenersTypes) {
        for (ListenersType listenersType : listenersTypes) {
            this.listeners.put(listenersType, new ArrayList<>());
        }
    }

    @Override
    public void subscribeListener(ListenersType listenersType, Listener listener) {
        listeners.get(listenersType).add(listener);
    }

    @Override
    public void unsubscribeListener(ListenersType listenersType, Listener listener) {
        listeners.get(listenersType).remove(listener);
    }

    @Override
    public void notify(ListenersType listenersType, Event event) {
        List<Listener> currentTypeListeners = listeners.get(listenersType);

        for (Listener listener : currentTypeListeners) {
            listener.update(event);
        }
    }
}
