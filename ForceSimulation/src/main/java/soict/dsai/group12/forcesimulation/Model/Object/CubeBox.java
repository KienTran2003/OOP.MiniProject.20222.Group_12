package soict.dsai.group12.forcesimulation.Model.Object;

import soict.dsai.group12.forcesimulation.Model.Surface.Surface;

public class CubeBox extends MainObject{
    private double side;

    public CubeBox(double side, double mass) {
        super(mass);
        this.side = side;
    }

    @Override
    public double calculateAcceleration(double appliedForce, Surface surface) {
        double frictionForce = calculateFrictionForces(appliedForce, surface);
        double netForce = appliedForce + frictionForce;
        double acceleration = netForce / getMass();
        return acceleration;
    }

    @Override
    public double calculateFrictionForces(double appliedForce, Surface surface) {
        double gravitationalForce = calculateGravitationalForce();
        double normalForce = calculateNormalForce(gravitationalForce);

        double frictionForce = 0;

        if (Math.abs(appliedForce) <= normalForce * surface.getStaticCoefficient() && this.getVelocity() == 0) {
            frictionForce = -appliedForce;
        } else if (Math.abs(appliedForce) > normalForce * surface.getStaticCoefficient() && this.getVelocity() == 0) {
            if (appliedForce > 0) {
                frictionForce = -normalForce * surface.getKineticCoefficient();
            } else {
                frictionForce = normalForce * surface.getKineticCoefficient();
            }
        } else if (this.getVelocity() < 0) {
            frictionForce = normalForce * surface.getKineticCoefficient();
        } else {
            frictionForce = -normalForce * surface.getKineticCoefficient();
        }
        return frictionForce;
    }

    @Override
    public void updateAttribute(double appliedForce, Surface surface) {
        double deltaTime = 0.01;
        double acc = calculateAcceleration(appliedForce, surface);
        setAcceleration(acc);
        setPosition(getPosition() + getVelocity() * deltaTime);

        double currentVelocity = getVelocity();
        double newVelocity = currentVelocity + getAcceleration() * deltaTime;
        if (currentVelocity * newVelocity < 0) {
            setVelocity(0); // Stop when velocity changes direction
        } else {
            setVelocity(newVelocity);
        }
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }
}