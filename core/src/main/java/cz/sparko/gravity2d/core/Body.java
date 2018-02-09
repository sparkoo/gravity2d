package cz.sparko.gravity2d.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

import cz.sparko.gravity2d.util.Point;
import cz.sparko.gravity2d.util.Vector2d;

public class Body {
    private static final Logger LOG = LoggerFactory.getLogger(Body.class);


    private static final double G = 6.674 * (10 ^ -11);

    private final Point position;

    private final Vector2d velocity;

    private final double mass;

    public Body(Point position, Vector2d velocity, double mass) {
        this.position = position;
        this.velocity = velocity;
        this.mass = mass;
    }

    public Body move(List<Body> bodies) {
        Vector2d force = bodies.stream()
                .filter(b -> !b.equals(this))
                .map(this::calculateGForce)
                .reduce(Vector2d::add)
                .map(f -> f.divide(mass))   //inertia
                .orElseThrow(() -> new RuntimeException("unable to calculate the Force"));

        Vector2d finalVelocity = velocity.subtract(force);
        return new Body(position.move(finalVelocity), finalVelocity, mass);
    }

    private Vector2d calculateGForce(Body b) {
        double forceScalar = G * ((mass * b.mass) / position.distance(b.position));
        Vector2d direction = position.direction(b.position);
        Vector2d unitDirection = direction.unit();
        return unitDirection.multiply(forceScalar);
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

    public double getMass() {
        return mass;
    }
}
