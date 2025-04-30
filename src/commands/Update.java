package commands;

import commands.utils.Command;
import managers.CollectionManager;
import models.HumanBeing;
import models.creators.HumanBeingCreator;
import utility.ExecutionResponse;
import utility.console.Console;

/**
 * Команда 'update'. Обновляет значение элемента коллекции, id которого равен заданному.
 */
public class Update extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Update(Console console, CollectionManager collectionManager) {
        super("update <key> {element}", "Обновить значение элемента коллекции, id которого равен заданному");
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

            HumanBeing humanBeing = HumanBeingCreator.createHumanBeing(console, key);

            if (humanBeing != null && humanBeing.validate()) {
                collectionManager.getCollection().remove(key);
                collectionManager.getCollection().put(key, humanBeing);
                return new ExecutionResponse("HumanBeing по ключу " + key + " успешно обновлён!");
            } else {
                return new ExecutionResponse(false, "Значения полей HumanBeing некорректны! Создание прервано.");
            }
        } catch (NumberFormatException e) {
            return new ExecutionResponse(false, "Ключ должен быть натуральным числом больше 0!");
        }
    }
}
