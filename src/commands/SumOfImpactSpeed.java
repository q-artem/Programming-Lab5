package commands;

import commands.utils.Command;
import managers.CollectionManager;
import models.HumanBeing;
import utility.Describable;
import utility.Executable;
import utility.ExecutionResponse;
import utility.console.Console;

/**
 * Команда 'sum_of_impact_speed'. Выводит сумму значений поля impactSpeed для всех элементов коллекции.
 * Реализует интерфейсы {@link Executable} и {@link Describable}.
 */
public class SumOfImpactSpeed extends Command implements Executable, Describable {
    private final CollectionManager collectionManager;

    /**
     * Конструктор команды 'sum_of_impact_speed'.
     *
     * @param ignoredConsole    консоль (не используется в этой команде)
     * @param collectionManager менеджер коллекции для подсчёта суммы
     */
    public SumOfImpactSpeed(Console ignoredConsole, CollectionManager collectionManager) {
        super("sum_of_impact_speed", "вывести сумму значений поля impactSpeed для всех элементов коллекции");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду подсчёта суммы значений поля impactSpeed для всех элементов коллекции.
     * Обходит все элементы коллекции и суммирует их значения impactSpeed.
     *
     * @param arguments аргументы команды (не используются)
     * @return результат выполнения команды ({@link ExecutionResponse}):
     * строка с суммой impactSpeed
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {
        float sum = 0;
        for (HumanBeing human : collectionManager.getCollection().values()) {
            sum += human.getImpactSpeed();
        }
        return new ExecutionResponse("Сумма impactSpeed по всем элементам: " + sum);
    }
}
