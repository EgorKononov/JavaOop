package ru.academits.java.kononov.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;
    private int size;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Неверное значение размерности вектора. Размерность вектора должна быть целым положительным числом.");
        }

        components = new double[size];
        this.size = size;
    }

    public Vector(Vector vector) {
        this.components = vector.components;
        this.size = vector.size;
    }

    public Vector(double[] components) {
        this.components = components;
        this.size = components.length;
    }

    public Vector(int size, double[] components) {
        if (size <= 0) {
            throw new IllegalArgumentException("Неверное значение размерности вектора. Размерность вектора должна быть целым положительным числом.");
        }

        this.size = size;
        this.components = new double[size];

        for (int i = 0; i < components.length; ++i) {
            this.components[i] = components[i];
        }

        if (size > components.length) {
            for (int i = components.length; i < size; ++i) {
                this.components[i] = 0;
            }
        }
    }

    public double[] getComponents() {
        return components;
    }

    public int getSize() {
        return size;
    }

    public void setComponents(double[] components) {
        this.components = components;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private static int getMaxSize(Vector vector1, Vector vector2) {
        return Math.max(vector1.getSize(), vector2.getSize());
    }

    public Vector add(Vector vector) {
        int maxSize = getMaxSize(this, vector);
        Vector addend1 = new Vector(maxSize, this.getComponents());
        Vector addend2 = new Vector(maxSize, vector.getComponents());
        double[] components = new double[maxSize];

        for (int i = 0; i < maxSize; i++) {
            components[i] = addend1.getComponents()[i] + addend2.getComponents()[i];
        }

        return new Vector(components);
    }

    public Vector subtract(Vector vector) {
        int maxSize = getMaxSize(this, vector);
        Vector reduced = new Vector(maxSize, this.getComponents());
        Vector subtracted = new Vector(maxSize, vector.getComponents());
        double[] components = new double[maxSize];

        for (int i = 0; i < maxSize; i++) {
            components[i] = reduced.getComponents()[i] - subtracted.getComponents()[i];
        }

        return new Vector(components);
    }

    public Vector scalarMultiply(double scalar) {
        int size = getSize();
        double[] components = new double[size];

        for (int i = 0; i < size; i++) {
            components[i] = getComponents()[i] * scalar;
        }

        return new Vector(components);
    }

    public Vector turn() {
        int size = getSize();
        double[] components = new double[size];

        for (int i = 0; i < size; i++) {
            components[i] = getComponents()[i] * -1;
        }

        return new Vector(components);
    }

    public double getLength() {
        int size = getSize();
        double[] components = new double[size];
        double sum = 0;

        for (int i = 0; i < size; i++) {
            sum += Math.pow(getComponents()[i], 2);
        }

        return Math.sqrt(sum);
    }

    public double getComponent(int index) {
        if (index < 0 || index > getSize()) {
            throw new IllegalArgumentException("Неверное значение индекса компоненты вектора.");
        }

        return getComponents()[index];
    }

    public void setComponent(int index, double component) {
        if (index < 0 || index > getSize()) {
            throw new IllegalArgumentException("Неверное значение индекса компоненты вектора.");
        }

        getComponents()[index] = component;
    }

    public static Vector getAddition(Vector vector1, Vector vector2) {
        int maxSize = getMaxSize(vector1, vector2);
        Vector addend1 = new Vector(maxSize, vector1.getComponents());
        Vector addend2 = new Vector(maxSize, vector2.getComponents());
        double[] components = new double[maxSize];

        for (int i = 0; i < maxSize; i++) {
            components[i] = addend1.getComponents()[i] + addend2.getComponents()[i];
        }

        return new Vector(components);
    }

    public static Vector getSubtraction(Vector vector1, Vector vector2) {
        int maxSize = getMaxSize(vector1, vector2);
        Vector reduced = new Vector(maxSize, vector1.getComponents());
        Vector subtracted = new Vector(maxSize, vector2.getComponents());
        double[] components = new double[maxSize];

        for (int i = 0; i < maxSize; i++) {
            components[i] = reduced.getComponents()[i] - subtracted.getComponents()[i];
        }

        return new Vector(components);
    }

    public static double getScalarMultiplication(Vector vector1, Vector vector2) {
        int maxSize = getMaxSize(vector1, vector2);
        Vector multiplier1 = new Vector(maxSize, vector1.getComponents());
        Vector multiplier2 = new Vector(maxSize, vector2.getComponents());
        double sum = 0;

        for (int i = 0; i < maxSize; i++) {
            sum += multiplier1.getComponents()[i] * multiplier2.getComponents()[i];
        }

        return sum;
    }

    @Override
    public String toString() {
        return Arrays.toString(components).replace("[", "{ ").replace("]", " }");
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Vector vector = (Vector) o;

        return Arrays.equals(vector.getComponents(), getComponents()) && vector.getSize() == getSize();
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Arrays.hashCode(getComponents());
        hash = prime * hash + getSize();

        return hash;
    }
}
