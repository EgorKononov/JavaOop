package ru.academits.java.kononov.temperatureconverter.view;

import ru.academits.java.kononov.temperatureconverter.controller.TemperatureConverterControllerInterface;
import ru.academits.java.kononov.temperatureconverter.model.scales.ScaleType;
import ru.academits.java.kononov.temperatureconverter.publisher.ConvertedTemperatureListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TemperatureConverterDesktopView implements TemperatureConverterView, ConvertedTemperatureListener {
    private static final DecimalFormat CONVERTED_TEMPERATURE_FORMAT = new DecimalFormat("##.00");
    private static final int TEMPERATURE_FIELD_WIDTH = 15;

    private final TemperatureConverterControllerInterface temperatureConverterController;
    private final ScaleType[] scaleTypes = ScaleType.values();
    private final Dimension panelsSize = new Dimension(160, 33 * (scaleTypes.length + 1));

    private JFrame frame;
    private JTextField initialTemperatureField;
    private JTextField convertedTemperatureField;
    private ButtonGroup initialTemperatureScalesButtonGroup;
    private ButtonGroup convertedTemperatureScalesButtonGroup;
    private boolean isConvertExecuted = false;

    public TemperatureConverterDesktopView(TemperatureConverterControllerInterface temperatureConverterController) {
        this.temperatureConverterController = temperatureConverterController;
    }

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }

            frame = new JFrame("Конвертер температур");
            frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            JPanel initialTemperaturePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            setPanelSizes(initialTemperaturePanel);
            addPanelBorder(initialTemperaturePanel, "Исходная величина");

            initialTemperatureField = new JTextField(TEMPERATURE_FIELD_WIDTH);
            initialTemperaturePanel.add(initialTemperatureField);

            List<JRadioButton> initialTemperatureScalesButtons = new ArrayList<>();
            initialTemperatureScalesButtonGroup = new ButtonGroup();
            createScalesButtons(initialTemperaturePanel, initialTemperatureScalesButtonGroup,
                    initialTemperatureScalesButtons, scaleTypes);

            JButton convertButton = new JButton("ПЕРЕВЕСТИ");
            convertButton.addActionListener(e -> {
                convertTemperature();
                isConvertExecuted = true;
            });

            JPanel convertedTemperaturePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            setPanelSizes(convertedTemperaturePanel);
            addPanelBorder(convertedTemperaturePanel, "Преобразованная величина");

            convertedTemperatureField = new JTextField(TEMPERATURE_FIELD_WIDTH);
            convertedTemperatureField.setText("0");
            convertedTemperatureField.setEditable(false);
            convertedTemperaturePanel.add(convertedTemperatureField);

            List<JRadioButton> convertedTemperatureScalesButtons = new ArrayList<>();
            convertedTemperatureScalesButtonGroup = new ButtonGroup();
            createScalesButtons(convertedTemperaturePanel, convertedTemperatureScalesButtonGroup,
                    convertedTemperatureScalesButtons, scaleTypes);

            frame.add(initialTemperaturePanel);
            frame.add(convertButton);
            frame.add(convertedTemperaturePanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private void setPanelSizes(JComponent component) {
        component.setPreferredSize(panelsSize);
        component.setMinimumSize(panelsSize);
        component.setMaximumSize(panelsSize);
    }

    private void addPanelBorder(JPanel panel, String borderTitle) {
        TitledBorder border = new TitledBorder(new LineBorder(Color.BLACK));
        border.setTitle(borderTitle);
        border.setTitleJustification(TitledBorder.CENTER);
        panel.setBorder(border);
    }

    private void createScalesButtons(JPanel panel, ButtonGroup buttonGroup,
                                     List<JRadioButton> buttonsList, ScaleType[] scaleTypes) {
        for (ScaleType st : scaleTypes) {
            buttonsList.add(createJRadioButton(st.getName(), st));
        }

        buttonsList.get(0).setSelected(true);

        for (JRadioButton rb : buttonsList) {
            buttonGroup.add(rb);
            panel.add(rb);
        }
    }

    private JRadioButton createJRadioButton(String text, ScaleType scaleType) {
        JRadioButton jRadioButton = new JRadioButton(text);
        jRadioButton.setActionCommand(scaleType.name());
        addActionListener(jRadioButton);
        return jRadioButton;
    }

    private void addActionListener(JRadioButton radioButton) {
        radioButton.addActionListener(e -> {
            if (isConvertExecuted) {
                convertTemperature();
            }
        });
    }

    private void convertTemperature() {
        try {
            temperatureConverterController.convertTemperature(Double.parseDouble(initialTemperatureField.getText()),
                    initialTemperatureScalesButtonGroup.getSelection().getActionCommand(),
                    convertedTemperatureScalesButtonGroup.getSelection().getActionCommand());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Необходимо ввести число в поле \"Исходная температура\"");
        }
    }

    @Override
    public void update(double convertedTemperature) {
        convertedTemperatureField.setText(CONVERTED_TEMPERATURE_FORMAT.format(convertedTemperature));
    }
}
