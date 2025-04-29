package commands;

import commands.utils.Command;
import managers.CollectionManager;
import utility.ExecutionResponse;
import utility.console.Console;

/**
 * Команда 'remove'. Удалить элемент из коллекции по указанному ключу.
 */
public class Remove extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Remove(Console console, CollectionManager collectionManager) {
        super("remove <key>", "Удалить из коллекции элемент с заданным ключом");
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

            if (collectionManager.getById(key) == null) {
                return new ExecutionResponse(false, "Элемента с таким ключом не существует!");
            }

            collectionManager.getCollection().remove(key);
            return new ExecutionResponse("HumanBeing с ключом " + key + " успешно удалён из коллекции!");

        } catch (NumberFormatException e) {
            return new ExecutionResponse(false, "Ключ должен быть натуральным числом больше 0!");
        }
    }
}
