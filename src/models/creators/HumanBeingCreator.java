package models.creators;

import models.HumanBeing;
import models.WeaponType;
import utility.console.Console;
import utility.console.StandartConsole;

import java.util.Map;

public class HumanBeingCreator {
    private static final Console console = new StandartConsole();

    static private final Map<String, Boolean> trueFalseMap = Map.of("1", true, "2", false);
    static private final Map<String, WeaponType> weaponTypes = Map.of("1", WeaponType.HAMMER, "2", WeaponType.AXE, "3", WeaponType.KNIFE);

    public static HumanBeing createHumanBeing(String mess) {
        console.println(mess);
        HumanBeing.Builder builder = new HumanBeing.Builder();
        builder.name(askName());
        builder.coordinates(CoordinatesCreator.createCoordinates());
        builder.realHero(askRealHero());
        builder.hasToothpick(askHasToothpick());
        builder.impactSpeed(askImpactSpeed());
        builder.soundtrackName(askSoundtrackName());
        builder.minutesOfWaiting(askMinutesOfWaiting());
        builder.weaponType(askWeaponType());
        builder.car(CarCreator.createCar());
        return builder.build();
    }

    private static String askName() {
        String name = null;
        while (!HumanBeing.validateName(name)) {
            name = console.getUserValueString("Введите имя (name). Не может быть пустым");
        }
        return name;
    }

    private static Boolean askRealHero() {
        Boolean realHero = null;
        String userRequest;
        while (realHero == null) {
            userRequest = console.getUserValueString("Введите является ли героем (realHero)? 1 - да, 2 - нет. По умолчанию - нет значения");
            if (userRequest.isEmpty()) {
                break;
            }
            realHero = trueFalseMap.get(userRequest);
        }
        return realHero;
    }

    private static Boolean askHasToothpick() {
        Boolean hasToothpick = null;
        String userRequest;
        while (hasToothpick == null) {
            userRequest = console.getUserValueString("Введите есть ли зубочистка (hasToothpick)? 1 - да, 2 - нет. По умолчанию - нет значения");
            if (userRequest.isEmpty()) {
                break;
            }
            hasToothpick = trueFalseMap.get(userRequest);
        }
        return hasToothpick;
    }

    private static float askImpactSpeed() {
        Float impactSpeed = null;
        while (!HumanBeing.validateImpactSpeed(impactSpeed)) {
            impactSpeed = console.getUserValueFloat("Введите скорость удара (impactSpeed). Пример ввода: 3.14. Не может быть пустым");
        }
        return impactSpeed;
    }

    private static String askSoundtrackName() {
        String soundtrackName = null;
        while (!HumanBeing.validateSoundtrackName(soundtrackName)) {
            soundtrackName = console.getUserValueString("Введите название саундтрека (soundtrackName). Не может быть пустым");
        }
        return soundtrackName;
    }

    private static Double askMinutesOfWaiting() {
        return console.getUserValueDouble("Введите время ожидания в минутах (minutesOfWaiting). Пример ввода: 2.71. По умолчанию - нет значения");
    }

    private static WeaponType askWeaponType() {
        WeaponType weaponType = null;
        String userRequest;
        while (weaponType == null) {
            userRequest = console.getUserValueString("Введите тип оружия (weaponType)? 1 - молот, 2 - топор, 3 - нож. По умолчанию - нет значения");
            if (userRequest.isEmpty()) {
                break;
            }
            weaponType = weaponTypes.get(userRequest);
        }
        return weaponType;
    }
}
