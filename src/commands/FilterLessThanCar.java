package commands;

import commands.utils.Command;
import managers.CollectionManager;
import models.Car;
import models.HumanBeing;
import utility.Describable;
import utility.Executable;
import utility.ExecutionResponse;
import utility.console.Console;

/**
 * Команда 'filter_less_than_car car'. Вывести элементы, значение поля car которых меньше заданного.
 */
public class FilterLessThanCar extends Command implements Executable, Describable {
    private final CollectionManager collectionManager;

    public FilterLessThanCar(Console ignoredConsole, CollectionManager collectionManager) {
        super("filter_less_than_car <car>", "вывести элементы, значение поля car которых меньше заданного");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments.length < 2) {
            return new ExecutionResponse(false, "Необходимо указать значение car!\nИспользование: '" + getName() + "'");
        }
        String carValue = arguments[1];

        Car compareCar = new Car.Builder().name(carValue).build();

        StringBuilder result = new StringBuilder();
        for (HumanBeing h : collectionManager.getCollection().values()) {
            if (h.getCar() != null && h.getCar().getName().compareTo(compareCar.getName()) < 0) {
                result.append(h).append("\n");
            }
        }
        String output = result.isEmpty() ? "Нет элементов, car которых меньше " + carValue : result.toString();
        return new ExecutionResponse(output);
    }
}
