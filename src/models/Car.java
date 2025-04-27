package models;

import utility.Validatable;

public class Car implements Validatable {
    private String name; //Поле не может быть null

    @Override
    public String toString() {
        return name;
    }

    public boolean validate() {
        if (name == null) return false;
        return true;
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