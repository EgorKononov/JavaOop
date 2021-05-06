package ru.academits.java.kononov.minesweeper.recordstable;

import ru.academits.java.kononov.minesweeper.controller.MinesweeperController;
import ru.academits.java.kononov.minesweeper.dto.eventsdto.CheckGameResultForRecordEventData;
import ru.academits.java.kononov.minesweeper.dto.eventsdto.WriteRecordEventData;
import ru.academits.java.kononov.minesweeper.eventmanager.Listener;
import ru.academits.java.kononov.minesweeper.eventmanager.events.Event;
import ru.academits.java.kononov.minesweeper.exceptions.IncorrectEventTypeException;
import ru.academits.java.kononov.minesweeper.model.Difficulty;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class FileMinesweeperRecordsTable implements MinesweeperRecordsTable, Listener {
    private static final int RECORDS_TABLE_SIZE = 10;
    private final MinesweeperController minesweeperController;

    public FileMinesweeperRecordsTable(MinesweeperController minesweeperController) {
        this.minesweeperController = minesweeperController;
    }

    @Override
    public void update(Event event) {
        try {
            switch (event.getType()) {
                case CHECK_GAME_RESULT_FOR_RECORD -> {
                    CheckGameResultForRecordEventData eventData = (CheckGameResultForRecordEventData) event.getData();
                    checkGameResultForRecord(eventData);
                }

                case WRITE_RECORD -> writeRecord((WriteRecordEventData) event.getData());
                case REQUEST_RECORDS_LISTS -> sendRecordsLists();
                default -> throw new IncorrectEventTypeException("Некорректное значение EventType" + event.getType().name());
            }
        } catch (IncorrectEventTypeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void checkGameResultForRecord(CheckGameResultForRecordEventData checkGameResultForRecordEventData) {
        try (BufferedReader reader = new BufferedReader(new FileReader(
                checkGameResultForRecordEventData.getDifficulty().getRecordsTableParametersFilePath()))) {
            int recordsCount = Integer.parseInt(reader.readLine());
            int minRecord = Integer.parseInt(reader.readLine());

            if (recordsCount < RECORDS_TABLE_SIZE || checkGameResultForRecordEventData.getGameResult() < minRecord) {
                minesweeperController.requestRecordOwnerName(recordsCount, minRecord);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeRecord(WriteRecordEventData writeRecordEventData) {
        String recordsTableFilePath = writeRecordEventData.getDifficulty().getRecordsTableFilePath();
        String recordsTableParametersFilePath = writeRecordEventData.getDifficulty().getRecordsTableParametersFilePath();
        String recordOwner = writeRecordEventData.getRecordOwner();
        String recordDate = writeRecordEventData.getRecordDate();
        int gameResult = writeRecordEventData.getGameResult();
        int recordsCount = writeRecordEventData.getRecordsCount();
        int minRecordGameResult = writeRecordEventData.getMinRecordGameResult();

        if (recordsCount == 0 || (recordsCount < RECORDS_TABLE_SIZE && gameResult >= minRecordGameResult)) {
            writeRecordToTheEndOfRecordsTableFile(recordsTableFilePath, recordOwner, recordDate, gameResult);
            writeRecordsTableParametersWhenNewRecordIsMinRecord(recordsTableParametersFilePath, gameResult, recordsCount);
        } else {
            List<MinesweeperRecord> recordsList = putRecord(recordsTableFilePath, recordOwner, recordDate, gameResult);

            if (recordsList.size() > RECORDS_TABLE_SIZE) {
                recordsList.remove(RECORDS_TABLE_SIZE);
            }

            assert recordsList.size() <= RECORDS_TABLE_SIZE;

            writeRecordsTableFile(recordsTableFilePath, recordsList);
            writeRecordsTableParametersFile(recordsTableParametersFilePath, recordsList);
        }
    }

    private void writeRecordToTheEndOfRecordsTableFile(String recordsTableFilePath, String recordOwner, String recordDate, int gameResult) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(recordsTableFilePath, true))) {
            writer.println(recordOwner + System.lineSeparator() +
                    gameResult + System.lineSeparator() +
                    recordDate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeRecordsTableParametersWhenNewRecordIsMinRecord(String recordsTableParametersFilePath, int gameResult, int recordsCount) {
        try (PrintWriter writer = new PrintWriter(recordsTableParametersFilePath)) {
            writer.println(++recordsCount + System.lineSeparator() +
                    gameResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<MinesweeperRecord> readRecordsFileToList(String recordsTableFilePath) {
        List<MinesweeperRecord> recordsList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(recordsTableFilePath))) {
            String owner = reader.readLine();

            while (owner != null) {
                int value = Integer.parseInt(reader.readLine());
                String date = reader.readLine();

                recordsList.add(new MinesweeperRecord(owner, value, date));

                owner = reader.readLine();
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return recordsList;
    }

    private List<MinesweeperRecord> putRecord(String recordsTableFilePath, String recordOwner, String recordDate, int gameResult) {
        List<MinesweeperRecord> recordsList = readRecordsFileToList(recordsTableFilePath);

        for (ListIterator<MinesweeperRecord> i = recordsList.listIterator(); i.hasNext(); ) {
            MinesweeperRecord minesweeperRecord = i.next();

            if (gameResult < minesweeperRecord.getValue()) {
                i.previous();
                i.add(new MinesweeperRecord(recordOwner, gameResult, recordDate));
                break;
            }

            if (gameResult == minesweeperRecord.getValue()) {
                i.add(new MinesweeperRecord(recordOwner, gameResult, recordDate));
                break;
            }
        }

        return recordsList;
    }

    private void writeRecordsTableParametersFile(String recordsTableParametersFilePath, List<MinesweeperRecord> recordsList) {
        try (PrintWriter writer = new PrintWriter(recordsTableParametersFilePath)) {
            writer.println(recordsList.size() + System.lineSeparator() +
                    recordsList.get(recordsList.size() - 1).getValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeRecordsTableFile(String recordsTableFilePath, List<MinesweeperRecord> recordsList) {
        try (PrintWriter writer = new PrintWriter(recordsTableFilePath)) {
            for (MinesweeperRecord mr : recordsList) {
                writer.println(mr.getOwner() + System.lineSeparator() +
                        mr.getValue() + System.lineSeparator() +
                        mr.getDate());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendRecordsLists() {
        List<List<MinesweeperRecord>> recordsLists = new ArrayList<>();
        Difficulty[] difficulties = Difficulty.values();

        for (Difficulty d : difficulties) {
            recordsLists.add(readRecordsFileToList(d.getRecordsTableFilePath()));
        }

        minesweeperController.showRecordsTable(recordsLists, RECORDS_TABLE_SIZE);
    }
}
