package ru.academits.java.kononov.minesweeper.dto.eventsdto;

import ru.academits.java.kononov.minesweeper.dto.OpenedCellData;

import java.util.ArrayList;
import java.util.List;

public class OpenCellsEventData implements EventData {
    private final List<OpenedCellData> openedCellsList;

    public OpenCellsEventData() {
        openedCellsList = new ArrayList<>();
    }

    public List<OpenedCellData> getOpenedCellsList() {
        return openedCellsList;
    }

    public void addOpenedCell(OpenedCellData openedCellData){
        openedCellsList.add(openedCellData);
    }

    public void addOpenedCells(List<OpenedCellData> openedCellsDataList){
        openedCellsList.addAll(openedCellsDataList);
    }
}
