package ru.academits.java.kononov.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException(size + " - неверное значение размерности вектора. Размерность вектора должна быть целым положительным числом.");
        }

        components = new double[size];
    }

    public Vector(Vector vector) {
        int size = vector.components.length;
        components = new double[size];
        System.arraycopy(vector.components, 0, components, 0, size);
    }

    public Vector(double[] components) {
        if (components.length == 0) {
            throw new IllegalArgumentException("Невозможно создать вектор. Длина переданного массива равна 0");
        }

        int size = components.length;
        this.components = new double[size];
        System.arraycopy(components, 0, this.components, 0, size);
    }

    public Vector(int size, double[] components) {
        if (size <= 0) {
            throw new IllegalArgumentException(size + " - неверное значение размерности вектора. Размерность вектора должна быть целым положительным числом.");
        }

        this.components = new double[size];
        System.arraycopy(components, 0, this.components, 0, Math.min(size, components.length));
    }

    public int getSize() {
        return components.length;
    }

    public void add(Vector vector) {
        int vectorSize = vector.getSize();

        if (vectorSize > getSize()) {
            components = Arrays.copyOf(components, vectorSize);
        }

        for (int i = 0; i < vectorSize; i++) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        int vectorSize = vector.getSize();

        if (vectorSize > getSize()) {
            components = Arrays.copyOf(components, vectorSize);
        }

        for (int i = 0; i < vectorSize; i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < getSize(); i++) {
            components[i] *= scalar;
        }
    }

    public void turn() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double e : components) {
            sum += e * e;
        }

        return Math.sqrt(sum);
    }

    public double getComponent(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException(index + " - неверное значение индекса компоненты вектора размерности " + getSize());
        }

        return components[index];
    }

    public void setComponent(int index, double component) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException(index + " - неверное значение индекса компоненты вектора размерности" + getSize());
        }

        components[index] = component;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector vector = new Vector(vector1);
        vector.add(vector2);

        return vector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector vector = new Vector(vector1);
        vector.subtract(vector2);

        return vector;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        int vector1Size = vector1.getSize();
        int vector2Size = vector2.getSize();
        double sum = 0;

        if (vector1Size > vector2Size) {
            for (int i = 0; i < vector2Size; i++) {
                sum += vector1.components[i] * vector2.components[i];
            }
        } else {
            for (int i = 0; i < vector1Size; i++) {
                sum += vector1.components[i] * vector2.components[i];
            }
        }

        return sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(Arrays.toString(components));
        sb.delete(1, 2);
        sb.delete(sb.length() - 1, sb.length());//
        sb.append("}");

        return sb.toString();
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

        return Arrays.equals(vector.components, components);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
    }
}