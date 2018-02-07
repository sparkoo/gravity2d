package cz.sparko.gravity2d.core;

import java.util.Objects;

public class Body {
    private int x;
    private int y;

    private int vx;
    private int vy;

    private int mass;

    public Body(int x, int y, int vx, int vy, int mass) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.mass = mass;
    }

    public void move(int maxX, int maxY) {
        if (x <= 0 || x >= maxX) {
            vx *= -1;
        }
        x += vx;

        if (y <= 0 || y >= maxY) {
            vy *= -1;
        }
        y += vy;
    }

    @Override
    public String toString() {
        return "Body{" +
                "x=" + x +
                ", y=" + y +
                ", vx=" + vx +
                ", vy=" + vy +
                ", mass=" + mass +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Body body = (Body) o;
        return x == body.x &&
                y == body.y &&
                vx == body.vx &&
                vy == body.vy &&
                mass == body.mass;
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y, vx, vy, mass);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVx() {
        return vx;
    }

    public int getVy() {
        return vy;
    }

    public int getMass() {
        return mass;
    }
}
