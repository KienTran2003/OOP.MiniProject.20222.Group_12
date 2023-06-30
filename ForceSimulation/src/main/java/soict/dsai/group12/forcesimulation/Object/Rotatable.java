package soict.dsai.group12.forcesimulation.Object;

import javafx.beans.property.DoubleProperty;
import soict.dsai.group12.forcesimulation.Force.Force;

public interface Rotatable {
    DoubleProperty angAccProperty();
    double getAngAcc();
    void setAngAcc(double angAcc);
    void updateAngAcc(Force force);
    DoubleProperty angVelProperty();
    double getAngVel();
    void setAngVel(double angVel);
    void updateAngVel(double t);
    DoubleProperty angleProperty();
    double getAngle();
    void setAngle(double angle);
    void updateAngle(double oldAngVel, double t);
    DoubleProperty radiusProperty();
    double getRadius();
    void setRadius(double radius) throws Exception;
    void applyForceRotate(Force force, double t);
}
