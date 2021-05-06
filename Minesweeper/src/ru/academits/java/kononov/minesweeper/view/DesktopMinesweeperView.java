package ru.academits.java.kononov.minesweeper.view;

import ru.academits.java.kononov.minesweeper.controller.MinesweeperController;
import ru.academits.java.kononov.minesweeper.dto.CellData;
import ru.academits.java.kononov.minesweeper.dto.OpenedCellData;
import ru.academits.java.kononov.minesweeper.dto.eventsdto.*;
import ru.academits.java.kononov.minesweeper.eventmanager.Listener;
import ru.academits.java.kononov.minesweeper.eventmanager.events.Event;
import ru.academits.java.kononov.minesweeper.exceptions.IncorrectEventTypeException;
import ru.academits.java.kononov.minesweeper.model.Difficulty;
import ru.academits.java.kononov.minesweeper.recordstable.MinesweeperRecord;
import ru.academits.java.kononov.minesweeper.view.about.BaseDesktopViewAbout;
import ru.academits.java.kononov.minesweeper.view.about.DesktopViewAbout;
import ru.academits.java.kononov.minesweeper.view.recordstable.BaseDesktopMinesweeperViewRecordsTable;
import ru.academits.java.kononov.minesweeper.view.recordstable.DesktopMinesweeperViewRecordsTable;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import static ru.academits.java.kononov.minesweeper.main.Constants.RECORD_OWNER_NAME_LENGTH_LIMIT;


public class DesktopMinesweeperView implements MinesweeperView, Listener {
    private static final int CELL_SIDE_SIZE = 32;
    private static final int GAME_INFO_PANEL_ELEMENTS_HEIGHT = 45;
    private static final int INDICATOR_PANELS_VERTICAL_INDENT = 6;
    private static final int GAME_INFO_AND_GAME_FIELD_PANELS_INDENT = 10;
    private static final Dimension MINES_LEFT_AND_TIMER_PANELS_SIZE =
            new Dimension(85, GAME_INFO_PANEL_ELEMENTS_HEIGHT);

    private static final ImageIcon CELL_ICON = new ImageIcon("Minesweeper/resources/images/cell.png");
    private static final ImageIcon PRESSED_CELL_ICON = new ImageIcon("Minesweeper/resources/images/pressed_cell.png");
    private static final ImageIcon FLAG_ICON = new ImageIcon("Minesweeper/resources/images/flag.png");
    private static final ImageIcon MINE_ICON = new ImageIcon("Minesweeper/resources/images/mine.png");
    private static final ImageIcon EXPLODED_MINE_ICON = new ImageIcon("Minesweeper/resources/images/exploded_mine.png");
    private static final ImageIcon WRONG_FLAG_ICON = new ImageIcon("Minesweeper/resources/images/wrong_flag.png");
    private static final ImageIcon SMILEY_BUTTON_ICON = new ImageIcon("Minesweeper/resources/images/smiley_face.png");
    private static final ImageIcon PRESSED_ON_CELL_ICON = new ImageIcon("Minesweeper/resources/images/face_with_open_mouth.png");
    private static final ImageIcon WIN_ICON = new ImageIcon("Minesweeper/resources/images/face_with_sunglasses.png");
    private static final ImageIcon LOSE_ICON = new ImageIcon("Minesweeper/resources/images/dizzy_face.png");
    private static final ImageIcon[] OPENED_CELLS_IMAGES = {
            new ImageIcon("Minesweeper/resources/images/0.png"),
            new ImageIcon("Minesweeper/resources/images/1.png"),
            new ImageIcon("Minesweeper/resources/images/2.png"),
            new ImageIcon("Minesweeper/resources/images/3.png"),
            new ImageIcon("Minesweeper/resources/images/4.png"),
            new ImageIcon("Minesweeper/resources/images/5.png"),
            new ImageIcon("Minesweeper/resources/images/6.png"),
            new ImageIcon("Minesweeper/resources/images/7.png"),
            new ImageIcon("Minesweeper/resources/images/8.png")
    };

