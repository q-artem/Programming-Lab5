package models;

import utility.Validatable;

public class Coordinates implements Validatable {
    private long x; //Значение поля должно быть больше -167, Поле не может быть null
    private Float y;

    public long getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public void setX(long x) {
        if (x <= -167) throw new IllegalArgumentException("X must be > -167");
        this.x = x;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public static boolean validateX(Long x) {
        return x != null && x > -167.0;
    }

    public boolean validate() {
        return validateX(x);
    }

    @Override
    public String toString() {
        return "(" + x + "; " + y + ")";
    }

    public static class Builder {
        private long x;
        private Float y;

        public Builder x(long x) {
            this.x = x;
            return this;
        }

        public Builder y(Float y) {
            this.y = y;
            return this;
        }

        public Coordinates build() {
            Coordinates coordinates = new Coordinates();
            coordinates.x = this.x;
            coordinates.y = this.y;
            return coordinates;
        }
    }
}
