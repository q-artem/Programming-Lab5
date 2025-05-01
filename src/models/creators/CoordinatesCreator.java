package models.creators;

import models.Coordinates;
import utility.console.Console;

public class CoordinatesCreator {
    public static Coordinates createCoordinates(Console console) {
        Coordinates.Builder builder = new Coordinates.Builder();
        console.println("Инициализировано создание координат (coordinates). Координаты обязательны");
        builder.x(askX(console));
        builder.y(askY(console));
        return builder.build();
    }

    private static long askX(Console console) {
        Long x = null;
        while (!Coordinates.validateX(x)) {
            x = console.getUserValueLong("Введите координату x (x). Пример ввода: 314. Должно быть больше -167. Не может быть пустым");
            if (!Coordinates.validateX(x)) {
                console.printError("Некорректный ввод! Попробуйте ещё раз");
            }
        }
        return x;
    }

    private static Float askY(Console console) {
        return console.getUserValueFloat("Введите координату y (y). Пример ввода: 3.14. По умолчанию - нет значения");
    }
}
