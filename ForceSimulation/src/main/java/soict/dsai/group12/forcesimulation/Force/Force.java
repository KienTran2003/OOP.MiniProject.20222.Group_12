package soict.dsai.group12.forcesimulation.Force;

public class Force extends MotionVector{
    /**
     * Constructor
     *
     * @param value
     */
    public Force(double value) {
        super(value);
    }

    /**
     * sum of two force
     * @param f1 : AppliedForce
     * @param f2 : Friction Force
     * @return
     */
    public static Force sumTwoForce(Force f1, Force f2) {
        double sumValue = f1.getValue() + f2.getValue();
        return new Force(sumValue);
    }
}
