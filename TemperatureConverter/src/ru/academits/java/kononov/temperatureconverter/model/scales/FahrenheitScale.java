package ru.academits.java.kononov.temperatureconverter.model.scales;

public class FahrenheitScale implements Scale {
    @Override
    public double convertToCelsius(double initialTemperature) {
        return (initialTemperature - 32) / 1.8;
    }

    @Override
    public double convertFromCelsius(double initialTemperature) {
        return initialTemperature * 1.8 + 32;
    }
}
