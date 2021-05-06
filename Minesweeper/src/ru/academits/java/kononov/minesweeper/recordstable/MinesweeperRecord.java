package ru.academits.java.kononov.minesweeper.recordstable;

public class MinesweeperRecord {
    private final String owner;
    private final int value;
    private final String date;

    public MinesweeperRecord(String owner, int value, String date) {
        this.owner = owner;
        this.value = value;
        this.date = date;
    }

    public String getOwner() {
        return owner;
    }

    public int getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }
}
