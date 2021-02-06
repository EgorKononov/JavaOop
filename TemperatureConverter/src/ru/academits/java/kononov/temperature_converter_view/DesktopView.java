package ru.academits.java.kononov.temperature_converter_view;

import ru.academits.java.kononov.temperature_converter_controller.Controller;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.DecimalFormat;

public class DesktopView implements View {
    private JFrame frame;
    private JTextField initialTemperatureField;
    private JTextField convertedTemperatureField;
    private ButtonGroup initialTemperatureScales;
    private ButtonGroup convertedTemperatureScales;
    private boolean isConvertExecuted = false;
    private final Controller controller;

    public DesktopView(Controller controller) {
        this.controller = controller;
    }

    private void setSizes(JComponent component, Dimension size) {
        component.setPreferredSize(size);
        component.setMinimumSize(size);
        component.setMaximumSize(size);
    }

    private void addActionListener(JRadioButton radioButton) {
        radioButton.addActionListener(e -> {
            if (isConvertExecuted) {
                convert();
            }
        });
    }

    private void convert() {
        try {
            double convertedTemperature = controller.convertTemperature(Double.parseDouble(initialTemperatureField.getText()),
                    initialTemperatureScales.getSelection().getActionCommand(), convertedTemperatureScales.getSelection().getActionCommand());

            DecimalFormat convertedTemperatureFormat = new DecimalFormat("##.00");
            convertedTemperatureField.setText(convertedTemperatureFormat.format(convertedTemperature));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Необходимо ввести число в поле \"Исходная температура\"");
        }
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
            frame.setMinimumSize(new Dimension(600, 245));
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

            Dimension panelsSize = new Dimension(160, 130);
            JPanel initialTemperaturePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            setSizes(initialTemperaturePanel, panelsSize);

            initialTemperatureField = new JTextField(15);
            initialTemperaturePanel.add(initialTemperatureField);

            String celsiusString = "Градусы Цельсия";
            String fahrenheitString = "Градусы Фаренгейта";
            String kelvinString = "Градусы Кельвина";

            JRadioButton initialTemperatureCelsiusButton = new JRadioButton(celsiusString, true);
            initialTemperatureCelsiusButton.setActionCommand(celsiusString);
            addActionListener(initialTemperatureCelsiusButton);

            JRadioButton initialTemperatureFahrenheitButton = new JRadioButton(fahrenheitString);
            initialTemperatureFahrenheitButton.setActionCommand(fahrenheitString);
            addActionListener(initialTemperatureFahrenheitButton);

            JRadioButton initialTemperatureKelvinButton = new JRadioButton(kelvinString);
            initialTemperatureKelvinButton.setActionCommand(kelvinString);
            addActionListener(initialTemperatureKelvinButton);

            initialTemperatureScales = new ButtonGroup();
            initialTemperatureScales.add(initialTemperatureCelsiusButton);
            initialTemperatureScales.add(initialTemperatureFahrenheitButton);
            initialTemperatureScales.add(initialTemperatureKelvinButton);

            initialTemperaturePanel.add(initialTemperatureCelsiusButton);
            initialTemperaturePanel.add(initialTemperatureFahrenheitButton);
            initialTemperaturePanel.add(initialTemperatureKelvinButton);

            TitledBorder initialTemperaturePanelBorder = new TitledBorder(new LineBorder(Color.BLACK));
            initialTemperaturePanelBorder.setTitle("Исходная величина");
            initialTemperaturePanelBorder.setTitleJustification(TitledBorder.CENTER);
            initialTemperaturePanel.setBorder(initialTemperaturePanelBorder);

            JButton convertButton = new JButton("ПЕРЕВЕСТИ");
            convertButton.addActionListener(e -> {
                convert();
                isConvertExecuted = true;
            });

            JPanel convertedTemperaturePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            setSizes(convertedTemperaturePanel, panelsSize);

            convertedTemperatureField = new JTextField(15);
            convertedTemperatureField.setText("0");
            convertedTemperatureField.setEditable(false);
            convertedTemperaturePanel.add(convertedTemperatureField);

            JRadioButton convertedTemperatureCelsiusButton = new JRadioButton(celsiusString);
            convertedTemperatureCelsiusButton.setActionCommand(celsiusString);
            addActionListener(convertedTemperatureCelsiusButton);

            JRadioButton convertedTemperatureFahrenheitButton = new JRadioButton(fahrenheitString, true);
            convertedTemperatureFahrenheitButton.setActionCommand(fahrenheitString);
            addActionListener(convertedTemperatureFahrenheitButton);

            JRadioButton convertedTemperatureKelvinButton = new JRadioButton(kelvinString);
            convertedTemperatureKelvinButton.setActionCommand(kelvinString);
            addActionListener(convertedTemperatureKelvinButton);

            convertedTemperatureScales = new ButtonGroup();
            convertedTemperatureScales.add(convertedTemperatureCelsiusButton);
            convertedTemperatureScales.add(convertedTemperatureFahrenheitButton);
            convertedTemperatureScales.add(convertedTemperatureKelvinButton);

            convertedTemperaturePanel.add(convertedTemperatureCelsiusButton);
            convertedTemperaturePanel.add(convertedTemperatureFahrenheitButton);
            convertedTemperaturePanel.add(convertedTemperatureKelvinButton);

            TitledBorder convertedTemperaturePanelBorder = new TitledBorder(new LineBorder(Color.BLACK));
            convertedTemperaturePanelBorder.setTitle("Преобразованная величина");
            convertedTemperaturePanelBorder.setTitleJustification(TitledBorder.CENTER);
            convertedTemperaturePanel.setBorder(convertedTemperaturePanelBorder);

            frame.add(initialTemperaturePanel);
            frame.add(convertButton);
            frame.add(convertedTemperaturePanel);
        });
    }
}