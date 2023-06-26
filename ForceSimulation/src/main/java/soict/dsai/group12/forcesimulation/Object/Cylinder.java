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

    public Cylinder(double radius, double mass){
        super(radius, mass);
    }

    @Override
    public double friction(double appliedForce, double staticCoeffient, double kineticCoefficient) {
        return 0;
    }

    @Override
    public void calculateGamma(double friction, double mass, double radius) {

    }
}
