package models;

import utility.Validatable;

public class Car implements Validatable {
    private String name; //Поле не может быть null

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