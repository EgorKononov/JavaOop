package ru.academits.java.kononov.minesweeper.dto.eventsdto;

import ru.academits.java.kononov.minesweeper.dto.CellData;

import java.util.List;

public class ShowMinedCellsEventData implements EventData {
    private final List<CellData> minedCells;

    public ShowMinedCellsEventData(List<CellData> minedCells) {
        this.minedCells = minedCells;
    }

    public List<CellData> getMinedCells() {
        return minedCells;
    }
}
