package soict.dsai.group12.forcesimulation.Object;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Cube extends MainObject {
    private DoubleProperty size;

    public static final double MAX_SIZE = 1.0;
    public static final double MIN_SIZE = 0.1;

    public Cube() {
        super();
        size = new SimpleDoubleProperty(MAX_SIZE * 0.3);
    }

    public Cube(double mass) {
        super();
        size = new SimpleDoubleProperty(MAX_SIZE * 0.3);
    }

    public Cube(double mass, double size) throws Exception {
        super();
        setSize(size);
    }

    public DoubleProperty sizeProperty() {
        return size;
    }

    public double getSize() {
        return size.get();
    }

    public void setSize(double size) throws Exception {
        if (size < MIN_SIZE || size > MAX_SIZE) {
            throw new Exception("You should enter Cube size between " + MIN_SIZE + " and " + MAX_SIZE);
        } else {
            this.size.set(size);
        }
    }
}

