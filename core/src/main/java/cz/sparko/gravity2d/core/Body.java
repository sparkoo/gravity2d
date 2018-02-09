package cz.sparko.gravity2d.core;

import java.util.List;
import java.util.Objects;

import cz.sparko.gravity2d.util.Point;
import cz.sparko.gravity2d.util.Vector2d;

public class Body {
    private Point position;

    private Vector2d velocity;

    private final int mass;

    public Body(Point position, Vector2d velocity, int mass) {
        this.position = position;
        this.velocity = velocity;
        this.mass = mass;
    }

    public void move(List<Body> bodies, int maxX, int maxY) {
        if (position.getX() <= 0 || position.getX() >= maxX) {
//            this.velocity = this.velocity.reverseX();
        }
        if (this.position.getY() <= 0 || this.position.getY() >= maxY) {
//            this.velocity = this.velocity.reverseY();
        }

        position = position.move(this.velocity);
    }

    @Override
    public String toString() {
        return "Body{" +
                "position=" + position +
                ", velocity=" + velocity +
                ", mass=" + mass +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Body body = (Body) o;
        return mass == body.mass &&
                Objects.equals(position, body.position) &&
                Objects.equals(velocity, body.velocity);
    }

    @Override
    public int hashCode() {

        return Objects.hash(position, velocity, mass);
    }

    public Point getPosition() {
        return position;
    }

    public Vector2d getVelocity() {
        return velocity;
    }

    public int getMass() {
        return mass;
    }
}
