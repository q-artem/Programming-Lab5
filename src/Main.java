import commands.Add;
import managers.CollectionManager;
import models.Car;
import models.Coordinates;
import models.HumanBeing;
import managers.DumpManager;
import utility.console.Console;
import utility.console.StandartConsole;

import java.util.TreeMap;

import static models.WeaponType.HAMMER;

public class Main {
    public static void main(String[] args) {
        Console console = new StandartConsole();

        HumanBeing.Builder builder = new HumanBeing.Builder();
        builder.name("human");
        builder.coordinates(new Coordinates.Builder().x(0L).y(3F).build());
        builder.soundtrackName("soundtrack");
        builder.weaponType(HAMMER);
        builder.car(new Car.Builder().name("car").build());
        HumanBeing element = builder.build();

        if (!element.validate()) {
            System.out.println("Ошибка валидации объекта HumanBeing");
            return;
        }

        TreeMap<Integer, HumanBeing> humanBeings = new TreeMap<>();
        humanBeings.put(element.getId(), element);

        for (var e : humanBeings.values()) System.out.println(e);

//        HumanBeing human2 = null;
//        try {
//            human2 = HumanBeingCreator.createHumanBeing(console);
//        } catch (AskBreak e) {
//            throw new RuntimeException(e);
//        }
//        humanBeings.put(human2.getId(), human2);

        CollectionManager collectionManager = new CollectionManager(new DumpManager("test.xml", new StandartConsole()));

        Add add = new Add(console, collectionManager);
        console.println(add.apply(new String[]{"add"}));

//
//        for (var e : humanBeings.values()) System.out.println(e);
        collectionManager.saveCollection();
        console.print(collectionManager.toString());

        for (var e : collectionManager.getCollection().values()) System.out.println(e);
    }

}