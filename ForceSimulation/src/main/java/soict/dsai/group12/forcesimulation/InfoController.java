package soict.dsai.group12.forcesimulation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import soict.dsai.group12.forcesimulation.Object.Cylinder;
import soict.dsai.group12.forcesimulation.Object.MainObject;

public class InfoController {
    private MainObject mainObject;
    @FXML
    Label accelerate, velo, posi, anAccelerate, anVelo, anPosi, accLabel, veloLabel, posiLabel, anAccLabel, anVeloLabel, anPosiLabel;

    InfoController(MainObject mainObject){
        this.mainObject = mainObject;
    }

    public void setMainObject(MainObject mainObject) {
        this.mainObject = mainObject;
    }

    public void showAccelerate(boolean bool) {
        if (bool){
            accelerate.setText(String.format("%.2f",mainObject.getAcceleration()) + " m/s^2");
            if (mainObject instanceof Cylinder){
                anAccelerate.setText(String.format("%.2f",((Cylinder) mainObject).getGamma()) + " */s^2");
            } else {
                anAccelerate.setText(0 + " */s^2");
            }
            accelerate.setVisible(true);
            anAccelerate.setVisible(true);
            accLabel.setVisible(true);
            anAccLabel.setVisible(true);
        } else {
            accelerate.setVisible(false);
            anAccelerate.setVisible(false);
            accLabel.setVisible(false);
            anAccLabel.setVisible(false);
        }
    }

    public void showVelo(boolean bool) {
        if (bool){
            velo.setText(String.format("%.2f",mainObject.getVelocity()) + " m/s");
            if (mainObject instanceof Cylinder){
                anVelo.setText(String.format("%.2f",((Cylinder) mainObject).getOmega()) + " */s");
            } else {
                anVelo.setText(0 + " */s");
            }
            velo.setVisible(true);
            anVelo.setVisible(true);
            veloLabel.setVisible(true);
            anVeloLabel.setVisible(true);
        } else {
            velo.setVisible(false);
            anVelo.setVisible(false);
            veloLabel.setVisible(false);
            anVeloLabel.setVisible(false);
        }
    }

    public void showPosi(boolean bool) {
        if (bool){
            posi.setText(String.format("%.2f",mainObject.getPosition()) + " m");
            if (mainObject instanceof Cylinder){
                anPosi.setText(String.format("%.2f", ((Cylinder) mainObject).getTheta()) + " *");
            } else {
                anPosi.setText(0 + " *");
            }
            posi.setVisible(true);
            anPosi.setVisible(true);
            posiLabel.setVisible(true);
            anPosiLabel.setVisible(true);
        } else {
            posi.setVisible(false);
            anPosi.setVisible(false);
            posiLabel.setVisible(false);
            anPosiLabel.setVisible(false);
        }
    }

}
