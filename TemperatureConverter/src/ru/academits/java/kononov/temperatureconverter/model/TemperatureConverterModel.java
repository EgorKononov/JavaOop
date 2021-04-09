package ru.academits.java.kononov.temperatureconverter.model;

import ru.academits.java.kononov.temperatureconverter.model.scales.Scale;
import ru.academits.java.kononov.temperatureconverter.model.scales.ScaleFactory;
import ru.academits.java.kononov.temperatureconverter.model.scales.ScaleType;
import ru.academits.java.kononov.temperatureconverter.publisher.Publisher;

public class TemperatureConverterModel implements TemperatureConverterModelInterface {
    private final Publisher convertedTemperaturePublisher;

    public TemperatureConverterModel(Publisher convertedTemperaturePublisher) {
        this.convertedTemperaturePublisher = convertedTemperaturePublisher;
    }

    @Override
    public void convertTemperature(double initialTemperature, String initialTemperatureScaleSelection,
                                   String convertedTemperatureScaleSelection) {
        Scale initialTemperatureScale = ScaleFactory.createScale(ScaleType.valueOf(initialTemperatureScaleSelection));
        Scale convertedTemperatureScale = ScaleFactory.createScale(ScaleType.valueOf(convertedTemperatureScaleSelection));
        updateConvertedTemperature
                (convertedTemperatureScale.convertFromCelsius(initialTemperatureScale.convertToCelsius(initialTemperature)));
    }

    private void updateConvertedTemperature(double convertedTemperature) {
        convertedTemperaturePublisher.notifyListeners(convertedTemperature);
    }
}
