package models.creators;

import models.Car;
import utility.console.Console;

public class CarCreator {
    public static Car createCar(Console console) {
        Car car = null;
        Car.Builder builder = new Car.Builder();
        console.println("Инициализировано создание машины (car)");

        boolean correctField = true;
        while (correctField) {
            switch (console.getUserValueString("Создать машину? 1 - да, 2 - нет")) {
                case ("1"):
                    builder.name(askName(console));
                    car = builder.build();
                    correctField = false;
                    break;
                case ("2"):
                    return null;
            }
        }
        return car;
    }

    private static String askName(Console console) {
        String name = null;
        while (!Car.validateName(name)) {
            name = console.getUserValueString("Введите название машины (name). Не может быть пустым");
        }
        return name;
    }
}
