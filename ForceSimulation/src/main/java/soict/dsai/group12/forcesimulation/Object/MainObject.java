package soict.dsai.group12.forcesimulation.Object;

public abstract class MainObject {
    private double side;
    private double mass;
    private double velocity = 0;
    private double acceleration = 0;


    public MainObject(double side, double mass) {
        this.side = side;
        this.mass = mass;


    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double normalForce() {
        return 10 * mass;
    }

    public abstract double friction(double appliedForce, double staticCoeffient, double kineticCoefficient);

}
