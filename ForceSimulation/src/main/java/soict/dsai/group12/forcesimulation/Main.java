package soict.dsai.group12.forcesimulation;

public class Main {
    public static void main(String[] args) throws Exception {
        // Khởi tạo các đối tượng và thiết lập các giá trị

        Cube cube = new Cube(50, 2.5);
        Actor cubeActor = new Actor(500);
        cube.setActor(cubeActor);

        Cylinder cylinder = new Cylinder(50, 1.0);
        Actor cylinderActor = new Actor(490);
        cylinder.setActor(cylinderActor);

        Surface surface = new Surface(0.8, 0.64);
        cube.setSurface(surface);
        cylinder.setSurface(surface);

        double deltaTime = 0.1;

        double appliedForce1 = cubeActor.getAppliedForce();

        cube.calculateForces(appliedForce1, surface.getStaticFrictionCoefficient(), surface.getKineticFrictionCoefficient());
        cube.updateMotion(deltaTime);

        double appliedForce2 = cylinderActor.getAppliedForce();
        cylinder.calculateForces(appliedForce2, surface.getStaticFrictionCoefficient(), surface.getKineticFrictionCoefficient());
        cylinder.updateMotion(deltaTime);


        // In ra kết quả

        System.out.println("Cube Friction Force: " + cube.getFrictionForce());
        System.out.println("Cube Acceleration: " + cube.getAcceleration());
        System.out.println("Cube Velocity: " + cube.getVelocity());

        System.out.println("Cylinder Friction Force: " + cylinder.getFrictionForce());
        System.out.println("Cylinder Acceleration: " + cylinder.getAcceleration());
        System.out.println("Cylinder Velocity: " + cylinder.getVelocity());
    }
}

