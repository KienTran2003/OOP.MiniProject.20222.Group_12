package soict.dsai.group12.forcesimulation;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Actor {

    public Actor(double appliedForce) {
        this.setAppliedForce(appliedForce);
    }

    public double getMagnitude() {
        return Math.abs(appliedForce.get());
    }

    public static final double MAX_FORCE = 500;
    protected DoubleProperty appliedForce = new SimpleDoubleProperty(0.0);

    public double getAppliedForce() {
        return appliedForce.get();
    }

    public void setAppliedForce(double appliedForce) {
        this.appliedForce.set(appliedForce);
    }

    private void validateValue() {
        if (getAppliedForce() > MAX_FORCE) {
            setAppliedForce(MAX_FORCE);
        } else if (getAppliedForce() < -MAX_FORCE) {
            setAppliedForce(-MAX_FORCE);
        }
    }

    public static Actor sumTwoForce(Actor f1, Actor f2) {
        double sumValue = f1.getAppliedForce() + f2.getAppliedForce();
        return new Actor(sumValue);
    }
}
