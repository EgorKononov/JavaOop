package ru.academits.java.kononov.temperature_converter_main;

import ru.academits.java.kononov.temperature_converter_controller.Controller;
import ru.academits.java.kononov.temperature_converter_model.TemperatureConverter;
import ru.academits.java.kononov.temperature_converter_view.DesktopView;
import ru.academits.java.kononov.temperature_converter_view.View;

public class Main {
    public static void main(String[] args) {
        TemperatureConverter temperatureConverter = new TemperatureConverter();
        Controller controller = new Controller(temperatureConverter);
        View desktopView = new DesktopView(controller);

        controller.setView(desktopView);
        desktopView.start();
    }
}