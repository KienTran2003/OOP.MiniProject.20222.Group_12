package soict.dsai.group12.forcesimulation.Object;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import soict.dsai.group12.forcesimulation.Force.Force;
import soict.dsai.group12.forcesimulation.Force.FrictionForce;

public class Cylinder extends MainObject implements Rotatable {
    private DoubleProperty angle = new SimpleDoubleProperty();;
    private DoubleProperty angAcc = new SimpleDoubleProperty();;
    private DoubleProperty angVel = new SimpleDoubleProperty();;
    private DoubleProperty radius = new SimpleDoubleProperty();;

    public static final double MAX_RADIUS = 1.0;
    public static final double MIN_RADIUS = 0.1;

    public Cylinder() throws Exception {
        super();
    }

    public Cylinder(double mass) throws Exception {
        super();
    }

    public Cylinder(double mass, double radius) throws Exception {
        super();
        angle = new SimpleDoubleProperty();
        angAcc = new SimpleDoubleProperty();
        angVel = new SimpleDoubleProperty();
        setRadius(radius);
    }

    public DoubleProperty angAccProperty() {
        return angAcc;
    }


    public double getAngAcc() {
        return angAcc.get();
    }

    public void setAngAcc(double angAcc) {
        this.angAcc.set(angAcc);
    }


    public void updateAngAcc(Force force) {
        if (force instanceof FrictionForce) {
            setAngAcc(-force.getValue() / (0.5 * getMass() * getRadius() * getRadius()));
        }
    }

    public DoubleProperty angVelProperty() {
        return angVel;
    }

    public double getAngVel() {
        return angVel.get();
    }

    public void setAngVel(double angVel) {
        this.angVel.set(angVel);
    }

    public void updateAngVel(double t) {
        setAngVel(getAngVel() + getAngAcc() * t);
    }

    public DoubleProperty angleProperty() {
        return angle;
    }

    public double getAngle() {
        return angle.get();
    }

    public void setAngle(double angle) {
        this.angle.set(angle);
    }

    public void updateAngle(double oldAngVel, double t) {
        setAngle(getAngle() + oldAngVel * t + 0.5 * getAngAcc() * t * t);
    }

    public DoubleProperty radiusProperty() {
        return radius;
    }

    public double getRadius() {
        return radius.get();
    }

    public void setRadius(double radius) throws Exception {
        if (radius < MIN_RADIUS || radius > MAX_RADIUS) {
            this.radius.set(MAX_RADIUS * 0.3);
            throw new Exception("You should enter radius of object between " + MIN_RADIUS + " and " + MAX_RADIUS);
        } else {
            this.radius.set(radius);
        }
    }

    public void applyForceRotate(Force force, double t) {
        double oldAngVel = getAngVel();
        updateAngAcc(force);
        updateAngVel(t);
        updateAngle(oldAngVel, t);
    }

    public void applyForce(Force netforce, Force fForce, double t) {
        super.applyForce(netforce, fForce, t);
        this.applyForceRotate(fForce, t);
    }
}
