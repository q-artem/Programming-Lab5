package models;

import utility.Validatable;

import java.util.Objects;

public class Car implements Validatable {
    private String name; //Поле не может быть null

    public String getName() {
        return name;
    }

    public static boolean validateName(String name) {
        return name != null && !name.isEmpty();
    }

    public boolean validate() {
        return validateName(name);
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public static class Builder {
        private String name;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Car build() {
            Car car = new Car();
            car.name = this.name;

            return car;
        }
    }
}