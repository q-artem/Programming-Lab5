package models;

import utility.Validatable;

public class Coordinates implements Validatable {
    private Long x; //Значение поля должно быть больше -167, Поле не может быть null
    private float y;

    public Long getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(Long x) {
        if (x == null) throw new IllegalArgumentException("X can't be null");
        if (x <= -167) throw new IllegalArgumentException("X must be > -167");
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "; " + y + ")";
    }

    public boolean validate() {
        if (x <= -167) return false;  // x always not null
        return true;
    }

    public static class Builder {
        private Long x;
        private float y;

        public Builder x(Long x) {
            this.x = x;
            return this;
        }

        public Builder y(float y) {
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