    private final MinesweeperController minesweeperController;

    private JFrame frame;
    private JPanel gamePanel;
    private JButton smileyButton;
    private JButton[][] cellButtons;
    private JLabel minesLeftIndicator;
    private JLabel timerValueLabel;

    public DesktopMinesweeperView(MinesweeperController minesweeperController) {
        this.minesweeperController = minesweeperController;
    }

    @Override
    public void update(Event event) {
        try {
            switch (event.getType()) {
                case CREATE_AND_SHOW_GAME_GUI -> {
                    CreateGuiEventData eventData = (CreateGuiEventData) event.getData();
                    createAndShowGameGui(eventData.getRowsCount(), eventData.getColumnsCount(), eventData.getMinesCount());
                }

                case START_NEW_GAME -> {
                    CreateGuiEventData eventData = (CreateGuiEventData) event.getData();
                    startNewGame(eventData.getRowsCount(), eventData.getColumnsCount(), eventData.getMinesCount());
                }

                case PRESS_ON_CELL -> pressOnCell();

                case OPEN_NOT_MINED_CELL -> {
                    OpenCellsEventData eventData = (OpenCellsEventData) event.getData();
                    openNotMinedCell(eventData.getOpenedCellsList());
                }

                case MARK_CELL -> {
                    MarkCellEventData eventData = (MarkCellEventData) event.getData();
                    markCell(eventData.getRow(), eventData.getColumn(), eventData.getMinesLeftCountIndicatorValue());
                }

                case UNMARK_CELL -> {
                    MarkCellEventData eventData = (MarkCellEventData) event.getData();
                    unmarkCell(eventData.getRow(), eventData.getColumn(), eventData.getMinesLeftCountIndicatorValue());
                }

                case FINISH_GAME_WITH_WIN -> {
                    FinishGameWithWinEventData eventData = (FinishGameWithWinEventData) event.getData();
                    finishGameWithWin(eventData.getNotMarkedMinedCells());
                }

                case FINISH_GAME_WITH_LOSE -> {
                    FinishGameWithLoseEventData eventData = (FinishGameWithLoseEventData) event.getData();
                    finishGameWithLose(eventData.getRow(), eventData.getColumn(),
                            eventData.getNotOpenedNotMarkedMinedCells(), eventData.getWrongMarkedCells());
                }

                case UPDATE_TIMER -> {
                    UpdateTimerEvenData evenData = (UpdateTimerEvenData) event.getData();
                    updateTimer(evenData.getTimerValue());
                }

                case REQUEST_RECORD_OWNER_NAME -> requestRecordOwnerName();

                case SHOW_RECORDS_TABLE -> {
                    ShowRecordsTableEventData eventData = (ShowRecordsTableEventData) event.getData();
                    showRecordTable(eventData.getRecordsLists(), eventData.getRecordsTableSize());
                }

                case SHOW_ABOUT -> showAbout();

                case SHOW_MINED_CELLS -> {
                    ShowMinedCellsEventData eventData = (ShowMinedCellsEventData) event.getData();
                    showMinedCells(eventData.getMinedCells());
                }

                default -> throw new IncorrectEventTypeException("Некорректное значение EventType" + event.getType().name());
            }
        } catch (IncorrectEventTypeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createAndShowGameGui(int rowsCount, int columnsCount, int minesCount) {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Minesweeper");
            frame.setIconImage(Toolkit.getDefaultToolkit().getImage("task3/src/main/resources/images/minesweeper_icon.png"));
            frame.setLayout(new GridBagLayout());
            frame.setResizable(false);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            createMenuBar();
            gamePanel = createGamePanel(rowsCount, columnsCount, minesCount);

            frame.add(gamePanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        JMenuItem newGameMenuItem = new JMenuItem("New Game");
        newGameMenuItem.addActionListener(e -> minesweeperController.startCurrentDifficultyNewGame());
        menu.add(newGameMenuItem);
        menu.addSeparator();

        ButtonGroup difficultyMenuItemsGroup = new ButtonGroup();
        addDifficultyMenuItem(Difficulty.BEGINNER, menu, difficultyMenuItemsGroup, true);
        addDifficultyMenuItem(Difficulty.INTERMEDIATE, menu, difficultyMenuItemsGroup, false);
        addDifficultyMenuItem(Difficulty.EXPERT, menu, difficultyMenuItemsGroup, false);
        menu.addSeparator();

        JMenuItem recordsTableMenuItem = new JMenuItem("Records Table");
        recordsTableMenuItem.addActionListener(e -> minesweeperController.requestRecordsLists());
        menu.add(recordsTableMenuItem);

        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(e -> minesweeperController.showAbout());
        menu.add(aboutMenuItem);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> requestGameExitConfirmation());
        menu.add(exitMenuItem);

        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
    }

    public void requestGameExitConfirmation() {
        int dialogResult = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit? All" +
                " progress will be lost", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (dialogResult == JOptionPane.YES_OPTION) {
            exitGame();
        }
    }

    private void addDifficultyMenuItem(Difficulty difficulty, JMenu menu, ButtonGroup buttonGroup, boolean selected) {
        JRadioButtonMenuItem difficultyMenuItem = new JRadioButtonMenuItem(difficulty.getName(), selected);
        difficultyMenuItem.addActionListener(e -> minesweeperController.startAnotherDifficultyNewGame(difficulty));
        buttonGroup.add(difficultyMenuItem);
        menu.add(difficultyMenuItem);
    }

    private JPanel createGamePanel(int rowsCount, int columnsCount, int minesCount) {
        JPanel newGamePanel = new JPanel(new GridBagLayout());
        createGameInfoPanel(columnsCount, minesCount, newGamePanel);
        createGameFieldPanel(rowsCount, columnsCount, newGamePanel);

        return newGamePanel;
    }

    private void createGameInfoPanel(int columnsCount, int minesCount, JPanel newGamePanel) {
        JPanel gameInfoPanel = new JPanel(new GridBagLayout());
        setSize(gameInfoPanel, new Dimension(CELL_SIDE_SIZE * columnsCount,
                GAME_INFO_PANEL_ELEMENTS_HEIGHT + INDICATOR_PANELS_VERTICAL_INDENT * 2));
        gameInfoPanel.setBorder(BorderFactory.createLoweredBevelBorder());

        createMinesLeftIndicatorPanel(minesCount, gameInfoPanel);
        createSmileyButton(gameInfoPanel);
        createTimerPanel(gameInfoPanel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(GAME_INFO_AND_GAME_FIELD_PANELS_INDENT,
                GAME_INFO_AND_GAME_FIELD_PANELS_INDENT,
                GAME_INFO_AND_GAME_FIELD_PANELS_INDENT,
                GAME_INFO_AND_GAME_FIELD_PANELS_INDENT);

        newGamePanel.add(gameInfoPanel, constraints);
    }

    private void createTimerPanel(JPanel gameInfoPanel) {
        JPanel timerPanel = createIndicatorPanel(gameInfoPanel, 2, 0, GridBagConstraints.LINE_END);
        JLabel timerLabel = new JLabel("Timer", SwingConstants.CENTER);
        timerPanel.add(timerLabel);
        timerValueLabel = new JLabel("0", SwingConstants.CENTER);
        timerPanel.add(timerValueLabel);
    }

    private void createSmileyButton(JPanel gameInfoPanel) {
        smileyButton = new JButton(SMILEY_BUTTON_ICON);
        setSize(smileyButton, new Dimension(GAME_INFO_PANEL_ELEMENTS_HEIGHT, GAME_INFO_PANEL_ELEMENTS_HEIGHT));
        smileyButton.setRolloverEnabled(false);
        smileyButton.addActionListener(e -> minesweeperController.startCurrentDifficultyNewGame());

        GridBagConstraints smileyButtonConstraints = new GridBagConstraints();
        smileyButtonConstraints.gridx = 1;
        smileyButtonConstraints.gridy = 0;
        smileyButtonConstraints.anchor = GridBagConstraints.CENTER;
        smileyButtonConstraints.weightx = 1;
        gameInfoPanel.add(smileyButton, smileyButtonConstraints);
    }

    private void createMinesLeftIndicatorPanel(int minesCount, JPanel gameInfoPanel) {
        JPanel minesLeftIndicatorPanel = createIndicatorPanel(gameInfoPanel, 0, 0, GridBagConstraints.LINE_START);
        JLabel minesLeftLabel = new JLabel("Mines Left", SwingConstants.CENTER);
        minesLeftIndicatorPanel.add(minesLeftLabel);
        minesLeftIndicator = new JLabel(Integer.toString(minesCount), SwingConstants.CENTER);
        minesLeftIndicatorPanel.add(minesLeftIndicator);
    }

    private void createGameFieldPanel(int rowsCount, int columnsCount, JPanel newGamePanel) {
        JPanel gameFieldPanel = new JPanel(new GridLayout(rowsCount, columnsCount, 0, 0));
        setSize(gameFieldPanel, new Dimension(CELL_SIDE_SIZE * columnsCount, CELL_SIDE_SIZE * rowsCount));

        cellButtons = new JButton[rowsCount][columnsCount];

        for (int i = 0; i < rowsCount; ++i) {
            for (int j = 0; j < columnsCount; j++) {
                JButton cellButton = createCellButton(i, j);
                gameFieldPanel.add(cellButton);
                cellButtons[i][j] = cellButton;
            }
        }

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 0, GAME_INFO_AND_GAME_FIELD_PANELS_INDENT, 0);

        newGamePanel.add(gameFieldPanel, constraints);
    }

    private JButton createCellButton(int i, int j) {
        JButton cellButton = new JButton(CELL_ICON);
        cellButton.setPressedIcon(PRESSED_CELL_ICON);
        cellButton.setBorderPainted(false);
        cellButton.setRolloverEnabled(false);

        cellButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                if (e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON2) {
                    minesweeperController.pressOnCell(i, j);
                }

                if (e.getButton() == MouseEvent.BUTTON3) {
                    minesweeperController.markOrUnmarkCell(i, j);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                if (e.getButton() == MouseEvent.BUTTON1) {
                    minesweeperController.openCell(i, j);
                }

                if (e.getButton() == MouseEvent.BUTTON2) {
                    minesweeperController.openAdjacentCells(i, j);
                }
            }
        });

        return cellButton;
    }

