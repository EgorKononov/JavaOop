package ru.academits.java.kononov.temperatureconverter.publisher;

public interface Publisher {
    void subscribeListener(Listener listener);

    void unsubscribeListener(Listener listener);

    void notifyListeners(double convertedTemperature);
}
