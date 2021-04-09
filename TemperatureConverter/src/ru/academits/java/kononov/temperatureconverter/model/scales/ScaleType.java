package ru.academits.java.kononov.temperatureconverter.model.scales;

public enum ScaleType {
    CELSIUS("Градусы Цельсия"),
    FAHRENHEIT("Градусы Фаренгейта"),
    KELVIN("Градусы Кельвина");

    private final String name;

    ScaleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
