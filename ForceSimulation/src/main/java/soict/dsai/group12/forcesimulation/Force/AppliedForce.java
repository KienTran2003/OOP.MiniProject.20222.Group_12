package soict.dsai.group12.forcesimulation.Force;

public class AppliedForce extends Force{
    public static final double MAX_FORCE = 500;
    /**
     * Constructor
     *
     * @param value
     */
    public AppliedForce(double value) {
        super(value);
    }
    public void setValue(double value) {
        super.setValue(value);
        validateValue();
    }
    private void validateValue() {
        if (getValue() > MAX_FORCE) {
            super.setValue(MAX_FORCE);
        } else if (getValue() < -MAX_FORCE) {
            super.setValue(-MAX_FORCE);
        }
    }
}
