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
        setOmega(0);
        setTheta(0);
    }

    @Override
    public double calculateAcceleration(double appliedForce, Surface surface) {
        double angularAcceleration = calculateAngularAcceleration(appliedForce, surface);
        double acceleration;
        if (angularAcceleration == 0) {
            acceleration = appliedForce / getMass();
        } else {
            acceleration = angularAcceleration * getRadius();
        }
        return acceleration;
    }

    public double calculateAngularAcceleration(double appliedForce, Surface surface) {
        double frictionForce = calculateFrictionForces(appliedForce, surface);
        double angularAcceleration = frictionForce / (0.5 * getMass() * Math.pow(getRadius(), 2));
        return angularAcceleration;
    }

    @Override
    public double calculateFrictionForces(double appliedForce, Surface surface) {
        double gravitationalForce = calculateGravitationalForce();
        double normalForce = calculateNormalForce(gravitationalForce);

        if (Math.abs(appliedForce) <= 3 * normalForce * surface.getStaticCoefficient() && this.getOmega() == 0) {
            return -appliedForce / 3;
        } else if (Math.abs(appliedForce) > 3 * normalForce * surface.getStaticCoefficient() && this.getOmega() == 0) {
            if (appliedForce > 0) {
                return -normalForce * surface.getKineticCoefficient();
            } else {
                return normalForce * surface.getKineticCoefficient();
            }
        } else if (this.getOmega() < 0) {
            return normalForce * surface.getKineticCoefficient();
        } else {
            return -normalForce * surface.getKineticCoefficient();
        }
    }

    public void updateAttribute(double appliedForce, Surface surface) {
        double deltaTime = 0.01;

        // Cập nhật gia tốc dựa trên lực tác dụng
        double newGamma = calculateAngularAcceleration(appliedForce, surface);
        setGamma(newGamma);
        double acc = calculateAcceleration(appliedForce, surface);
        acceleration.set(acc);

        if (this.getOmega()*(this.getOmega() + deltaTime * newGamma) < 0){
            this.setOmega(0);
        } else {
            this.setOmega(this.getOmega() + deltaTime * newGamma);
        }
        if (this.getTheta()*(this.getTheta() + deltaTime * this.getOmega()) < 0){
            this.setTheta(0);
        } else {
            this.setTheta(this.getTheta() + deltaTime * this.getOmega());
        }

        double currentVelocity = getVelocity();
        double newVelocity = currentVelocity + getAcceleration() * deltaTime;
        if (currentVelocity * newVelocity < 0) {
            velocity.set(0);
        } else {
            velocity.set(newVelocity);
        }

        double linearVelocity = getRadius() * getOmega();
        double currentPosition = getPosition();
        double newLinearPosition = currentPosition + linearVelocity * deltaTime;
        setPosition(newLinearPosition);
    }
}