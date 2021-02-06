package ru.academits.java.kononov.temperature_converter_model;

public class TemperatureConverter {
    public double convertCelsiusToFahrenheit(double celsiusTemperature) {
        return celsiusTemperature * 1.8 + 32;
    }

    public double convertCelsiusToKelvin(double celsiusTemperature) {
        return celsiusTemperature + 273.15;
    }

    public double convertFahrenheitToCelsius(double fahrenheitTemperature) {
        return (fahrenheitTemperature - 32) / 1.8;
    }

    public double convertFahrenheitToKelvin(double fahrenheitTemperature) {
        return (fahrenheitTemperature + 459.67) / 1.8;
    }

    public double convertKelvinToCelsius(double kelvinTemperature) {
        return kelvinTemperature - 273.15;
    }

    public double convertKelvinToFahrenheit(double kelvinTemperature) {
        return kelvinTemperature * 1.8 - 459.67;
    }
}