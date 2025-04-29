package models.creators;

import models.HumanBeing;
import models.WeaponType;
import utility.AskBreak;
import utility.console.Console;

import java.util.Map;

public class HumanBeingCreator {
    static private final Map<String, Boolean> trueFalseMap = Map.of("1", true, "2", false);
    static private final Map<String, WeaponType> weaponTypes = Map.of("1", WeaponType.HAMMER, "2", WeaponType.AXE, "3", WeaponType.KNIFE);

    public static HumanBeing createHumanBeing(Console console) throws AskBreak {
        console.println("Инициализировано создание Персонажа (HumanBeing)");
        HumanBeing.Builder builder = new HumanBeing.Builder();
        builder.name(askName(console));
        builder.coordinates(CoordinatesCreator.createCoordinates(console));
        builder.realHero(askRealHero(console));
        builder.hasToothpick(askHasToothpick(console));
        builder.impactSpeed(askImpactSpeed(console));
        builder.soundtrackName(askSoundtrackName(console));
        builder.minutesOfWaiting(askMinutesOfWaiting(console));
        builder.weaponType(askWeaponType(console));
        builder.car(CarCreator.createCar(console));
        return builder.build();
    }

    private static String askName(Console console) throws AskBreak {
        String name = null;
        while (!HumanBeing.validateName(name)) {
            name = console.getUserValueString("Введите имя (name). Не может быть пустым");
        }
        return name;
    }

    private static Boolean askRealHero(Console console) throws AskBreak {
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

    private static Boolean askHasToothpick(Console console) throws AskBreak {
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

    private static float askImpactSpeed(Console console) throws AskBreak {
        Float impactSpeed = null;
        while (!HumanBeing.validateImpactSpeed(impactSpeed)) {
            impactSpeed = console.getUserValueFloat("Введите скорость удара (impactSpeed). Пример ввода: 3.14. Не может быть пустым");
        }
        return impactSpeed;
    }

    private static String askSoundtrackName(Console console) throws AskBreak {
        String soundtrackName = null;
        while (!HumanBeing.validateSoundtrackName(soundtrackName)) {
            soundtrackName = console.getUserValueString("Введите название саундтрека (soundtrackName). Не может быть пустым");
        }
        return soundtrackName;
    }

    private static Double askMinutesOfWaiting(Console console) throws AskBreak {
        return console.getUserValueDouble("Введите время ожидания в минутах (minutesOfWaiting). Пример ввода: 2.71. По умолчанию - нет значения");
    }

    private static WeaponType askWeaponType(Console console) throws AskBreak {
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
