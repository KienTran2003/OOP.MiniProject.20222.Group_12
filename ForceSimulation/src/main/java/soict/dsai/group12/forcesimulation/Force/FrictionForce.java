package soict.dsai.group12.forcesimulation.Force;

import soict.dsai.group12.forcesimulation.Object.Cube;
import soict.dsai.group12.forcesimulation.Object.Cylinder;
import soict.dsai.group12.forcesimulation.Object.MainObject;
import soict.dsai.group12.forcesimulation.Surface.Surface;

public class FrictionForce extends Force{
    private Surface surface;
    private MainObject mainObj;
    private AppliedForce aForce;
    public static final double g = 10;
    public static final double VEL_THRESHOLD = 0.001;
    /**
     * Constructor
     *
     * @param value
     */
    public FrictionForce(double value) {
        super(value);
    }
    public FrictionForce(double value, Surface surface, MainObject mainObj, AppliedForce aForce) {
        super(value);
        this.surface = surface;
        this.mainObj = mainObj;
        this.aForce = aForce;
        updateFrictionForce();
    }
    public void updateFrictionForce() {
        if (mainObj != null) {
            double direction = 0;
            double normalForce = mainObj.getMass() * g;
            double aForceValue = Math.abs(aForce.getValue());

            if (mainObj.getVel().getValue() != 0) {
                direction = (mainObj.getVel().isDirectionRight()) ? -1 : 1;
            } else {
                if (aForceValue == 0) {
                    setValue(0);
                    return;
                } else {
                    direction = (aForce.isDirectionRight()) ? -1 : 1;
                }
            }

            if (mainObj instanceof Cube) {
                if (aForceValue <= surface.getStaCoef() * normalForce
                        && mainObj.getVel().getValue() < VEL_THRESHOLD) {
                    setValue(-aForce.getValue());
                } else {
                    setValue(direction * surface.getKiCoef() * normalForce);
                }
            } else if (mainObj instanceof Cylinder) {
                if (aForceValue <= 3 * surface.getStaCoef() * normalForce && aForceValue > 0) {
                    setValue(direction * aForceValue / 3);
                } else {
                    setValue(direction * surface.getKiCoef() * normalForce);
                }
            }
        }
    }
    public void setMainObj(MainObject obj) {
        this.mainObj = obj;
        updateFrictionForce();
    }
}
