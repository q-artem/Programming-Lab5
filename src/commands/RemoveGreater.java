package commands;

import commands.utils.Command;
import managers.CollectionManager;
import models.HumanBeing;
import utility.ExecutionResponse;
import utility.console.Console;

/**
 * Команда 'remove_greater'. Удаляет из коллекции все элементы, превышающие заданный.
 */
public class RemoveGreater extends Command {
    private final CollectionManager collectionManager;

    public RemoveGreater(Console ignoredConsole, CollectionManager collectionManager) {
        super("remove_greater <key>", "удалить из коллекции все элементы, превышающие заданный по id элемент");
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

            HumanBeing comparationHumanBeing = collectionManager.getById(key);
            if (collectionManager.getById(key) == null) {
                return new ExecutionResponse(false, "Элемента с таким ключом не существует!");
            }

            int count = 0;
            for (HumanBeing h : collectionManager.getCollection().values()) {
                if (h.compareTo(comparationHumanBeing) > 0) {
                    if (collectionManager.remove(h.getId())) {
                        count++;
                    } else {
                        return new ExecutionResponse(false, "Ошибка!");
                    }
                }
            }
            return new ExecutionResponse("Удалено элементов: " + count);

        } catch (NumberFormatException e) {
            return new ExecutionResponse(false, "Ключ должен быть натуральным числом больше 0!");
        }
    }
}