    private void setSize(JComponent component, Dimension size) {
        component.setPreferredSize(size);
        component.setMinimumSize(size);
        component.setMaximumSize(size);
    }

    private JPanel createIndicatorPanel(JPanel container, int gridx, int gridy, int anchor) {
        JPanel indicatorPanel = new JPanel(new GridLayout(2, 1));
        setSize(indicatorPanel, MINES_LEFT_AND_TIMER_PANELS_SIZE);
        indicatorPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.anchor = anchor;
        constraints.weightx = 1;
        constraints.insets = new Insets(0, 4, 0, 4);
        container.add(indicatorPanel, constraints);

        return indicatorPanel;
    }

    @Override
    public void startNewGame(int rowsCount, int columnsCount, int minesCount) {
        frame.remove(gamePanel);

        gamePanel = createGamePanel(rowsCount, columnsCount, minesCount);

        frame.add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void pressOnCell() {
        smileyButton.setIcon(PRESSED_ON_CELL_ICON);
    }

    @Override
    public void openNotMinedCell(List<OpenedCellData> openedCells) {
        for (OpenedCellData openedCellData : openedCells) {
            setCellIcons(openedCellData.getRow(), openedCellData.getColumn(),
                    OPENED_CELLS_IMAGES[openedCellData.getAdjacentMinedCellsCount()]);
        }

        smileyButton.setIcon(SMILEY_BUTTON_ICON);
    }

    private void setCellIcons(int row, int column, ImageIcon imageIcon) {
        JButton cellButton = cellButtons[row][column];
        cellButton.setIcon(imageIcon);
        cellButton.setPressedIcon(imageIcon);
    }

    @Override
    public void markCell(int row, int column, int minesLeftCountIndicatorValue) {
        setCellIcons(row, column, FLAG_ICON);
        minesLeftIndicator.setText(Integer.toString(minesLeftCountIndicatorValue));
    }

    @Override
    public void unmarkCell(int row, int column, int minesLeftCountIndicatorValue) {
        JButton cellButton = cellButtons[row][column];
        cellButton.setIcon(CELL_ICON);
        cellButton.setPressedIcon(PRESSED_CELL_ICON);
        minesLeftIndicator.setText(Integer.toString(minesLeftCountIndicatorValue));
    }

    @Override
    public void finishGameWithWin(List<CellData> notMarkedMinedCells) {
        setCellsIcon(notMarkedMinedCells, MINE_ICON);
        setButtonsPressedIconsToButtonsIcon();
        smileyButton.setIcon(WIN_ICON);
    }

    private void setCellsIcon(List<CellData> cells, ImageIcon mineIcon) {
        for (CellData cell : cells) {
            cellButtons[cell.getRow()][cell.getColumn()].setIcon(mineIcon);
        }
    }

    private void setButtonsPressedIconsToButtonsIcon() {
        for (JButton[] cellButton : cellButtons) {
            for (JButton cell : cellButton) {
                cell.setPressedIcon(cell.getIcon());
            }
        }
    }

    @Override
    public void finishGameWithLose(int row, int column, List<CellData> notOpenedNotMarkedMinedCells, List<CellData> wrongMarkedCells) {
        cellButtons[row][column].setIcon(EXPLODED_MINE_ICON);
        setCellsIcon(notOpenedNotMarkedMinedCells, MINE_ICON);
        setCellsIcon(wrongMarkedCells, WRONG_FLAG_ICON);
        setButtonsPressedIconsToButtonsIcon();
        smileyButton.setIcon(LOSE_ICON);
    }

    @Override
    public void updateTimer(int timerValue) {
        timerValueLabel.setText(Integer.toString(timerValue));
    }

    @Override
    public void requestRecordOwnerName() {
        int confirmDialogResult = JOptionPane.NO_OPTION;

        while (confirmDialogResult != JOptionPane.YES_OPTION) {
            String recordOwnerName = JOptionPane.showInputDialog(frame,
                    "Congratulations! You are set a new record!" + System.lineSeparator() +
                            "Enter your name:", "New record!", JOptionPane.PLAIN_MESSAGE);

            if (recordOwnerName != null && !recordOwnerName.isEmpty()) {
                if (recordOwnerName.length() <= RECORD_OWNER_NAME_LENGTH_LIMIT) {
                    sendRecordOwnerName(recordOwnerName);
                    break;
                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Name length must not exceed " + RECORD_OWNER_NAME_LENGTH_LIMIT + " characters",
                            null, JOptionPane.WARNING_MESSAGE);
                }
            } else {
                confirmDialogResult = JOptionPane.showConfirmDialog(frame,
                        "Are you sure? Your record will not be written in the record table", null,
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            }
        }
    }

    @Override
    public void sendRecordOwnerName(String recordOwnerName) {
        minesweeperController.writeRecord(recordOwnerName);
    }

    @Override
    public void showRecordTable(List<List<MinesweeperRecord>> recordsLists, int recordsTableSize) {
        DesktopMinesweeperViewRecordsTable recordsTable = new BaseDesktopMinesweeperViewRecordsTable();
        JOptionPane.showMessageDialog(frame, recordsTable.getRecordsTable(recordsLists,
                recordsTableSize), "Records Table", JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public void showAbout() {
        DesktopViewAbout desktopViewAbout = new BaseDesktopViewAbout();
        JOptionPane.showMessageDialog(frame, desktopViewAbout.getAbout(), "About",
                JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public void exitGame() {
        minesweeperController.exitGame();
    }

    /**
     * Вспомогательный метод для отладки
     */
    private void showMinedCells(List<CellData> minedCells) {
        setCellsIcon(minedCells, MINE_ICON);
    }
}
