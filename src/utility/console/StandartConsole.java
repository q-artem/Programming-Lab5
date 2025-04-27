package utility.console;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class StandartConsole implements Console {
    private static final String prompt = "-> ";
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

    private String getString(String mess) {
        this.println(mess);
        this.print(prompt);
        return this.readln();
    }

    @Override
    public void printTable(Object elementLeft, Object elementRight) {
        System.out.printf(" %-35s%-1s%n", elementLeft, elementRight);
    }

    public String getUserValueString(String mess) {
        return getString(mess);
    }

    public Float getUserValueFloat(String mess) {
        float val = 0f;
        boolean floatInput = true;
        while (floatInput) {
            try {
                String input = getString(mess);
                if (input.isEmpty()) {
                    return null;
                }
                val = Float.parseFloat(input);
                floatInput = false;
            } catch (NumberFormatException exception) {
                this.println(exception.getMessage());
            }
        }
        return val;
    }

    public Double getUserValueDouble(String mess) {
        double val = 0.0;
        boolean doubleInput = true;
        while (doubleInput) {
            try {
                String input = getString(mess);
                if (input.isEmpty()) {
                    return null;
                }
                val = Double.parseDouble(input);
                doubleInput = false;
            } catch (NumberFormatException exception) {
                this.println(exception.getMessage());
            }
        }
        return val;
    }

    public Long getUserValueLong(String mess) {
        long val = 0L;
        boolean longInput = true;
        while (longInput) {
            try {
                String input = getString(mess);
                if (input.isEmpty()) {
                    return null;
                }
                val = Long.parseLong(input);
                longInput = false;
            } catch (NumberFormatException exception) {
                this.println(exception.getMessage());
            }
        }
        return val;
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