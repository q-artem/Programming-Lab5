package models.creators;

import models.Car;
import utility.console.Console;
import utility.console.StandartConsole;

public class CarCreator {
    private static final Console console = new StandartConsole();

    public static Car createCar() {
        Car car = null;
        Car.Builder builder = new Car.Builder();
        console.println("Инициализировано создание машины (car)");

        boolean correctField = true;
        while (correctField) {

            switch (console.getUserValueString("Создать машину? 1 - да, 2 - нет")) {
                case ("1"):
                    builder.name(askName());
                    car = builder.build();
                    correctField = false;
                    break;
                case ("2"):
                    return null;
            }
        }

        return car;
    }

    private static String askName() {
        String name = null;
        while (!Car.validateName(name)) {
            name = console.getUserValueString("Введите название машины (name). Не может быть пустым");
        }
        return name;
    }
}
