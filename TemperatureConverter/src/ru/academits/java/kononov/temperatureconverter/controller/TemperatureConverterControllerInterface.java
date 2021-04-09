package ru.academits.java.kononov.temperatureconverter.controller;

public interface TemperatureConverterControllerInterface {
    void convertTemperature(double initialTemperature, String initialTemperatureScaleSelection,
                            String convertedTemperatureScaleSelection);
}
