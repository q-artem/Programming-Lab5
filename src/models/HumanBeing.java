package models;

import utility.Element;
import utility.Validatable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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

    public HumanBeing(Integer id, LocalDate date) {
        this.id = id;
        this.creationDate = date;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Boolean getRealHero() {
        return realHero;
    }

    public Boolean getHasToothpick() {
        return hasToothpick;
    }

    public float getImpactSpeed() {
        return impactSpeed;
    }

    public String getSoundtrackName() {
        return soundtrackName;
    }

    public Double getMinutesOfWaiting() {
        return minutesOfWaiting;
    }

    public WeaponType getWeaponType() {
        return weaponType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HumanBeing humanBeing = (HumanBeing) o;
        return Objects.equals(id, humanBeing.id) &&
                Objects.equals(name, humanBeing.name) &&
                Objects.equals(coordinates, humanBeing.coordinates) &&
                Objects.equals(creationDate, humanBeing.creationDate) &&
                Objects.equals(realHero, humanBeing.realHero) &&
                Objects.equals(hasToothpick, humanBeing.hasToothpick) &&
                Objects.equals(impactSpeed, humanBeing.impactSpeed) &&
                Objects.equals(soundtrackName, humanBeing.soundtrackName) &&
                Objects.equals(minutesOfWaiting, humanBeing.minutesOfWaiting) &&
                Objects.equals(weaponType, humanBeing.weaponType) &&
                Objects.equals(car, humanBeing.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, realHero, hasToothpick, impactSpeed, soundtrackName, minutesOfWaiting, weaponType, car);
    }

    public static class Builder {
        private boolean loadedFromFile = false;
        private Integer id = null;
        private LocalDate date = null;

        private String name = null;
        private Coordinates coordinates = new Coordinates();
        private Boolean realHero = null;
        private Boolean hasToothpick = null;
        private float impactSpeed = 1f;
        private String soundtrackName = null;
        private Double minutesOfWaiting = null;
        private WeaponType weaponType = null;
        private Car car = new Car();

        public Builder() {

        }

        public Builder(Integer id, LocalDate date) {
            loadedFromFile = true;
            this.id = id;
            this.date = date;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder coordinates(Coordinates coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        public Builder realHero(Boolean realHero) {
            this.realHero = realHero;
            return this;
        }

        public Builder hasToothpick(Boolean hasToothpick) {
            this.hasToothpick = hasToothpick;
            return this;
        }

        public Builder impactSpeed(float impactSpeed) {
            this.impactSpeed = impactSpeed;
            return this;
        }

        public Builder soundtrackName(String soundtrackName) {
            this.soundtrackName = soundtrackName;
            return this;
        }

        public Builder minutesOfWaiting(Double minutesOfWaiting) {
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
            HumanBeing humanBeing;
            if (loadedFromFile) {
                humanBeing = new HumanBeing(id, date);
            } else {
                humanBeing = new HumanBeing();
            }
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