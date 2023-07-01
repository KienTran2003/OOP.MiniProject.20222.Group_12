package soict.dsai.group12.forcesimulation;

import java.util.ArrayList;
import java.util.List;

public class ForceSimulation {
    private List<MainObject> objects;
    private Actor actor;
    private double deltaTime;

    public ForceSimulation() {
        objects = new ArrayList<>();
        deltaTime = 0.1; // Giá trị thời gian delta cố định, có thể điều chỉnh theo yêu cầu
    }

    public void addObject(MainObject object) {
        objects.add(object);
    }

    public void updateSimulation() {
        for (MainObject object : objects) {
            object.updateMotion(deltaTime);
        }
    }

    public void printObjectStates() {
        for (MainObject object : objects) {
            System.out.println("Object: " + object.getClass().getSimpleName());
            System.out.println("Position: " + object.getPosition());
            System.out.println("Velocity: " + object.getVelocity());
            System.out.println("Acceleration: " + object.getAcceleration());
            System.out.println("Friction Force: " + object.getFrictionForce());
            System.out.println("------");
        }
    }
}
