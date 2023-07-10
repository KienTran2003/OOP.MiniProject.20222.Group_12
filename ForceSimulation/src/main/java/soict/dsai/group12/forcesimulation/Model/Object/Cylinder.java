package soict.dsai.group12.forcesimulation.Model.Object;

import soict.dsai.group12.forcesimulation.Model.Surface.Surface;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Cylinder extends MainObject implements RotatingObject{
    private DoubleProperty radius = new SimpleDoubleProperty();
    private DoubleProperty gamma = new SimpleDoubleProperty(); //angular acceleration
    private DoubleProperty theta = new SimpleDoubleProperty(); //angular position
    private DoubleProperty omega = new SimpleDoubleProperty(); //angular velocity

    public Cylinder(double radius, double mass) {
        super(mass);
        setRadius(radius);
    }
    public double getRadius() {
        return radius.get();
    }

    public DoubleProperty radiusProperty() {
        return radius;
    }
    public void setRadius(double radius){
        this.radius.set(radius);
    }

    public double getGamma() {
        return gamma.get();
    }

    public DoubleProperty gammaProperty() {
        return gamma;
    }

    public void setGamma(double gamma) {
        this.gamma.set(gamma);
    }

    public double getTheta() {
        return theta.get();
    }

    public DoubleProperty thetaProperty() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta.set(theta);
    }

    public double getOmega() {
        return omega.get();
    }

    public DoubleProperty omegaProperty() {
        return omega;
    }

    public void setOmega(double omega) {
        this.omega.set(omega);
    }
    public void resetObject(){
        setAcceleration(0);
        setVelocity(0);
        setPosition(0);
        setGamma(0);
        setTheta(0);
        setOmega(0);
    }

    @Override
    public double calculateAcceleration(double appliedForce, Surface surface) {
        double angularAcceleration = calculateAngularAcceleration(appliedForce, surface);
        gamma.set(angularAcceleration);
        double acceleration;
        if (angularAcceleration == 0) {
            acceleration = appliedForce / getMass();
        } else {
            acceleration = angularAcceleration;
        }
        return acceleration;
    }

    public double calculateAngularAcceleration(double appliedForce, Surface surface) {
        double frictionForce = calculateFrictionForces(appliedForce, surface);
        return 2 * -frictionForce / (getMass() * Math.pow(getRadius(), 2));
    }
    @Override
    public double calculateFrictionForces(double appliedForce, Surface surface) {
        double gravitationalForce = calculateGravitationalForce();
        double normalForce = calculateNormalForce(gravitationalForce);

        if (Math.abs(appliedForce) <= 3 * normalForce * surface.getStaticCoefficient() && velocity.get() < 0.001) {
            return -(appliedForce / 3);
        } else {
            if (appliedForce > 0) {
                return -normalForce * surface.getKineticCoefficient();
            } else {
                return normalForce * surface.getKineticCoefficient();
            }
        }
    }

    public void updateAttribute(double appliedForce, Surface surface) {
        double deltaTime = 0.01;
        super.updateAttribute(appliedForce, surface);

        // Cập nhật vận tốc góc và vị trí góc của Cylinder
        double currentTheta = theta.get();
        double currentOmega = omega.get();

        double newOmega = currentOmega + acceleration.get() * deltaTime;
        if (currentOmega * newOmega < 0) {
            omega.set(0);
        } else {
            omega.set(newOmega);
        }

        double newTheta = currentTheta + omega.get() * deltaTime + 0.5 * getAcceleration() * deltaTime * deltaTime;
        if (currentTheta * newTheta < 0) {
            theta.set(0);
        } else {
            theta.set(newTheta);
        }
    }

}