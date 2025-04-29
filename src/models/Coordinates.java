package models;

import utility.Validatable;

import java.util.Objects;

public class Coordinates implements Validatable {
    private long x; //Значение поля должно быть больше -167, Поле не может быть null
    private Float y;

    public long getX() {
        return x;
    }

    public Float getY() {
        return y;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordinates that = (Coordinates) obj;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
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
