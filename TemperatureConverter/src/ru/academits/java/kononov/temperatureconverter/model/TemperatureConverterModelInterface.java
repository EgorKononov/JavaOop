package ru.academits.java.kononov.temperatureconverter.model;

public interface TemperatureConverterModelInterface {//TODO если шкалы одинаковые не делать ничего (во View

    void convertTemperature(double initialTemperature, String initialTemperatureScaleSelection,
                            String convertedTemperatureScaleSelection);
}
