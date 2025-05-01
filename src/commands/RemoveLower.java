package commands;

import commands.utils.Command;
import managers.CollectionManager;
import models.HumanBeing;
import utility.ExecutionResponse;
import utility.console.Console;

/**
 * Команда 'remove_lower'. Удаляет из коллекции все элементы, меньшие, чем заданный по id элемент.
 */
public class RemoveLower extends Command {
    private final CollectionManager collectionManager;

    public RemoveLower(Console ignoredConsole, CollectionManager collectionManager) {
        super("remove_lower <key>", "удалить из коллекции все элементы, меньшие, чем заданный по id элемент");
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

            HumanBeing comparisonHumanBeing = collectionManager.getById(key);
            if (comparisonHumanBeing == null) {
                return new ExecutionResponse(false, "Элемента с таким ключом не существует!");
            }

            int count = 0;
            for (HumanBeing h : collectionManager.getCollection().values()) {
                if (h.compareTo(comparisonHumanBeing) < 0) {
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
