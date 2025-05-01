package commands;

import commands.utils.Command;
import managers.CollectionManager;
import utility.Describable;
import utility.Executable;
import utility.ExecutionResponse;
import utility.console.Console;

/**
 * Команда 'remove'. Удалить элемент из коллекции по указанному ключу.
 */
public class RemoveKey extends Command implements Executable, Describable {
    private final CollectionManager collectionManager;

    public RemoveKey(Console ignoredConsole, CollectionManager collectionManager) {
        super("remove_key <key>", "Удалить из коллекции элемент с заданным ключом");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments.length < 2) {
            return new ExecutionResponse(false, "Ключ должен быть указан!\nИспользование: '" + getName() + "'");
        }

        try {
            int key = Integer.parseInt(arguments[1]);
            if (key < 1) throw new NumberFormatException();

            if (collectionManager.getById(key) == null) {
                return new ExecutionResponse(false, "Элемента с таким ключом не существует!");
            }

            if (collectionManager.remove(key)) {
                return new ExecutionResponse("HumanBeing с ключом " + key + " успешно удалён из коллекции!");
            } else {
                return new ExecutionResponse(false, "Ошибка!");
            }
        } catch (NumberFormatException e) {
            return new ExecutionResponse(false, "Ключ должен быть натуральным числом больше 0!");
        }
    }
}
