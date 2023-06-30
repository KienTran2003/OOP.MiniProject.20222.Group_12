package soict.dsai.group12.forcesimulation.Force;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class MotionVector {
    /**
     * Hướng vector, true: right
     */
    protected BooleanProperty direction = new SimpleBooleanProperty(true);

    /**
     * Độ lớn vector, giá trị mặc định 0.0
     */
    protected DoubleProperty value = new SimpleDoubleProperty(0.0);

    /**
     * Constructor
     * @param value
     */
    public MotionVector(double value) {
        this.setValue(value);
    }
    public double getValue() {
        return value.get();
    }

    public void setValue(double value) {
        this.value.set(value);
        updateValueDirection();
    }
    private void updateValueDirection() {
        if (getValue() >= 0) {
            direction.set(true);
        } else {
            direction.set(false);
        }
    }
    public BooleanProperty directionProperty() {
        return direction;
    }

    public boolean isDirectionRight() {
        return direction.get();
    }

    public void setDirectionRight(boolean isRight) {
        direction.set(isRight);
        updateDirectionValue();
    }
    public DoubleProperty valueProperty() {
        return value;
    }

    public double getMagnitude() {
        return Math.abs(value.get());
    }


    /**
     * được sử dụng để cập nhật giá trị của thuộc tính value dựa trên giá trị thuộc tính direction
     * Nếu direction có giá trị true (đúng), nghĩa là vector đang hướng sang phải,
     * thì value sẽ được cập nhật với giá trị absValue (giá trị tuyệt đối của value).
     */

    private void updateDirectionValue() {
        double absValue = Math.abs(getValue());
        value.set(isDirectionRight() ? absValue : -absValue);
    }
}
