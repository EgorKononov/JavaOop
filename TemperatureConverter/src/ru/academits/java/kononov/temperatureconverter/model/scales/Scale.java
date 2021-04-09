package ru.academits.java.kononov.temperatureconverter.model.scales;

public interface Scale {
    double convertToCelsius(double initialTemperature);

    double convertFromCelsius(double initialTemperature);
}
