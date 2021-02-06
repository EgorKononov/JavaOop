package ru.academits.java.kononov.temperature_converter_controller;

import ru.academits.java.kononov.temperature_converter_model.TemperatureConverter;
import ru.academits.java.kononov.temperature_converter_view.View;

public class Controller {
    private View view;
    private final TemperatureConverter temperatureConverter;

    public Controller(TemperatureConverter temperatureConverter) {
        this.temperatureConverter = temperatureConverter;
    }

    public void setView(View view) {
        this.view = view;
    }

    public double convertTemperature(double initialTemperature, String initialTemperatureScaleSelection, String convertedTemperatureScaleSelection) {
        String celsiusString = "Градусы Цельсия";
        String fahrenheitString = "Градусы Фаренгейта";

        if (initialTemperatureScaleSelection.equals(celsiusString)) {
            if (convertedTemperatureScaleSelection.equals(celsiusString)) {
                return initialTemperature;
            }

            if (convertedTemperatureScaleSelection.equals(fahrenheitString)) {
                return temperatureConverter.convertCelsiusToFahrenheit(initialTemperature);
            }

            return temperatureConverter.convertCelsiusToKelvin(initialTemperature);
        }

        if (initialTemperatureScaleSelection.equals(fahrenheitString)) {
            if (convertedTemperatureScaleSelection.equals(celsiusString)) {
                return temperatureConverter.convertFahrenheitToCelsius(initialTemperature);
            }

            if (convertedTemperatureScaleSelection.equals(fahrenheitString)) {
                return initialTemperature;
            }

            return temperatureConverter.convertFahrenheitToKelvin(initialTemperature);
        }

        if (convertedTemperatureScaleSelection.equals(celsiusString)) {
            return temperatureConverter.convertKelvinToCelsius(initialTemperature);
        }

        if (convertedTemperatureScaleSelection.equals(fahrenheitString)) {
            return temperatureConverter.convertKelvinToFahrenheit(initialTemperature);
        }

        return initialTemperature;
    }
}