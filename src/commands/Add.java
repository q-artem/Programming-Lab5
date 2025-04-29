package commands;

import commands.utils.Command;
import managers.CollectionManager;
import models.HumanBeing;
import models.creators.HumanBeingCreator;
import utility.AskBreak;
import utility.ExecutionResponse;
import utility.console.Console;

/**
 * Команда 'add'. Добавляет новый элемент в коллекцию.
 */
public class Add extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Add(Console console, CollectionManager collectionManager) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды и сообщение об успешности.
     */
    @Override
    public ExecutionResponse apply(String[] argument) {
        try {
            if (!argument[1].isEmpty()) return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");

            HumanBeing humanBeing = HumanBeingCreator.createHumanBeing(console);

            if (humanBeing != null && humanBeing.validate()) {
                collectionManager.add(humanBeing);
                return new ExecutionResponse("HumanBeing успешно добавлен!");
            } else
                return new ExecutionResponse(false, "Поля HumanBeing не валидны! HumanBeing не создан!");
        } catch (AskBreak e) {
            return new ExecutionResponse(false, "Отмена...");
        }
    }
}