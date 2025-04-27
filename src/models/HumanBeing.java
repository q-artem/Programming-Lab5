package models;

import utility.Element;
import utility.Validatable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HumanBeing extends Element implements Validatable {
    private final Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private final java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private boolean realHero;
    private boolean hasToothpick;
    private Float impactSpeed; //Поле не может быть null
    private String soundtrackName; //Поле не может быть null
    private double minutesOfWaiting;
    private WeaponType weaponType; //Поле не может быть null
    private Car car; //Поле может быть null

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

    public boolean validate() {
        if (id <= 0) return false;  // id always not null
        if (name == null || name.isEmpty()) return false;
        if (coordinates == null || !coordinates.validate()) return false;
        if (creationDate == null) return false;
        if (impactSpeed == null) return false;
        if (soundtrackName == null) return false;
        if (weaponType == null) return false;
        if (!car.validate()) return false;
        return true;
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
        return (Integer)(this.id - element.getId());
    }

    public static class Builder {
        private String name = null;
        private Coordinates coordinates = new Coordinates();
        boolean realHero = false;
        boolean hasToothpick = false;
        private Float impactSpeed = 1f;
        private String soundtrackName = "default_track";
        private double minutesOfWaiting = 1f;
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