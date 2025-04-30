package commands;

import commands.utils.Command;
import managers.CollectionManager;
import models.Car;
import models.HumanBeing;
import utility.ExecutionResponse;
import utility.console.Console;

import java.util.stream.Collectors;

/**
 * Команда 'filter_less_than_car car'. Вывести элементы, значение поля car которых меньше заданного.
 */
public class FilterLessThanCar extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public FilterLessThanCar(Console console, CollectionManager collectionManager) {
        super("filter_less_than_car <car>", "вывести элементы, значение поля car которых меньше заданного");
        this.console = console;
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
