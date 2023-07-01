package soict.dsai.group12.forcesimulation.Object;

import javafx.beans.property.*;
import soict.dsai.group12.forcesimulation.Force.Force;
import soict.dsai.group12.forcesimulation.Force.MotionVector;

public abstract class MainObject {
    public static final double DEFAULT_MASS = 50;
    private DoubleProperty mass = new SimpleDoubleProperty(DEFAULT_MASS);

    private MotionVector acc = new MotionVector(0.0);
    private MotionVector vel = new MotionVector(0.0);
    private DoubleProperty position = new SimpleDoubleProperty();

    public MainObject() {
    }

    public DoubleProperty massProperty() {
        return mass;
    }
    public double getMass() {
        return mass.get();
    }

    public void setMass(double mass) {
        this.mass.set(mass);
    }

    public MotionVector getAcc() {
        return acc;
    }

    public MotionVector getVel() {
        return vel;
    }

    public void setAcc(MotionVector acc) {
        this.acc = acc;
    }

    public void setVel(MotionVector vel) {
        this.vel = vel;
    }

    /**
     * cập nhật gia tốc
     * @param force
     */
    public void updateAcc(Force force) {
        double newAccValue = force.getValue() / getMass();
        acc.setValue(newAccValue);
    }

    /**
     * cập nhật vận tốc
     * @param t
     */
    public void updateVel(double t) {
        double newVelValue = vel.getValue() + acc.getValue() * t;
        vel.setValue(newVelValue);
        if (vel.getMagnitude() == 0) {
            vel.setDirectionRight(acc.isDirectionRight());
        }
    }
    //lực áp dụng trong thời gian t, cập nhật a,v,x
    public void applyForce(Force netForce, Force frictionForce, double t) {
        updateAcc(netForce);
        updateVel(t);
        double oldPos = position.get();
        double newPos = oldPos + vel.getValue() * t + 0.5 * acc.getValue() * t * t;
        position.set(newPos);
    }

    public DoubleProperty posProperty() {
        return position;
    }

    public double getPosition() {
        return position.get();
    }

    public void setPosition(double pos) {
        this.position.set(pos);
    }

}
