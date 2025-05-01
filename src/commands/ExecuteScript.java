package commands;

import commands.utils.Command;
import utility.ExecutionResponse;
import utility.console.Console;

/**
 * Команда 'execute_script'. Выполнить скрипт из файла.
 */
public class ExecuteScript extends Command {

    public ExecuteScript(Console ignoredConsole) {
        super("execute_script <file_name>", "выполнить скрипт из указанного файла");
    }

    /**
     * Выполняет команду
     *
     * @return Успешность выполнения команды.
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments[1].isEmpty())
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");

        return new ExecutionResponse("Выполнение скрипта '" + arguments[1] + "'...");
    }
}