package soict.dsai.group12.forcesimulation.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class ForceController implements Initializable {

    double origin;
    @FXML
    ImageView posiAppForce, negaAppForce, posiFricForce, negaFricForce, posiSumForce, negaSumForce;
    @FXML
    private Label appForceLabel, fricLabel, sumLabel;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        origin = negaAppForce.getLayoutX();
    }

    public void updateAppForceVector(double applyForce, boolean isShow, boolean isShowValue){
        appForceLabel.setText(String.format("%.0f", applyForce) + "N");
        appForceLabel.setVisible(isShow && isShowValue);
        if (applyForce > 0){
            posiAppForce.setFitWidth(applyForce/2);
//            appForceLabel.setText(String.format("%.0f", applyForce));
            appForceLabel.setLayoutX(posiAppForce.getLayoutX() + posiAppForce.getFitWidth()/2);

            posiAppForce.setVisible(isShow);
            negaAppForce.setVisible(false);

        } else if (applyForce < 0){
            negaAppForce.setLayoutX(origin + applyForce/2);
            negaAppForce.setFitWidth(-applyForce/2);

            appForceLabel.setLayoutX(negaAppForce.getLayoutX() + negaAppForce.getFitWidth()/2);
//            appForceLabel.setVisible(isShow);
            posiAppForce.setVisible(false);
            negaAppForce.setVisible(isShow);

        } else {
            appForceLabel.setVisible(false);
            posiAppForce.setVisible(false);
            negaAppForce.setVisible(false);
        }

    }
    public void updateFrictionVector(double fricForce, boolean isShow, boolean isShowValue){
        fricLabel.setText(String.format("%.0f", fricForce) + "N");
        fricLabel.setVisible(isShow && isShowValue);
        if (fricForce > 0){
            posiFricForce.setFitWidth(fricForce/2);
            fricLabel.setLayoutX(posiFricForce.getLayoutX() + posiFricForce.getFitWidth()/2);
            posiFricForce.setVisible(isShow);
            negaFricForce.setVisible(false);
        } else if (fricForce < 0) {
            negaFricForce.setLayoutX(origin + (fricForce)/2);
            negaFricForce.setFitWidth(-fricForce/2);
            fricLabel.setLayoutX(negaFricForce.getLayoutX() + negaFricForce.getFitWidth()/2);
            posiFricForce.setVisible(false);
            negaFricForce.setVisible(isShow);
        } else {
            fricLabel.setVisible(false);
            negaFricForce.setVisible(false);
            posiFricForce.setVisible(false);
        }
    }
    public void updateSumForce(double applyForce, double fricForce, boolean isShow, boolean isShowValue){
        sumLabel.setText(String.format("%.0f", applyForce+fricForce) + "N");
        sumLabel.setVisible(isShow && isShowValue);
        if (applyForce + fricForce > 0){
            posiSumForce.setFitWidth((applyForce + fricForce)/2);
            sumLabel.setLayoutX(posiSumForce.getLayoutX() + posiSumForce.getFitWidth()/2);
            posiSumForce.setVisible(isShow);
            negaSumForce.setVisible(false);
        } else if (applyForce+fricForce < 0) {
            negaSumForce.setLayoutX(origin + (applyForce + fricForce)/2);
            negaSumForce.setFitWidth((-applyForce-fricForce)/2);
            sumLabel.setLayoutX(negaSumForce.getLayoutX() + negaSumForce.getFitWidth()/2);
            negaSumForce.setVisible(isShow);
            posiSumForce.setVisible(false);
        } else {
            sumLabel.setVisible(false);
            posiSumForce.setVisible(false);
            negaSumForce.setVisible(false);
        }
    }
}
