package commands;

import commands.utils.Command;
import managers.CollectionManager;
import utility.ExecutionResponse;
import utility.console.Console;

/**
 * Команда 'save'. Сохраняет коллекцию в файл.
 */
public class Save extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Save(Console console, CollectionManager collectionManager) {
        super("save", "сохранить коллекцию в файл");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
        }

        try {
            collectionManager.saveCollection();
            return new ExecutionResponse("Коллекция успешно сохранена в файл!");
        } catch (Exception e) {
            return new ExecutionResponse(false, "Ошибка при сохранении коллекции: " + e.getMessage());
        }
    }
}
