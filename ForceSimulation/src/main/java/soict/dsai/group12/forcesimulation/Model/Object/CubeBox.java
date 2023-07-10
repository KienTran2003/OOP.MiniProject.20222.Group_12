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

        if (Math.abs(appliedForce) <= normalForce * surface.getStaticCoefficient() && velocity.get() == 0) {
            return -(appliedForce);
        } else {
            if (appliedForce > 0) {
                return -normalForce * surface.getKineticCoefficient();
            } else {
                return normalForce * surface.getKineticCoefficient();
            }
        }
    }


    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }
}