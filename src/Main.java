import models.Car;
import models.Coordinates;
import models.HumanBeing;
import managers.DumpManager;
import utility.console.StandartConsole;

import java.util.TreeMap;

import static models.WeaponType.HAMMER;

public class Main {
    public static void main(String[] args) {

        HumanBeing.Builder builder = new HumanBeing.Builder();
        builder.name("human");
        builder.coordinates(new Coordinates.Builder().x(0L).y(1f).build());
        builder.weaponType(HAMMER);
        builder.car(new Car.Builder().name("car").build());
        HumanBeing element = builder.build();

        TreeMap<Integer, HumanBeing> humanBeings = new TreeMap<>();
        humanBeings.put(element.getId(), element);

        for (var e : humanBeings.values()) System.out.println(e);

//        HumanBeing human2 = HumanBeingCreator.createHumanBeing("1111");
//        humanBeings.put(human2.getId(), human2);
//
//        for (var e : humanBeings.values()) System.out.println(e);
        DumpManager dumpManager = new DumpManager("test.xml", new StandartConsole());
        dumpManager.writeCollection(humanBeings);
        System.out.println(dumpManager.readCollection());
    }

}