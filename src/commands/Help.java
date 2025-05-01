package commands;

import commands.utils.Command;
import managers.CommandManager;
import utility.Describable;
import utility.Executable;
import utility.ExecutionResponse;
import utility.console.Console;

import java.util.stream.Collectors;

public class Help extends Command implements Executable, Describable {
    private final CommandManager commandManager;

    public Help(Console ignoredConsole, CommandManager commandManager) {
        super("help", "вывести справку по доступным командам");
        this.commandManager = commandManager;
    }

    /**
     * Выполняет команду
     *
     * @return Успешность выполнения команды.
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (!arguments[1].isEmpty())
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");

        return new ExecutionResponse(commandManager.getCommands().values().stream().map(command -> String.format(" %-35s%-1s%n", command.getName(), command.getDescription())).collect(Collectors.joining("\n")));
    }
}