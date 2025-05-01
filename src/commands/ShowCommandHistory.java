package commands;

import commands.utils.Command;
import managers.CommandManager;
import utility.Describable;
import utility.Executable;
import utility.ExecutionResponse;
import utility.console.Console;

import java.util.List;

/**
 * Команда 'command_history'. Выводит историю выполненных команд.
 */
public class ShowCommandHistory extends Command implements Executable, Describable {
    private final CommandManager commandManager;

    public ShowCommandHistory(Console ignoredConsole, CommandManager commandManager) {
        super("command_history", "вывести последние выполненные команды");
        this.commandManager = commandManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        List<String> history = commandManager.getCommandHistory();

        StringBuilder result = new StringBuilder("Последние выполненные команды:\n");
        for (int i = 0; i < history.size(); i++) {
            result.append(i + 1).append(". ").append(history.get(i)).append("\n");
        }

        return new ExecutionResponse(result.toString());
    }
}
