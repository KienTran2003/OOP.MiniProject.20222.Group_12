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

    /**
     * cập nhật giá trị của vecto chuyển động
     * cập nhật hướng dựa trên giá trị mới
     * @param value
     */
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

    /**
     * cập nhật hướng và giá trị
     * gắn gía trị isRight cho biến direction
     * updateDirectionValue() để cập nhật giá trị trên hướng mới
     * @param isRight
     */
    public void setDirectionRight(boolean isRight) {
        direction.set(isRight);
        updateDirectionValue();
    }
    /**
     * được sử dụng để cập nhật giá trị của thuộc tính value dựa trên giá trị thuộc tính direction
     * Nếu hướng hiện tại là true (đại diện cho hướng dương),
     * thì giá trị của absValue được gán trực tiếp cho value.
     * Ngược lại, nếu hướng hiện tại là false (đại diện cho hướng âm),
     * thì giá trị của absValue được đảo dấu (-absValue)
     * trước khi gán cho value.
     */

    private void updateDirectionValue() {
        double absValue = Math.abs(getValue());
        value.set(isDirectionRight() ? absValue : -absValue);
    }
    public DoubleProperty valueProperty() {
        return value;
    }

    public double getMagnitude() {
        return Math.abs(value.get());
    }

}
