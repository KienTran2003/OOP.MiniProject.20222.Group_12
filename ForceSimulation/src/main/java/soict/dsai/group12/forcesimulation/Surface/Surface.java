package soict.dsai.group12.forcesimulation.Surface;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Surface {
    //giả sử ma sát dùng là bê tông : 0.6 - 1.0
    public static final double MAX_STATIC_COEFFICIENT = 1.0;
    private DoubleProperty staCoef = new SimpleDoubleProperty(0.8);
    private DoubleProperty kiCoef = new SimpleDoubleProperty(0.64);
    public static final double STEP_COEF = 0.001;

    public Surface() {
    }

    public Surface(double staCoef) {
        setStaCoef(staCoef);
        setKiCoef(staCoef / 1.25);
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
        } else if (kiCoef >= getStaCoef()) {
            this.kiCoef.set(getStaCoef() - STEP_COEF);
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
