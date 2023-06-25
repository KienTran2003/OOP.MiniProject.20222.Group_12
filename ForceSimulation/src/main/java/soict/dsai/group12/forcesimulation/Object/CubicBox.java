package soict.dsai.group12.forcesimulation.Object;

public class CubicBox {
    private double sideLength;
    private double mass;
    private double velocity;

    private double acceleration;


    public CubicBox(double sideLength, double mass, double velocity, double acceleration){
        this.sideLength = sideLength;
        this.mass = mass;
        this.velocity = velocity;
        this.acceleration = acceleration;

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



    public double getSideLength() {
        return sideLength;
    }

    public double getMass() {
        return mass;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double normalForce(){
        return 10*mass;
    }
    public double friction(double appliedForce, double staticCoeffient, double kineticCoefficient, double velocity){
        if (Math.abs(appliedForce) <= this.normalForce()*staticCoeffient && velocity == 0){
            return -appliedForce;
        } else if (Math.abs(appliedForce) > this.normalForce()*staticCoeffient && velocity == 0) {
            if (appliedForce > 0){
                return -this.normalForce()*kineticCoefficient;
            } else {
                return this.normalForce()*kineticCoefficient;
            }
        } else if (velocity <0){
            return this.normalForce()*kineticCoefficient;
        } else {
            return -this.normalForce()*kineticCoefficient;
        }
    }
}
