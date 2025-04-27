package models.creators;

import models.Coordinates;
import utility.console.Console;
import utility.console.StandartConsole;

public class CoordinatesCreator {
    private static final Console console = new StandartConsole();

    public static Coordinates createCoordinates() {
        Coordinates.Builder builder = new Coordinates.Builder();
        console.println("Инициализировано создание координат (coordinates). Координаты обязательны");

        builder.x(askX());
        builder.y(askY());
        return builder.build();

    }

    private static long askX() {
        Long x = null;
        while (!Coordinates.validateX(x)) {
            x = console.getUserValueLong("Введите координату x (x)");
        }
        return x;
    }

    private static Float askY() {
        return console.getUserValueFloat("Введите координату y (y)");
    }
}
