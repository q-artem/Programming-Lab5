package utility.console;

import java.util.Scanner;

/**
 * Консоль для ввода команд и вывода результата
 */
public interface Console {
    void print(Object obj);

    void println(Object obj);

    String readln();

    boolean isCanReadln();

    void printError(Object obj);

    void printTable(Object obj1, Object obj2);

    String getUserValueString(String mess);

    Float getUserValueFloat(String mess);

    Double getUserValueDouble(String mess);

    Long getUserValueLong(String mess);

    void prompt();

    String getPrompt();

    void selectFileScanner(Scanner obj);

    void selectConsoleScanner();
}