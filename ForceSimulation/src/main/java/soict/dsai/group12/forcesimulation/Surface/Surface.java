package soict.dsai.group12.forcesimulation.Surface;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Surface {
    public static final double MAX_STATIC_COEFFICIENT = 1.0;
    private DoubleProperty staCoef = new SimpleDoubleProperty(MAX_STATIC_COEFFICIENT / 2);
    private DoubleProperty kiCoef = new SimpleDoubleProperty(MAX_STATIC_COEFFICIENT / 4);
    public static final double STEP_COEF = 0.001;

    public Surface() {
    }

    public Surface(double staCoef) {
        setStaCoef(staCoef);
        setKiCoef(staCoef / 2);
    }

    public Surface(double staCoef, double kiCoef) {
        setStaCoef(staCoef);
        setKiCoef(kiCoef);
    }

    public DoubleProperty staCoefProperty() {
        return staCoef;
    }

    public double getStaCoef() {
        return staCoef.get();
    }

    public void setStaCoef(double staCoef) {
        if (staCoef < 0) {
            this.staCoef.set(0);
        } else if (staCoef > MAX_STATIC_COEFFICIENT) {
            this.staCoef.set(MAX_STATIC_COEFFICIENT);
        } else {
            this.staCoef.set(staCoef);
        }
    }

    public DoubleProperty kiCoefProperty() {
        return kiCoef;
    }

    public double getKiCoef() {
        return kiCoef.get();
    }

    public void setKiCoef(double kiCoef) {
        if (kiCoef < 0) {
            this.kiCoef.set(0);
        } else if (kiCoef >= MAX_STATIC_COEFFICIENT) {
            this.kiCoef.set(Math.max(0, getStaCoef() - STEP_COEF));
        } else if (getStaCoef() <= kiCoef) {
            this.kiCoef.set(Math.max(0, getStaCoef() - STEP_COEF));
        } else {
            this.kiCoef.set(kiCoef);
        }
    }

    public double getStaCoefThreshold() {
        return getKiCoef() + STEP_COEF;
    }

    public double getKiCoefThreshold() {
        return Math.max(0, getStaCoef() - STEP_COEF);
    }
}
