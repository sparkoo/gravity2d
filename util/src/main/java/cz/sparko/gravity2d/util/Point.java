package cz.sparko.gravity2d.util;

import java.util.Objects;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Point {
    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point move(Vector2d velocity) {
        return new Point(x + velocity.getX(), y + velocity.getY());
    }

    //  _      __________________________
    //   \    /          2             2
    //    \  /  (Xa - Xb)  +  (Ya - Yb)
    //     \/
    public double distance(Point p2) {
        return sqrt(pow((x - p2.getX()), 2) + pow((y - p2.getY()), 2));
    }

    public Vector2d direction(Point p2) {
        return new Vector2d(p2.getX() - x, p2.getY() - y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 &&
                Double.compare(point.y, y) == 0;
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
