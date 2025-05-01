package commands;

import commands.utils.Command;
import utility.Describable;
import utility.Executable;
import utility.ExecutionResponse;
import utility.console.Console;

public class Exit extends Command implements Executable, Describable {

    public Exit(Console ignoredConsole) {
        super("exit", "завершить программу (без сохранения в файл)");
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

        return new ExecutionResponse("exit");
    }
}