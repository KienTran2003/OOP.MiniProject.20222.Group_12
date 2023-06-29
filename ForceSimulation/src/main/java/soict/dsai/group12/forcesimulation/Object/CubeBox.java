package soict.dsai.group12.forcesimulation.Object;

public class CubeBox extends MainObject{



    public CubeBox(double sideLength, double mass){
        super(sideLength, mass);

    }


    public double friction(double appliedForce, double staticCoeffient, double kineticCoefficient){
        if (Math.abs(appliedForce) <= this.normalForce()*staticCoeffient && this.getVelocity() == 0){
            return -appliedForce;
        } else if (Math.abs(appliedForce) > this.normalForce()*staticCoeffient && this.getVelocity() == 0) {
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


    }
}
