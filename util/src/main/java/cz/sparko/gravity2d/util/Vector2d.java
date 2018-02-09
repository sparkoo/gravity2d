package cz.sparko.gravity2d.util;

import java.util.Objects;

public class Vector2d {
    private final Point point;

    public Vector2d(double x, double y) {
        this.point = new Point(x, y);
    }

    public Vector2d(Point point) {
        this.point = point;
    }

    public Vector2d add(Vector2d v2) {
        return new Vector2d(this.point.getX() + v2.point.getX(),
                this.point.getY() + v2.point.getY());
    }

    public Vector2d subtract(Vector2d v2) {
        return new Vector2d(this.point.getX() - v2.point.getX(),
                this.point.getY() - v2.point.getY());
    }

    public Vector2d divide(double scalar) {
        return new Vector2d(point.getX() / scalar, point.getY() / scalar);
    }

    public Vector2d multiply(double scalar) {
        return new Vector2d(point.getX() * scalar, point.getY() * scalar);
    }

    public Vector2d reverseX() {
        return new Vector2d(point.getX() * -1, point.getY());
    }

    public Vector2d reverseY() {
        return new Vector2d(point.getX(), point.getY() * -1);
    }

    public double norm() {
        return point.distance(new Point(0, 0));
    }

    public Vector2d unit() {
        return this.divide(norm());
    }

    @Override
    public String toString() {
        return "Vector2d{" +
                "point=" + point +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Vector2d vector2d = (Vector2d) o;
        return Objects.equals(point, vector2d.point);
    }

    @Override
    public int hashCode() {

        return Objects.hash(point);
    }

    public Point getPoint() {
        return point;
    }

    public double getX() {
        return point.getX();
    }

    public double getY() {
        return point.getY();
    }
}
