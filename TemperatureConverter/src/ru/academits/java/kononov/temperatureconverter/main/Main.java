package ru.academits.java.kononov.temperatureconverter.main;

import ru.academits.java.kononov.temperatureconverter.controller.TemperatureConverterController;
import ru.academits.java.kononov.temperatureconverter.controller.TemperatureConverterControllerInterface;
import ru.academits.java.kononov.temperatureconverter.model.TemperatureConverterModel;
import ru.academits.java.kononov.temperatureconverter.model.TemperatureConverterModelInterface;
import ru.academits.java.kononov.temperatureconverter.publisher.ConvertedTemperaturePublisher;
import ru.academits.java.kononov.temperatureconverter.publisher.ConvertedTemperatureListener;
import ru.academits.java.kononov.temperatureconverter.publisher.ConvertedTemperaturePublisherInterface;
import ru.academits.java.kononov.temperatureconverter.view.TemperatureConverterDesktopView;
import ru.academits.java.kononov.temperatureconverter.view.TemperatureConverterView;

public class Main {
    public static void main(String[] args) {
        ConvertedTemperaturePublisherInterface convertedTemperaturePublisher = new ConvertedTemperaturePublisher();

        TemperatureConverterModelInterface temperatureConverterModel =
                new TemperatureConverterModel(convertedTemperaturePublisher);

        TemperatureConverterControllerInterface temperatureConverterController =
                new TemperatureConverterController(temperatureConverterModel);

        TemperatureConverterView desktopView = new TemperatureConverterDesktopView(temperatureConverterController);
        convertedTemperaturePublisher.subscribeListener((ConvertedTemperatureListener) desktopView);

        desktopView.start();
    }
}
