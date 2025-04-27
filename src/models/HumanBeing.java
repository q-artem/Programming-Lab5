package models;

import utility.Element;
import utility.Validatable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Тип данных полей не соответствует комментариям. Тип данных был изменён.
public class HumanBeing extends Element implements Validatable {
    private final Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private final LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Boolean realHero;
    private Boolean hasToothpick;
    private float impactSpeed; //Поле не может быть null
    private String soundtrackName; //Поле не может быть null
    private Double minutesOfWaiting;
    private WeaponType weaponType; //Поле не может быть null
    private Car car; //Поле МОЖЕТ быть null

    public HumanBeing() {
        this.id = IdGenerator.assigntHumanBeingId();
        this.creationDate = LocalDate.now();
    }

    @Override
    public Integer getId() {
        return id;
    }

    public Car getCar() {
        return car;
    }

    public static boolean validateId(Integer id) {
        return id > 0;  // id always not null
    }

    public static boolean validateName(String name) {
        return name != null && !name.isEmpty();
    }

    public static boolean validateCoordinates(Coordinates coordinates) {
        return coordinates != null && coordinates.validate();
    }

    public static boolean validateCreationDate(java.time.LocalDate creationDate) {
        return creationDate != null;
    }

    public static boolean validateImpactSpeed(Float impactSpeed) {
        return impactSpeed != null;
    }

    public static boolean validateSoundtrackName(String soundtrackName) {
        return soundtrackName != null;
    }

    public static boolean validateWeaponType(WeaponType weaponType) {
        return weaponType != null;
    }

    public static boolean validateCar(Car car) {
        return car.validate();
    }


    public boolean validate() {
        return HumanBeing.validateId(id) &&
                validateName(name) &&
                validateCoordinates(coordinates) &&
                validateCreationDate(creationDate) &&
                // impactSpeed never been null
                validateSoundtrackName(soundtrackName) &&
                validateWeaponType(weaponType) &&
                validateCar(car);
    }

    @Override
    public String toString() {
        return "HumanBeing{\"id\": " + id + ", " +
                "\"name\": \"" + name + "\", " +
                "\"coordinates\": " + coordinates + ", " +
                "\"creationDate\": \"" + creationDate.format(DateTimeFormatter.ISO_DATE) + "\", " +
                "\"realHero\": " + realHero + ", " +
                "\"hasToothpick\": " + hasToothpick + ", " +
                "\"impactSpeed\": " + impactSpeed + ", " +
                "\"soundtrackName\": \"" + soundtrackName + "\", " +
                "\"minutesOfWaiting\": " + minutesOfWaiting + ", " +
                "\"weaponType\": \"" + weaponType + "\", " +
                "\"car\": " + (car == null ? "null" : car) +
                "}";
    }

    @Override
    public int compareTo(Element element) {
        return this.id - element.getId();
    }

    public static class Builder {
        private String name = null;
        private Coordinates coordinates = new Coordinates();
        private Boolean realHero = false;
        private Boolean hasToothpick = false;
        private float impactSpeed = 1f;
        private String soundtrackName = "default_track";
        private Double minutesOfWaiting = 1.0;
        private WeaponType weaponType = null;
        private Car car = new Car();

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder coordinates(Coordinates coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        public Builder realHero(boolean realHero) {
            this.realHero = realHero;
            return this;
        }

        public Builder hasToothpick(boolean hasToothpick) {
            this.hasToothpick = hasToothpick;
            return this;
        }


        public Builder impactSpeed(Float impactSpeed) {
            this.impactSpeed = impactSpeed;
            return this;
        }

        public Builder soundtrackName(String soundtrackName) {
            this.soundtrackName = soundtrackName;
            return this;
        }

        public Builder minutesOfWaiting(double minutesOfWaiting) {
            this.minutesOfWaiting = minutesOfWaiting;
            return this;
        }

        public Builder weaponType(WeaponType weaponType) {
            this.weaponType = weaponType;
            return this;
        }

        public Builder car(Car car) {
            this.car = car;
            return this;
        }

        public HumanBeing build() {
            HumanBeing humanBeing = new HumanBeing();
            humanBeing.name = this.name;
            humanBeing.coordinates = this.coordinates;
            humanBeing.realHero = this.realHero;
            humanBeing.hasToothpick = this.hasToothpick;
            humanBeing.impactSpeed = this.impactSpeed;
            humanBeing.soundtrackName = this.soundtrackName;
            humanBeing.minutesOfWaiting = this.minutesOfWaiting;
            humanBeing.weaponType = this.weaponType;
            humanBeing.car = this.car;
            return humanBeing;
        }
    }
}