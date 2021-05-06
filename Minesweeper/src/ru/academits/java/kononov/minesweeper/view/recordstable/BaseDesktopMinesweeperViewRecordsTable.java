package ru.academits.java.kononov.minesweeper.view.recordstable;

import ru.academits.java.kononov.minesweeper.model.Difficulty;
import ru.academits.java.kononov.minesweeper.recordstable.MinesweeperRecord;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static ru.academits.java.kononov.minesweeper.main.Constants.*;


public class BaseDesktopMinesweeperViewRecordsTable implements DesktopMinesweeperViewRecordsTable {
    private static final int TEXT_FIELD_INDENT = 3;

    public BaseDesktopMinesweeperViewRecordsTable() {
    }

    @Override
    public JPanel getRecordsTable(List<List<MinesweeperRecord>> recordsLists, int recordsTableSize) {
        Difficulty[] difficulties = Difficulty.values();
        JPanel recordsTablePanel = new JPanel(new GridLayout(difficulties.length, 1, 0, 10));

        for (int i = 0; i < difficulties.length; ++i) {
            JPanel currentDifficultyRecordsPanel = new JPanel(new GridBagLayout());

            addDifficultyLabel(difficulties[i], currentDifficultyRecordsPanel);

            addLabelToPanel(currentDifficultyRecordsPanel, "Name", 1, 1);
            addLabelToPanel(currentDifficultyRecordsPanel, "Time", 2, 1);
            addLabelToPanel(currentDifficultyRecordsPanel, "Date", 3, 1);

            JTextField[][] textFields = new JTextField[3][recordsTableSize];

            for (int j = 1; j <= recordsTableSize; j++) {
                addLabelToPanel(currentDifficultyRecordsPanel, j + ".  ", 0, j + 1);

                JTextField nameTextField = new JTextField(RECORD_OWNER_NAME_LENGTH_LIMIT/2 + TEXT_FIELD_INDENT);
                textFields[0][j - 1] = nameTextField;
                addTextFieldToPanel(nameTextField, currentDifficultyRecordsPanel, 1, j + 1);

                JTextField timeTextField = new JTextField(Integer.toString(TIMER_VALUE_LIMIT).length()/2 + TEXT_FIELD_INDENT);
                textFields[1][j - 1] = timeTextField;
                addTextFieldToPanel(timeTextField, currentDifficultyRecordsPanel, 2, j + 1);

                JTextField dateTextField = new JTextField(DATE_FORMAT.format(System.currentTimeMillis()).length()/2 + TEXT_FIELD_INDENT);
                textFields[2][j - 1] = dateTextField;
                addTextFieldToPanel(dateTextField, currentDifficultyRecordsPanel, 3, j + 1);
            }

            for (int k = 0; k < recordsLists.get(i).size(); ++k) {
                MinesweeperRecord minesweeperRecord = recordsLists.get(i).get(k);
                textFields[0][k].setText(minesweeperRecord.getOwner());
                textFields[1][k].setText(Integer.toString(minesweeperRecord.getValue()));
                textFields[2][k].setText(minesweeperRecord.getDate());
            }

            recordsTablePanel.add(currentDifficultyRecordsPanel);
        }

        return recordsTablePanel;
    }

    private void addDifficultyLabel(Difficulty difficulty, JPanel currentDifficultyRecordsPanel) {
        JLabel currentDifficultyLabel = new JLabel(difficulty.getName());
        GridBagConstraints currentDifficultyLabelConstraints = new GridBagConstraints();
        currentDifficultyLabelConstraints.gridx = 0;
        currentDifficultyLabelConstraints.gridy = 0;
        currentDifficultyLabelConstraints.gridwidth = 4;
        currentDifficultyLabelConstraints.insets = new Insets(0, 0, 5, 0);
        currentDifficultyLabelConstraints.anchor = GridBagConstraints.CENTER;
        currentDifficultyRecordsPanel.add(currentDifficultyLabel, currentDifficultyLabelConstraints);
    }

    private static void addLabelToPanel(JPanel panel, String labelText, int gridx, int gridy) {
        JLabel label = new JLabel(labelText);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.anchor = GridBagConstraints.LINE_START;
        panel.add(label, constraints);
    }

    private static void addTextFieldToPanel(JTextField textField, JPanel panel, int gridx, int gridy) {
        textField.setEditable(false);
        textField.setBorder(null);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.anchor = GridBagConstraints.LINE_START;
        panel.add(textField, constraints);
    }
}
