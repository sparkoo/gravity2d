package cz.sparko.gravity2d.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import cz.sparko.gravity2d.util.Point;
import cz.sparko.gravity2d.util.Vector2d;
import javafx.scene.paint.Color;

public class Body {
    private static final Logger LOG = LoggerFactory.getLogger(Body.class);


    private static AtomicLong counter = new AtomicLong(0L);
    private static final double G = 6.674 * (10 ^ -11);
    private static final Random r = new Random();

    private final long id;

    private final Point position;

    private final Vector2d velocity;

    private final double mass;
    private final Color color;

    private Body(long id, Point position, Vector2d velocity, double mass, Color color) {
        if (mass <= 0) {
            throw new IllegalArgumentException("mass must be > 0");
        }
        this.id = id;
        this.position = position;
        this.velocity = velocity;
        this.mass = mass;
        this.color = color;
    }

    public Body(Point position, Vector2d velocity, double mass, Color color) {
        this(counter.getAndIncrement(), position, velocity, mass, color);
    }

    public Body(Point position, Vector2d velocity, double mass) {
        this(counter.getAndIncrement(), position, velocity, mass,
                Color.color(r.nextDouble(), r.nextDouble(), r.nextDouble()));
    }

    public Body move(List<Body> bodies) {
        Vector2d force = bodies.stream()
                .filter(b -> !b.equals(this))
                .map(this::calculateGForce)
                .reduce(Vector2d::add)
                .map(f -> f.divide(mass))   //inertia
                .orElseThrow(() -> new RuntimeException("unable to calculate the Force"));

        Vector2d finalVelocity = velocity.subtract(force);
        return new Body(id, position.move(finalVelocity), finalVelocity, mass, color);
    }

    private Vector2d calculateGForce(Body b) {
        double distance = position.distance(b.position);
        if (distance == Double.NaN) {
            throw new Error("division by zero");
        }
        if (distance <= 0.001) {
            return new Vector2d(0d, 0d);
        }
        double forceScalar = G * ((mass * b.mass) / distance);
        Vector2d direction = position.direction(b.position);
        Vector2d unitDirection = direction.unit();
        return unitDirection.multiply(forceScalar);
    }

    @Override
    public String toString() {
        return "Body{" +
                "id=" + id +
                ", position=" + position +
                ", velocity=" + velocity +
                ", mass=" + mass +
                ", color=" + color +
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

    public Color getColor() {
        return color;
    }
}
