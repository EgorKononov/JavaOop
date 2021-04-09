package ru.academits.java.kononov.temperatureconverter.model;

public interface TemperatureConverterModelInterface {
    void convertTemperature(double initialTemperature, String initialTemperatureScaleSelection,
                            String convertedTemperatureScaleSelection);
}
