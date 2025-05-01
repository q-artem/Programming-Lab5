package commands;

import commands.utils.Command;
import managers.CollectionManager;
import models.HumanBeing;
import models.creators.HumanBeingCreator;
import utility.ExecutionResponse;
import utility.console.Console;

/**
 * Команда 'replace_if_greater'. Заменяет элемент по ключу, если новое значение больше старого.
 */
public class ReplaceIfGreater extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public ReplaceIfGreater(Console console, CollectionManager collectionManager) {
        super("replace_if_greater <key> {element}", "заменить значение по ключу, если новое значение больше старого");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments.length < 2) {
            return new ExecutionResponse(false, "Необходимо указать ключ!\nИспользование: '" + getName() + "'");
        }
        try {
            int key = Integer.parseInt(arguments[1]);
            HumanBeing oldHuman = collectionManager.getById(key);
            if (oldHuman == null) {
                return new ExecutionResponse(false, "Элемент с таким ключом не найден!");
            }
            HumanBeing newHuman = HumanBeingCreator.createHumanBeing(console, null);
            if (newHuman != null && newHuman.validate()) {
                if (compareHumanBeings(newHuman, oldHuman)) {
                    collectionManager.getCollection().remove(key);
                    collectionManager.getCollection().put(key, newHuman);
                    return new ExecutionResponse(true, "Элемент успешно заменён!");
                } else {
                    return new ExecutionResponse(false, "Новое значение не больше старого - замена не произведена.");
                }
            } else {
                return new ExecutionResponse(false, "Значения полей HumanBeing некорректны! Создание прервано.");
            }
        } catch (NumberFormatException e) {
            return new ExecutionResponse(false, "Ключ должен быть натуральным числом больше 0!");
        }
    }

    /**
     * Сравнивает два объекта HumanBeing по ImpactSpeed, MinutesOfWaiting и Name.
     *
     * @return true если newHuman больше oldHuman, false если меньше или если равны
     */
    private boolean compareHumanBeings(HumanBeing newHuman, HumanBeing oldHuman) {
        float speedCompare = newHuman.getImpactSpeed() - oldHuman.getImpactSpeed();
        if (speedCompare > 0) return true;

        double waitingCompare = (newHuman.getMinutesOfWaiting() != null ? newHuman.getMinutesOfWaiting() : 0) -
                (oldHuman.getMinutesOfWaiting() != null ? oldHuman.getMinutesOfWaiting() : 0);
        if (waitingCompare > 0) return true;

        return newHuman.getName().compareTo(oldHuman.getName()) > 0;
    }
}
