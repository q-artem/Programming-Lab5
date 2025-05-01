package commands;

import commands.utils.Command;
import managers.CollectionManager;
import utility.Describable;
import utility.Executable;
import utility.ExecutionResponse;
import utility.console.Console;

public class Show extends Command implements Executable, Describable {
    private final CollectionManager collectionManager;

    public Show(Console ignoredConsole, CollectionManager collectionManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
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

        return new ExecutionResponse(collectionManager.toString());
    }
}