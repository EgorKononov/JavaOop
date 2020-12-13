package ru.academits.java.kononov.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Csv {
    public static void convertCsvTableToHtmlTable(String inputFilePath, String outputFilePath) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream(inputFilePath));
             PrintWriter writer = new PrintWriter(outputFilePath)) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>Таблица</title>");
            writer.println("<meta charset=\"utf-8\">");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<table border=\"1\">");

            boolean isQuotesOpen = false;

            while (scanner.hasNextLine()) {
                String currentString = scanner.nextLine();

                if (currentString.length() > 0) {
                    if (!isQuotesOpen) {
                        writer.println("<tr>");
                        writer.print("<td>");
                    }

                    for (int i = 0; i < currentString.length(); ++i) {
                        switch (currentString.charAt(i)) {
                            case '"':
                                if (!isQuotesOpen) {
                                    isQuotesOpen = true;
                                } else {
                                    if (i < currentString.length() - 1 && currentString.charAt(i + 1) == '"') {
                                        writer.print("\"");
                                        ++i;
                                    } else {
                                        isQuotesOpen = false;
                                    }
                                }

                                break;
                            case ',':
                                if (!isQuotesOpen) {
                                    writer.print("</td><td>");
                                } else {
                                    writer.print(",");
                                }

                                break;
                            case '<':
                                writer.print("&lt;");
                                break;
                            case '>':
                                writer.print("&gt;");
                                break;
                            case '&':
                                writer.print("&amp;");
                                break;
                            default:
                                writer.print(currentString.charAt(i));
                        }
                    }

                    if (!isQuotesOpen) {
                        writer.println("</td>");
                        writer.println("</tr>");
                    } else {
                        writer.print("<br/>");
                    }
                }
            }

            writer.println("</table>");
            writer.println("</body>");
            writer.print("</html>");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 2) {
            System.out.println("Неверно заданы аргументы.");
            System.out.println("Необходимо задать агументы:");
            System.out.println("Аргумент 1 - путь к существующему файлу CSV таблицы");
            System.out.println("Аргумент 2 - путь к создаваемому HTML файлу");
        } else {
            convertCsvTableToHtmlTable(args[0], args[1]);
        }
    }
}