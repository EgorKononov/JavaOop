package ru.academits.java.kononov.temperatureconverter.publisher;

import java.util.ArrayList;
import java.util.List;

public class ConvertedTemperaturePublisher implements ConvertedTemperaturePublisherInterface {
    private final List<ConvertedTemperatureListener> convertedTemperatureListeners;

    public ConvertedTemperaturePublisher() {
        convertedTemperatureListeners = new ArrayList<>();
    }

    @Override
    public void subscribeListener(ConvertedTemperatureListener convertedTemperatureListener) {
        convertedTemperatureListeners.add(convertedTemperatureListener);
    }

    @Override
    public void unsubscribeListener(ConvertedTemperatureListener convertedTemperatureListener) {
        int i = convertedTemperatureListeners.indexOf(convertedTemperatureListener);

        if (i != -1) {
            convertedTemperatureListeners.remove(i);
        }
    }

    @Override
    public void notifyListeners(double convertedTemperature) {
        for (ConvertedTemperatureListener l : convertedTemperatureListeners) {
            l.update(convertedTemperature);
        }
    }
}
