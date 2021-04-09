package ru.academits.java.kononov.temperatureconverter.publisher;

import java.util.ArrayList;
import java.util.List;

public class ConvertedTemperaturePublisher implements Publisher {
    private List<Listener> listeners;

    public ConvertedTemperaturePublisher() {
        listeners = new ArrayList<>();
    }

    @Override
    public void subscribeListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void unsubscribeListener(Listener listener) {
        int i = listeners.indexOf(listener);

        if (i != -1) {
            listeners.remove(i);
        }
    }

    @Override
    public void notifyListeners(double convertedTemperature) {
        for (Listener l : listeners){
            l.update(convertedTemperature);
        }
    }
}
