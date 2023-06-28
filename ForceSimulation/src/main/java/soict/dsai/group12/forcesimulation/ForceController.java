package soict.dsai.group12.forcesimulation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class ForceController implements Initializable {

    double origin;
    @FXML
    ImageView posiAppForce, negaAppForce, posiFricForce, negaFricForce, posiSumForce, negaSumForce;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        origin = negaAppForce.getLayoutX();
    }

    public void updateAppForceVector(double applyForce, boolean isShow){
        if (applyForce > 0){
            posiAppForce.setFitWidth(applyForce/2);
            posiAppForce.setVisible(isShow);
            negaAppForce.setVisible(false);

        } else if (applyForce < 0){
            negaAppForce.setLayoutX(origin + applyForce/2);
            negaAppForce.setFitWidth(-applyForce/2);
            posiAppForce.setVisible(false);
            negaAppForce.setVisible(true);

        } else {
            posiAppForce.setVisible(false);
            negaAppForce.setVisible(false);
        }

    }
    public void updateFrictionVector(double fricForce, boolean isShow){
        if (fricForce > 0){
            posiFricForce.setFitWidth(fricForce/2);
            posiFricForce.setVisible(isShow);
            negaFricForce.setVisible(false);
        } else if (fricForce < 0) {
            negaFricForce.setLayoutX(origin + (fricForce)/2);
            negaFricForce.setFitWidth(-fricForce/2);
            posiFricForce.setVisible(false);
            negaFricForce.setVisible(isShow);
        } else {
            negaFricForce.setVisible(false);
            posiFricForce.setVisible(false);
        }
    }
    public void updateSumForce(double applyForce, double fricForce, boolean isShow){

        if (applyForce + fricForce > 0){
            posiSumForce.setFitWidth((applyForce + fricForce)/2);
            posiSumForce.setVisible(isShow);
            negaSumForce.setVisible(false);
        } else if (applyForce+fricForce < 0) {
            negaSumForce.setLayoutX(origin + (applyForce + fricForce)/2);
            negaSumForce.setFitWidth((-applyForce-fricForce)/2);
            negaSumForce.setVisible(isShow);
            posiSumForce.setVisible(false);
        } else {
            posiSumForce.setVisible(false);
            negaSumForce.setVisible(false);
        }
    }
}
