package soict.dsai.group12.forcesimulation.Object;

public abstract class MainObject {
    private double side;
    private double mass;
    private double position = 0;
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
    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }
    public void resetObject(){
        this.acceleration = 0;
        this.velocity = 0;
        this.position = 0;
    }
    public double normalForce() {
        return 10 * mass;
    }

    public abstract double friction(double appliedForce, double staticCoeffient, double kineticCoefficient);

}
