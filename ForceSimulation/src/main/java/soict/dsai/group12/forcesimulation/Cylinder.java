package soict.dsai.group12.forcesimulation;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Cylinder extends MainObject {
    private DoubleProperty radius = new SimpleDoubleProperty();
    private Actor actor;
    private Surface surface;
    private double frictionForce;
    private double acceleration;

    public Cylinder() throws Exception {
        super();
    }

    @Override
    public void calculateForces(double appliedForce, double staticFrictionCoefficient, double kineticFrictionCoefficient) {
        double gravitationalForce = getMass() * 10;
        double normalForce = gravitationalForce;
        frictionForce = 0;

        if (appliedForce <= (3*normalForce * surface.getStaticFrictionCoefficient())) {
            frictionForce = -appliedForce/3;
        } else if (appliedForce > (3*normalForce * surface.getStaticFrictionCoefficient())) {
            if (appliedForce > 0) {
                frictionForce = -surface.getKineticFrictionCoefficient() * normalForce;
            } else {
                frictionForce = surface.getKineticFrictionCoefficient() * normalForce;
            }
        }
        this.frictionForce = frictionForce;
    }

    public Cylinder(double mass) throws Exception {
        super();
    }

    public Cylinder(double mass, double radius) throws Exception {
        super(mass);
        setRadius(radius);
    }

    public double getRadius() {
        return radius.get();
    }

    public void setRadius(double radius) {
        this.radius.set(radius);
    }

    public DoubleProperty radiusProperty() {
        return radius;
    }

    protected double calculateAcceleration() {
        double acceleration = frictionForce / (0.5 * getMass() * getRadius() * getRadius());
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
