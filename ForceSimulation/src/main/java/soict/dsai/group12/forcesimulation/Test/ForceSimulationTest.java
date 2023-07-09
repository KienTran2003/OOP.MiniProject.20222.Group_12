package soict.dsai.group12.forcesimulation.Test;

import soict.dsai.group12.forcesimulation.Model.Object.CubeBox;
import soict.dsai.group12.forcesimulation.Model.Object.Cylinder;
import soict.dsai.group12.forcesimulation.Model.Surface.Surface;

public class ForceSimulationTest {
    public static void main(String[] args){
        CubeBox cubeBox = new CubeBox(20, 50);
        Cylinder cylinder = new Cylinder(50,50);
        Surface surface = new Surface(0.5,0.4);
        System.out.println("SideLength of CubicBox is: " + cubeBox.getSide());
        System.out.println("Mass of Cubicbox is: " + cubeBox.getMass());
        System.out.println("Position: " + cubeBox.getPosition() + " Velocity: " + cubeBox.getVelocity() + " Accelartion: " + cubeBox.getAcceleration());
        cubeBox.setMass(10);
        cubeBox.setSide(40);
        System.out.println("SideLength of CubicBox is: " + cubeBox.getSide());
        System.out.println("Mass of cubix box is: " + cubeBox.getMass());
        System.out.println(cubeBox.calculateFriction(100,surface.getStaticCoefficient(), surface.getKineticCoefficient()));
    }
}
