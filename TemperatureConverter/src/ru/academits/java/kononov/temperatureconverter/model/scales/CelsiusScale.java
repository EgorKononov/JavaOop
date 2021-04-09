package ru.academits.java.kononov.temperatureconverter.model.scales;

public class CelsiusScale implements Scale {
    @Override
    public double convertToCelsius(double initialTemperature) {
        return initialTemperature;
    }

    @Override
    public double convertFromCelsius(double initialTemperature) {
        return initialTemperature;
    }
}
