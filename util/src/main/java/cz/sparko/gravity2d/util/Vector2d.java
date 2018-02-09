package cz.sparko.gravity2d.util;

import java.util.Objects;

public class Vector2d {
    private final double x;
    private final double y;

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2d add(Vector2d v2) {
        return new Vector2d(this.x + v2.x, this.y + v2.y);
    }

    public Vector2d subtract(Vector2d v2) {
        return new Vector2d(this.x - v2.x, this.y - v2.y);
    }

    public Vector2d reverseX() {
        return new Vector2d(x * -1, y);
    }

    public Vector2d reverseY() {
        return new Vector2d(x, y * -1);
    }

    @Override
    public String toString() {
        return "Vector2d{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Vector2d vector2d = (Vector2d) o;
        return Double.compare(vector2d.x, x) == 0 &&
                Double.compare(vector2d.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
