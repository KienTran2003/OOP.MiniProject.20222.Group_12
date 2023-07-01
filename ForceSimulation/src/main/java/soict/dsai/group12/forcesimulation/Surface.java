package soict.dsai.group12.forcesimulation;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Surface {
    private static final double MAX_STA_COEF = 1.0;
    private DoubleProperty staticFrictionCoefficient = new SimpleDoubleProperty(0.8);
    private DoubleProperty kineticFrictionCoefficient = new SimpleDoubleProperty(0.64);

    public double getStaticFrictionCoefficient() {
        return staticFrictionCoefficient.get();
    }

    public Surface() {
    }

    public Surface(double staCoef) {
        setStaticFrictionCoefficient(staCoef);
        setKineticFrictionCoefficient(staCoef / 1.25);
    }

    public Surface(double staCoef, double kiCoef) {
        setStaticFrictionCoefficient(staCoef);
        setKineticFrictionCoefficient(kiCoef);
    }

    public void setStaticFrictionCoefficient(double staticFrictionCoefficient) {
        // Kiểm tra giá trị mới để đảm bảo nó không vượt quá MAX_STA_COEF
        if (staticFrictionCoefficient <= MAX_STA_COEF) {
            this.staticFrictionCoefficient.set(staticFrictionCoefficient);
        } else {
            // Nếu vượt quá giá trị tối đa, đặt giá trị là MAX_STA_COEF
            this.staticFrictionCoefficient.set(MAX_STA_COEF);
        }
    }

    public double getKineticFrictionCoefficient() {
        return kineticFrictionCoefficient.get();
    }

    public void setKineticFrictionCoefficient(double kineticFrictionCoefficient) {
        if (kineticFrictionCoefficient < 0) {
            this.kineticFrictionCoefficient.set(0);
        } else if (kineticFrictionCoefficient >= getStaticFrictionCoefficient()) {
            this.kineticFrictionCoefficient.set(getStaticFrictionCoefficient() - 0.001);
        } else {
            this.kineticFrictionCoefficient.set(kineticFrictionCoefficient);
        }
    }

    public DoubleProperty staticFrictionCoefficientProperty() {
        return staticFrictionCoefficient;
    }

    public DoubleProperty kineticFrictionCoefficientProperty() {
        return kineticFrictionCoefficient;
    }
}
