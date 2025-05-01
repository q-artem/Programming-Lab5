package models.creators;

import models.HumanBeing;
import models.WeaponType;
import utility.console.Console;

import java.time.LocalDate;
import java.util.Map;

public class HumanBeingCreator {
    static private final Map<String, Boolean> trueFalseMap = Map.of("1", true, "2", false);
    static private final Map<String, WeaponType> weaponTypes = Map.of("1", WeaponType.HAMMER, "2", WeaponType.AXE, "3", WeaponType.KNIFE);

    public static HumanBeing createHumanBeing(Console console, Integer id) {
        console.println("Инициализировано создание Персонажа (HumanBeing)");
        HumanBeing.Builder builder;
        if (id != null) {
            builder = new HumanBeing.Builder(id, LocalDate.now());
        } else {
            builder = new HumanBeing.Builder();
        }
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

    private static String askName(Console console) {
        String name = null;
        while (!HumanBeing.validateName(name)) {
            name = console.getUserValueString("Введите имя (name). Не может быть пустым");
            if (!HumanBeing.validateName(name)) {
                console.printError("Некорректный ввод! Попробуйте ещё раз");
            }
        }
        return name;
    }

    private static Boolean askRealHero(Console console) {
        Boolean realHero = null;
        String userRequest;
        while (realHero == null) {
            userRequest = console.getUserValueString("Введите является ли героем (realHero)? 1 - да, 2 - нет. По умолчанию - нет значения");
            if (userRequest.isEmpty()) {
                break;
            }
            realHero = trueFalseMap.get(userRequest);
            if (realHero == null) {
                console.printError("Некорректный ввод! Попробуйте ещё раз");
            }
        }
        return realHero;
    }

    private static Boolean askHasToothpick(Console console) {
        Boolean hasToothpick = null;
        String userRequest;
        while (hasToothpick == null) {
            userRequest = console.getUserValueString("Введите есть ли зубочистка (hasToothpick)? 1 - да, 2 - нет. По умолчанию - нет значения");
            if (userRequest.isEmpty()) {
                break;
            }
            hasToothpick = trueFalseMap.get(userRequest);
            if (hasToothpick == null) {
                console.printError("Некорректный ввод! Попробуйте ещё раз");
            }
        }
        return hasToothpick;
    }

    private static float askImpactSpeed(Console console) {
        Float impactSpeed = null;
        while (!HumanBeing.validateImpactSpeed(impactSpeed)) {
            impactSpeed = console.getUserValueFloat("Введите скорость удара (impactSpeed). Пример ввода: 3.14. Не может быть пустым");
            if (!HumanBeing.validateImpactSpeed(impactSpeed)) {
                console.printError("Некорректный ввод! Попробуйте ещё раз");
            }
        }
        return impactSpeed;
    }

    private static String askSoundtrackName(Console console) {
        String soundtrackName = null;
        while (!HumanBeing.validateSoundtrackName(soundtrackName)) {
            soundtrackName = console.getUserValueString("Введите название саундтрека (soundtrackName). Не может быть пустым");
            if (!HumanBeing.validateSoundtrackName(soundtrackName)) {
                console.printError("Некорректный ввод! Попробуйте ещё раз");
            }
        }
        return soundtrackName;
    }

    private static Double askMinutesOfWaiting(Console console) {
        Double minutesOfWaiting = -1.0;
        while (!HumanBeing.validateMinutesOfWaiting(minutesOfWaiting)) {
            minutesOfWaiting = console.getUserValueDouble("Введите время ожидания в минутах (minutesOfWaiting). Пример ввода: 2.71. По умолчанию - нет значения");
            if (!HumanBeing.validateMinutesOfWaiting(minutesOfWaiting)) {
                console.printError("Некорректный ввод! Попробуйте ещё раз");
            }
        }
        return minutesOfWaiting;
    }

    private static WeaponType askWeaponType(Console console) {
        WeaponType weaponType = null;
        String userRequest;
        while (weaponType == null) {
            userRequest = console.getUserValueString("Введите тип оружия (weaponType)? 1 - молот, 2 - топор, 3 - нож. Не может быть пустым");
            weaponType = weaponTypes.get(userRequest);
            if (weaponType == null) {
                console.printError("Некорректный ввод! Попробуйте ещё раз");
            }
        }
        return weaponType;
    }
}
