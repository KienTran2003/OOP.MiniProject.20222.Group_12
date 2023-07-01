package soict.dsai.group12.forcesimulation;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Cube extends MainObject {
    private DoubleProperty sideLength = new SimpleDoubleProperty();
    private double frictionForce;
    private Surface surface;
    private double acceleration;
    private Actor actor;

    public Cube(double mass) {
        super(mass);
    }

    public Cube(double mass, double sideLength) {
        super(mass);
        setSideLength(sideLength);
    }


    public double getSideLength() {
        return sideLength.get();
    }

    public void setSideLength(double sideLength) {
        this.sideLength.set(sideLength);
    }

    public DoubleProperty sideLengthProperty() {
        return sideLength;
    }

    public void calculateForces(double appliedForce, double staticFrictionCoefficient, double kineticFrictionCoefficient) {
        double gravitationalForce = getMass() * 10;
        double normalForce = gravitationalForce;
        frictionForce = 0;

        if (appliedForce <= normalForce * surface.getStaticFrictionCoefficient()) {
            frictionForce = -appliedForce;
        } else if (appliedForce > normalForce * surface.getStaticFrictionCoefficient()) {
            if (appliedForce > 0) {
                frictionForce = -surface.getKineticFrictionCoefficient() * normalForce;
            } else {
                frictionForce = surface.getKineticFrictionCoefficient() * normalForce;
            }
        }
        this.frictionForce = frictionForce;
    }

    protected double calculateAcceleration() {
        double netForce = Math.abs(actor.getAppliedForce() - frictionForce);
        double acceleration = netForce / getMass();
        this.acceleration = acceleration;
        return acceleration;
    }

    public void updateMotion(double deltaTime) {
        // Cập nhật gia tốc dựa trên lực ma sát và lực áp dụng
        calculateForces(actor.getAppliedForce(), surface.getStaticFrictionCoefficient(), surface.getKineticFrictionCoefficient());
        setAcceleration(calculateAcceleration());

        // Cập nhật vận tốc và vị trí dựa trên gia tốc và thời gian delta
        double newVelocity = getVelocity() + getAcceleration() * deltaTime;
        setVelocity(newVelocity);
        double newPosition = getPosition() + newVelocity * deltaTime;
        setPosition(newPosition);

        // Cập nhật giá trị tuyệt đối của vận tốc
        setSpeed(Math.abs(getVelocity()));
    }



    public double getAcceleration() {
        return acceleration;
    }

    public double getSpeed() {
        return speed.get();
    }

    public double getFrictionForce() {
        return frictionForce;
    }

    public void setSurface(Surface surface) {
        this.surface = surface;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }
}
