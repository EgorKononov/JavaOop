package ru.academits.java.kononov.temperatureconverter.model;

import ru.academits.java.kononov.temperatureconverter.model.scales.Scale;
import ru.academits.java.kononov.temperatureconverter.model.scales.ScaleFactory;
import ru.academits.java.kononov.temperatureconverter.model.scales.ScaleType;
import ru.academits.java.kononov.temperatureconverter.publisher.ConvertedTemperaturePublisherInterface;

public class TemperatureConverterModel implements TemperatureConverterModelInterface {
    private final ConvertedTemperaturePublisherInterface convertedTemperaturePublisher;

    public TemperatureConverterModel(ConvertedTemperaturePublisherInterface convertedTemperaturePublisher) {
        this.convertedTemperaturePublisher = convertedTemperaturePublisher;
    }

    @Override
    public void convertTemperature(double initialTemperature, String initialTemperatureScaleSelection,
                                   String convertedTemperatureScaleSelection) {
        if (initialTemperatureScaleSelection.equals(convertedTemperatureScaleSelection)) {
            updateConvertedTemperature(initialTemperature);
        } else {
            Scale initialTemperatureScale = ScaleFactory.createScale(ScaleType.valueOf(initialTemperatureScaleSelection));
            Scale convertedTemperatureScale = ScaleFactory.createScale
                    (ScaleType.valueOf(convertedTemperatureScaleSelection));

            updateConvertedTemperature
                    (convertedTemperatureScale.convertFromCelsius
                            (initialTemperatureScale.convertToCelsius(initialTemperature)));
        }
    }

    private void updateConvertedTemperature(double convertedTemperature) {
        convertedTemperaturePublisher.notifyListeners(convertedTemperature);
    }
}
