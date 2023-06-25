package soict.dsai.group12.forcesimulation;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import soict.dsai.group12.forcesimulation.Object.CubicBox;

public class SliderController implements Initializable {

    private CubicBox cubicBox;
    private Timeline sliderTimeline;
    private double staticCoefficient, kineticCoefficient;
    @FXML
    Slider slider;

    public SliderController(CubicBox cubicBox, double staticCoefficient, double kineticCoefficient){
        this.cubicBox =  cubicBox;
        this.staticCoefficient = staticCoefficient;
        this.kineticCoefficient = kineticCoefficient;
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
        });
        slider.setOnMouseReleased(e -> {
            slider.setValue(0);




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
        double acceleration = (appliedForce + cubicBox.friction(appliedForce, staticCoefficient, kineticCoefficient, cubicBox.getVelocity()))/cubicBox.getMass();
        cubicBox.setAcceleration(acceleration);
        if (cubicBox.getVelocity()*(cubicBox.getVelocity() + 0.01 * acceleration) < 0 ){
            cubicBox.setVelocity(0);
        } else {
            cubicBox.setVelocity(cubicBox.getVelocity() + 0.01 * acceleration);
        }

    }
}
