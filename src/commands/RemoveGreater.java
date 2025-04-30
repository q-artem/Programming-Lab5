package commands;

import commands.utils.Command;
import managers.CollectionManager;
import models.HumanBeing;
import models.creators.HumanBeingCreator;
import utility.ExecutionResponse;
import utility.console.Console;

/**
 * Команда 'remove_greater'. Удаляет из коллекции все элементы, превышающие заданный.
 */
public class RemoveGreater extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public RemoveGreater(Console console, CollectionManager collectionManager) {
        super("remove_greater <key>", "удалить из коллекции все элементы, превышающие заданный по id элемент");
        this.console = console;
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
                collectionManager.getCollection().remove(h.getId());
                count++;
            }
        }
        return new ExecutionResponse("Удалено элементов: " + count);

        } catch (NumberFormatException e) {
            return new ExecutionResponse(false, "Ключ должен быть натуральным числом больше 0!");
        }
    }
}
