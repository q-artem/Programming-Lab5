package utility.console;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class StandardConsole implements Console {
    private static final String prompt = "> ";
    private static Scanner fileScanner = null;
    private static Scanner defScanner = new Scanner(System.in);

    @Override
    public void print(Object obj) {
        System.out.print(obj);
    }

    @Override
    public void println(Object obj) {
        System.out.println(obj);
    }

    public void printError(Object obj) {
        System.err.println("Error: " + obj);
    }

    public String readln() throws NoSuchElementException, IllegalStateException {
        return (fileScanner != null ? fileScanner : defScanner).nextLine();
    }

    public boolean isCanReadln() throws IllegalStateException {
        return (fileScanner != null ? fileScanner : defScanner).hasNextLine();
    }

    @Override
    public void printTable(Object elementLeft, Object elementRight) {
        System.out.printf(" %-35s%-1s%n", elementLeft, elementRight);
    }

    @Override
    public void prompt() {
        System.out.print(prompt);
    }

    @Override
    public String getPrompt() {
        return prompt;
    }

    public void selectFileScanner(Scanner scanner) {
        fileScanner = scanner;
    }

    public void selectConsoleScanner() {
        fileScanner = null;
    }

    // Дополнительный метод для закрытия ресурсов
    public void close() {
        if (fileScanner != null) {
            fileScanner.close();
        }
    }
}