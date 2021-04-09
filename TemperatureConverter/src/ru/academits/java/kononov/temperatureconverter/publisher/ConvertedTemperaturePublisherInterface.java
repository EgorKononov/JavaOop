package ru.academits.java.kononov.temperatureconverter.publisher;

public interface ConvertedTemperaturePublisherInterface {
    void subscribeListener(ConvertedTemperatureListener convertedTemperatureListener);

    void unsubscribeListener(ConvertedTemperatureListener convertedTemperatureListener);

    void notifyListeners(double convertedTemperature);
}
