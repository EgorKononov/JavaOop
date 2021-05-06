package ru.academits.java.kononov.minesweeper.view.about;

import javax.swing.*;

public class BaseDesktopViewAbout implements DesktopViewAbout{
    private static final String ABOUT_TEXT = "Version of classic game \"Minesweeper\"" + System.lineSeparator() +
            "Study project for Academ IT School" + System.lineSeparator() + System.lineSeparator() +
            "Developer: Egor Kononov" + System.lineSeparator() +
            "2021";

    public BaseDesktopViewAbout() {
    }

    @Override
    public JPanel getAbout() {
        JPanel aboutPanel = new JPanel();
        JTextArea textArea = new JTextArea(ABOUT_TEXT);
        textArea.setEditable(false);
        aboutPanel.add(textArea);

        return aboutPanel;
    }
}
