package soict.dsai.group12.forcesimulation.Model.Object;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import soict.dsai.group12.forcesimulation.Model.Surface.Surface;

public abstract class MainObject {
    protected DoubleProperty mass = new SimpleDoubleProperty(50);
    protected DoubleProperty position = new SimpleDoubleProperty(0);
    protected DoubleProperty velocity = new SimpleDoubleProperty(0);
    protected DoubleProperty acceleration = new SimpleDoubleProperty(0);


    public MainObject(double mass) {
        setMass(mass);
    }

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

    public DoubleProperty positionProperty() {
        return position;
    }

    public void setPosition(double position) {
        this.position.set(position);
    }

    public double getVelocity() {
        return velocity.get();
    }

    public DoubleProperty velocityProperty() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity.set(velocity);
    }

    public double getAcceleration() {
        return acceleration.get();
    }

    public DoubleProperty accelerationProperty() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration.set(acceleration);
    }

    public void resetObject(){
        setAcceleration(0);
        setVelocity(0);
        setPosition(0);
    }

    public abstract double calculateFrictionForces(double appliedForce, Surface surface);

    public void updateAttribute(double appliedForce, Surface surface) {
        double deltaTime = 0.01;
        double acc = calculateAcceleration(appliedForce, surface);
        setAcceleration(acc);
        setPosition(getPosition() + getVelocity() * deltaTime + 0.5 * acc * deltaTime * deltaTime);

        double currentVelocity = getVelocity();
        double newVelocity = currentVelocity + getAcceleration() * deltaTime;
        if (currentVelocity * newVelocity < 0) {
            setVelocity(0); // Stop when velocity changes direction
        } else {
            setVelocity(newVelocity);
        }
    }
    double calculateGravitationalForce() {
        return this.getMass() * 10;
    }

    double calculateNormalForce(double gravitationalForce) {
        return gravitationalForce;
    }
    public abstract double calculateAcceleration(double appliedForce, Surface surface);
}