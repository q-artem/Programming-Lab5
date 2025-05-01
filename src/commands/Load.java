package commands;

import commands.utils.Command;
import managers.CollectionManager;
import utility.Describable;
import utility.Executable;
import utility.ExecutionResponse;
import utility.console.Console;

/**
 * Команда 'load'. Перезагружает коллекцию из файла.
 */
public class Load extends Command implements Executable, Describable {
    private final CollectionManager collectionManager;

    public Load(Console ignoredConsole, CollectionManager collectionManager) {
        super("load", "перезагрузить коллекцию из файла");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
        }

        try {
            collectionManager.loadCollection();
            return new ExecutionResponse("Коллекция успешно перезагружена из файла!");
        } catch (Exception e) {
            return new ExecutionResponse(false, "Ошибка при загрузке коллекции: " + e.getMessage());
        }
    }
}
