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

    /**
     * cập nhật giá trị của AppliedForce
     * và kiểm tra giá trị mới
     * để nó không vượt quá ngưỡng
     * @param value
     */
    public void setValue(double value) {
        super.setValue(value);
        validateValue();
    }

    /**
     * hàm check ngưỡng của lực
     * nếu không vi phạm
     * thì sẽ không có điều gì xảy ra sau đó
     */
    private void validateValue() {
        if (getValue() > MAX_FORCE) {
            super.setValue(MAX_FORCE);
        } else if (getValue() < -MAX_FORCE) {
            super.setValue(-MAX_FORCE);
        }
    }
}
