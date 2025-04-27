import models.Car;
import models.Coordinates;
import models.HumanBeing;
import utility.console.Console;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.TreeMap;

import static models.WeaponType.HAMMER;

public class Main
{
    public static void main(String[] args) {

        HumanBeing.Builder builder = new HumanBeing.Builder();
        builder.name("human");
        builder.coordinates(new Coordinates.Builder().x(0L).y(1).build());
        builder.weaponType(HAMMER);
        builder.car(new Car.Builder().name("car").build());
        HumanBeing element = builder.build();

        TreeMap<Integer, HumanBeing> humanBeings = new TreeMap<>();
        humanBeings.put(element.getId(), element);

        for (var e: humanBeings.values()) System.out.println(e);
    }
}

class Ask {
    public static class AskBreak extends Exception {}

    public static HumanBeing askHumanBeing(Console console) throws AskBreak {

        try {
            String name;
            while (true) {
                console.print("name: ");
                name = console.readln().trim();
                if (name.equals("exit")) throw new AskBreak();
                if (!name.isEmpty()) break;
            }
            var coordinates = askCoordinates(console);
            var weaponType = askWeaponType(console);
            return new Aboba(id, name, coordinates, weaponType);
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    public static Coordinates askCoordinates(Console console) throws AskBreak {
        try {
            int x;
            while (true) {
                console.print("coordinates.x: ");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (!line.equals("")) {
                    try { x = Integer.parseInt(line); break; }catch(NumberFormatException e) { }
                }
            }
            float y;
            while (true) {
                console.print("coordinates.y: ");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (!line.equals("")) {
                    try { y = Float.parseFloat(line); break; }catch(NumberFormatException e) { }
                }
            }
            return new Coordinates(x, y);
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    public static WeaponType askWeaponType(Console console) throws AskBreak {
        try {
            WeaponType r;
            while (true) {
                console.print("WeaponType ("+WeaponType.names()+"): ");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (!line.equals("")) {
                    try {

                        r = WeaponType.valueOf(line); break;

                    } catch (NullPointerException | IllegalArgumentException  e) { }
                } else return null;
            }
            return r;
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }
}