package ru.academits.java.kononov.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    @Override
    public String toString() {
        return "(" + from + "; " + to + ")";
    }

    public boolean isInside(double x) {
        return x >= from && x <= to;
    }

    public Range getIntersection(Range range) {
        double from = Math.max(this.from, range.from);
        double to = Math.min(this.to, range.to);

        if (from >= to) {
            return null;
        }

        return new Range(from, to);
    }

    public Range[] getUnion(Range range) {
        if (Math.max(from, range.from) <= Math.min(to, range.to)) {
            return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
        }

        return new Range[]{new Range(from, to), new Range(range.from, range.to)};
    }

    public Range[] getComplement(Range range) {
        if (from < range.from) {
            if (range.from < to) {
                if (to <= range.to) {
                    return new Range[]{new Range(from, range.from)};
                }

                return new Range[]{new Range(from, range.from), new Range(range.to, to)};
            }

            return new Range[]{new Range(from, to)};
        }

        if (range.to <= from) {
            return new Range[]{new Range(from, to)};
        }

        if (range.to < to) {
            return new Range[]{new Range(range.to, to)};
        }

        return new Range[]{};
    }
}
