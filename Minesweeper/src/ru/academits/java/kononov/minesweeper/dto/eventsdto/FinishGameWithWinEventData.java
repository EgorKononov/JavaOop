package ru.academits.java.kononov.minesweeper.dto.eventsdto;

import ru.academits.java.kononov.minesweeper.dto.CellData;

import java.util.List;

public class FinishGameWithWinEventData implements EventData {
    private final List<CellData> notMarkedMinedCells;

    public FinishGameWithWinEventData(List<CellData> notMarkedMinedCells) {
        this.notMarkedMinedCells = notMarkedMinedCells;
    }

    public List<CellData> getNotMarkedMinedCells() {
        return notMarkedMinedCells;
    }
}
