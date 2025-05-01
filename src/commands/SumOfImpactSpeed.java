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
 */
public class SumOfImpactSpeed extends Command implements Executable, Describable {
    private final CollectionManager collectionManager;

    public SumOfImpactSpeed(Console ignoredConsole, CollectionManager collectionManager) {
        super("sum_of_impact_speed", "вывести сумму значений поля impactSpeed для всех элементов коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        float sum = 0;
        for (HumanBeing human : collectionManager.getCollection().values()) {
            sum += human.getImpactSpeed();
        }
        return new ExecutionResponse("Сумма impactSpeed по всем элементам: " + sum);
    }
}
