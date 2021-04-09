package ru.academits.java.kononov.temperatureconverter.controller;

import ru.academits.java.kononov.temperatureconverter.model.TemperatureConverterModelInterface;

public class TemperatureConverterController implements TemperatureConverterControllerInterface {
    private final TemperatureConverterModelInterface temperatureConverterModel;

    public TemperatureConverterController(TemperatureConverterModelInterface temperatureConverterModel) {
        this.temperatureConverterModel = temperatureConverterModel;
    }

    @Override
    public void convertTemperature(double initialTemperature, String initialTemperatureScaleSelection,
                                   String convertedTemperatureScaleSelection) {
        temperatureConverterModel.convertTemperature(initialTemperature, initialTemperatureScaleSelection,
                convertedTemperatureScaleSelection);
    }
}
