package utility.console;

import utility.AskBreak;

import java.util.Scanner;

/**
 * Консоль для ввода команд и вывода результата
 */
public interface Console {
    void print(Object obj);

    void println(Object obj);

    String readln() throws AskBreak;

    boolean isCanReadln();

    void printError(Object obj);

    void printTable(Object obj1, Object obj2);

    String getUserValueString(String mess) throws AskBreak;

    Float getUserValueFloat(String mess) throws AskBreak;

    Double getUserValueDouble(String mess) throws AskBreak;

    Long getUserValueLong(String mess) throws AskBreak;

    void prompt();

    String getPrompt();

    void selectFileScanner(Scanner obj);

    void selectConsoleScanner();
}