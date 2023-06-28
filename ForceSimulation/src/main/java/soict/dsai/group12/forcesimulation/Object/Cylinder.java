package soict.dsai.group12.forcesimulation.Object;

public class Cylinder extends MainObject implements Rotable{
    private double gamma = 0;
    private double omega = 0;
    private double theta = 0;

    public double getGamma() {
        return gamma;
    }

    public void setGamma(double gamma) {
        this.gamma = gamma;
    }

    public double getOmega() {
        return omega;
    }

    public void setOmega(double omega) {
        this.omega = omega;
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }
    @Override
    public void resetObject(){
        this.setAcceleration(0);
        this.setVelocity(0);
        this.setPosition(0);
        this.setGamma(0);
        this.setOmega(0);
        this.setTheta(0);
    }

    public Cylinder(double radius, double mass){
        super(radius, mass);
    }

    @Override
    public double friction(double appliedForce, double staticCoeffient, double kineticCoefficient) {

        if (Math.abs(appliedForce) <= 3*this.normalForce()*staticCoeffient && this.getVelocity() == 0){
            return -appliedForce/3;
        } else if (Math.abs(appliedForce) > 3*this.normalForce()*staticCoeffient && this.getVelocity() == 0) {
            if (appliedForce > 0){
                return -this.normalForce()*kineticCoefficient;
            } else {
                return this.normalForce()*kineticCoefficient;
            }
        } else if (this.getVelocity() <0){
            return this.normalForce()*kineticCoefficient;
        } else {
            return -this.normalForce()*kineticCoefficient;
        }
//        if (Math.abs(appliedForce) <= 3*this.normalForce()*staticCoeffient){
//            return -appliedForce/3;
//        } else {
//            if (appliedForce > 0) {
//                return -this.normalForce() * kineticCoefficient;
//            } else {
//                return this.normalForce() * kineticCoefficient;
//            }
//        }

    }


    @Override
    public double calculateGamma(double friction, double mass, double radius) {
        return -2*friction/(mass*radius);
    }
}
