package soict.dsai.group12.forcesimulation.Force;

import soict.dsai.group12.forcesimulation.Object.Cube;
import soict.dsai.group12.forcesimulation.Object.Cylinder;
import soict.dsai.group12.forcesimulation.Object.MainObject;
import soict.dsai.group12.forcesimulation.Surface.Surface;

public class FrictionForce extends Force{
        private Surface surface;
        private MainObject mainObject;
        private AppliedForce appliedForce;
        public static final double GRAVITY = 10;
        public static final double VELOCITY_THRESHOLD = 0.001;

        public FrictionForce(double value) {
            super(value);
        }

        public FrictionForce(double value, Surface surface, MainObject mainObject, AppliedForce appliedForce) {
            super(value);
            this.surface = surface;
            this.mainObject = mainObject;
            this.appliedForce = appliedForce;
            updateFrictionForce();
        }

    /**
     * Kiểm tra mainObject khác null không, nếu không, ma sát = 0, kết thúc
     * Có: đặt direction = 0, tính normalForce, tính giá trị tuyệt đối
     * của appliedForce
     */
    public void updateFrictionForce() {
        if (mainObject != null) {
            double direction = 0;
            double normalForce = mainObject.getMass() * GRAVITY;
            double aForceValue = Math.abs(appliedForce.getValue());

            /**
             * 1. appliedForce
             * xác định hướng của lực ma sát dựa trên vận tốc
             * vận tốc # 0 -> hướng sẽ là hướng vận tốc
             * vận tốc = 0 -> kiểm tra gía trị aForceValue
             */
            if (mainObject.getVel().getValue() != 0) {
                direction = (mainObject.getVel().isDirectionRight() == true) ? -1 : 1;
            } else {
                /**
                 * nếu aForceValue = 0
                 * lực ma sát = 0 -> kết thúc
                 * aForceValue # 0
                 * hướng được xác định dựa trên hướng của appliedForce
                 */
                if (aForceValue == 0) {
                    setValue(0);
                    return;
                } else {
                    direction = (appliedForce.isDirectionRight() == true) ? -1 : 1;
                }
            }
            /**
             * 2. lực ma sát dựa trên mainObject và surface
             * Cube: so sánh giá trị với ngưỡng staCoef*normalForce và giá trị vận tốc
             * nếu đúng -> ma sát = -appliedForce.getValue()
             * để ngăn chuyển động dừng lại
             */
            if (mainObject instanceof Cube) {
                if (aForceValue <= surface.getStaCoef() * normalForce
                        && mainObject.getVel().getMagnitude() < VELOCITY_THRESHOLD) {
                    setValue(-appliedForce.getValue());
                } else {
                    setValue(direction * surface.getKiCoef() * normalForce);
                }
            } else if (mainObject instanceof Cylinder) {
                if (aForceValue <= 3 * surface.getStaCoef() * normalForce && aForceValue > 0) {
                    setValue(direction * aForceValue / 3);
                } else {
                    setValue(direction * surface.getKiCoef() * normalForce);
                }
            }
        }
    }

    public void setMainObj(MainObject obj) {
        this.mainObject = obj;
        updateFrictionForce();
    }
}
