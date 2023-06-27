package soict.dsai.group12.forcesimulation;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import soict.dsai.group12.forcesimulation.Object.CubicBox;
import soict.dsai.group12.forcesimulation.Object.Cylinder;
import soict.dsai.group12.forcesimulation.Object.MainObject;

public class SliderController implements Initializable {

    private MainObject mainObject;
    private Timeline sliderTimeline;
    private double staticCoefficient, kineticCoefficient;

    @FXML
    Slider slider;
    @FXML
    Label forceLabel;


    public SliderController(MainObject mainObject, double staticCoefficient, double kineticCoefficient){
        this.mainObject =  mainObject;
        this.staticCoefficient = staticCoefficient;
        this.kineticCoefficient = kineticCoefficient;
    }

    public void setMainObject(MainObject mainObject) {
        this.mainObject = mainObject;
    }

    public MainObject getMainObject() {
        return mainObject;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        slider.setOnMouseDragged(e -> {


            if (sliderTimeline != null){sliderTimeline.stop();}
            Duration newDuration = Duration.millis(10); // Set the new duration
            KeyFrame newKeyFrame = new KeyFrame(newDuration,event -> {
                update();
            });;
            sliderTimeline = new Timeline(newKeyFrame);
            sliderTimeline.setCycleCount(Animation.INDEFINITE);
            sliderTimeline.play();
            String formattedValue = String.format("%.0f", slider.getValue());
            forceLabel.setText(formattedValue + " newtons");
        });
        slider.setOnMouseReleased(e -> {
            slider.setValue(0);
            String formattedValue = String.format("%.0f", slider.getValue());
            forceLabel.setText(formattedValue + " newtons");




            if (sliderTimeline != null){sliderTimeline.stop();}
            Duration newDuration = Duration.millis(10); // Set the new duration
            KeyFrame newKeyFrame = new KeyFrame(newDuration, event -> {
                update();
            });;
            sliderTimeline = new Timeline(newKeyFrame);
            sliderTimeline.setCycleCount(Animation.INDEFINITE);
            sliderTimeline.play();
        });
    }

    void update(){
        double appliedForce = slider.getValue();
        double acceleration = (appliedForce + mainObject.friction(appliedForce, staticCoefficient, kineticCoefficient))/mainObject.getMass();
        mainObject.setAcceleration(acceleration);
        if (mainObject.getVelocity()*(mainObject.getVelocity() + 0.01 * acceleration) < 0 ){
            mainObject.setVelocity(0);
        } else {
            mainObject.setVelocity(mainObject.getVelocity() + 0.01 * acceleration);
        }
        if (mainObject instanceof Cylinder){
            double newGamma = ((Cylinder) mainObject).calculateGamma(mainObject.friction(appliedForce, staticCoefficient, kineticCoefficient), mainObject.getMass(), mainObject.getMass());
            ((Cylinder) mainObject).setGamma(newGamma);
            if (((Cylinder) mainObject).getOmega()*(((Cylinder) mainObject).getOmega() + 0.01*newGamma) < 0){
                ((Cylinder) mainObject).setOmega(0);
            } else {
                ((Cylinder) mainObject).setOmega(((Cylinder) mainObject).getOmega() + 0.01*newGamma);
            }
            if (((Cylinder) mainObject).getTheta()*(((Cylinder) mainObject).getTheta() + 0.01*((Cylinder) mainObject).getOmega()) < 0){
                ((Cylinder) mainObject).setTheta(0);
            } else {
                ((Cylinder) mainObject).setTheta(((Cylinder) mainObject).getTheta() + 0.01* ((Cylinder) mainObject).getOmega());
            }


        }

    }

    void setDisableSlider(boolean bool){
        slider.setDisable(bool);
    }
}
