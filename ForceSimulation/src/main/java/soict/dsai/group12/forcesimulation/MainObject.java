package soict.dsai.group12.forcesimulation;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class MainObject {
    protected DoubleProperty mass = new SimpleDoubleProperty(50);
    protected DoubleProperty position = new SimpleDoubleProperty(0);
    protected DoubleProperty velocity = new SimpleDoubleProperty(0);
    protected DoubleProperty acceleration = new SimpleDoubleProperty(0);
    protected DoubleProperty speed = new SimpleDoubleProperty(0);
    protected Actor Actor;
    private Surface surface;

    public MainObject(double mass, double position, double velocity) {
        setMass(mass);
        setPosition(position);
        setVelocity(velocity);
    }

    public MainObject(double mass) {
        setMass(mass);
    }

    public MainObject() {
    }


    public abstract void calculateForces(double appliedForce, double staticFrictionCoefficient,
                                         double kineticFrictionCoefficient);

    public void updateMotion(double deltaTime) {
        acceleration.set(calculateAcceleration());
        velocity.set(velocity.get() + acceleration.get() * deltaTime);
        position.set(position.get() + velocity.get() * deltaTime);
        speed.set(Math.abs(velocity.get()));
    }

    protected abstract double calculateAcceleration();

    public double getMass() {
        return mass.get();
    }

    public void setMass(double mass) {
        this.mass.set(mass);
    }

    public DoubleProperty massProperty() {
        return mass;
    }

    public double getPosition() {
        return position.get();
    }

    public void setPosition(double position) {
        this.position.set(position);
    }

    public DoubleProperty positionProperty() {
        return position;
    }

    public void setSpeed(double speed) {
        this.speed.set(speed);
    }

    public double getSpeed() {
        return speed.get();
    }

    public DoubleProperty speedProperty() {
        return speed;
    }

    protected double frictionForce;

    public double getFrictionForce() {
        return frictionForce;
    }
    public void setSurface(Surface surface) {
        this.surface = surface;
    }
    public void setActor(Actor actor) {
        this.Actor = actor;
    }
    public double getVelocity() {
        return velocity.get();
    }

    public void setVelocity(double velocity) {
        this.velocity.set(velocity);
    }

    public DoubleProperty velocityProperty() {
        return velocity;
    }

    public double getAcceleration() {
        return acceleration.get();
    }

    public void setAcceleration(double acceleration) {
        this.acceleration.set(acceleration);
    }

    public DoubleProperty accelerationProperty() {
        return acceleration;
    }

}
