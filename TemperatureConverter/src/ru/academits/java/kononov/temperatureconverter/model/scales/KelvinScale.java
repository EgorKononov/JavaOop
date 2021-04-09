package ru.academits.java.kononov.temperatureconverter.model.scales;

public class KelvinScale implements Scale {
    @Override
    public double convertToCelsius(double initialTemperature) {
        return initialTemperature - 273.15;
    }

    @Override
    public double convertFromCelsius(double initialTemperature) {
        return initialTemperature + 273.15;
    }
}
