package soict.dsai.group12.forcesimulation.Model.Object;

import soict.dsai.group12.forcesimulation.Model.Surface.Surface;
import javafx.beans.property.DoubleProperty;

public interface RotatingObject {
    DoubleProperty gammaProperty();
    double getGamma();
    void setGamma(double gamma);
    double getTheta();
    DoubleProperty thetaProperty();
    void setTheta(double theta);
    double getOmega();
    DoubleProperty omegaProperty();
    void setOmega(double omega);
    DoubleProperty radiusProperty();
    double getRadius();
    void setRadius(double radius) throws Exception;
    double calculateAngularAcceleration(double appliedForce, Surface surface); //cal gamma
    void updateAttribute(double appliedForce, Surface surface);
}