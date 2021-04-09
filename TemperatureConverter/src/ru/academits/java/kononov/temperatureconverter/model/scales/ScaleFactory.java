package ru.academits.java.kononov.temperatureconverter.model.scales;

public class ScaleFactory {
    private ScaleFactory() {
    }

    public static Scale createScale(ScaleType scaleType) {
        Scale scale = null;

        switch (scaleType) {
            case CELSIUS -> scale = new CelsiusScale();
            case FAHRENHEIT -> scale = new FahrenheitScale();
            case KELVIN -> scale = new KelvinScale();
        }

        return scale;
    }
}
