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

    public boolean isInside(double x) {
        return x >= from && x <= to;
    }

    public Range intersection(Range range) {
        double from = Math.max(this.getFrom(), range.getFrom());
        double to = Math.min(this.getTo(), range.getTo());

        if (from >= to) {
            return null;
        } else {
            return new Range(from, to);
        }
    }

    public Range[] union(Range range) {
        double from = Math.max(this.getFrom(), range.getFrom());
        double to = Math.min(this.getTo(), range.getTo());

        if (from <= to) {
            return new Range[]{new Range(Math.min(this.getFrom(), range.getFrom()), Math.max(this.getTo(), range.getTo()))};
        } else {
            return new Range[]{new Range(this.getFrom(), this.getTo()), new Range(range.getFrom(), range.getTo())};
        }
    }

    public Range[] complement(Range range) {
        if (this.getFrom() < range.getFrom()) {
            if (range.getFrom() < this.getTo()) {
                if (this.getTo() <= range.getTo()) {
                    return new Range[]{new Range(this.getFrom(), range.getFrom())};
                } else {
                    return new Range[]{new Range(this.getFrom(), range.getFrom()), new Range(range.getTo(), this.getTo())};
                }
            } else {
                return new Range[]{new Range(this.getFrom(), this.getTo())};
            }
        } else {
            if (range.getTo() <= this.getFrom()) {
                return new Range[]{new Range(this.getFrom(), this.getTo())};
            } else {
                if (range.getTo() < this.getTo()) {
                    return new Range[]{new Range(range.getTo(), this.getTo())};
                } else {
                    return null;
                }
            }
        }
    }
}
