package soict.dsai.group12.forcesimulation.Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.util.Duration;
import soict.dsai.group12.forcesimulation.Model.Object.Cylinder;
import soict.dsai.group12.forcesimulation.Model.Object.MainObject;
import soict.dsai.group12.forcesimulation.Model.Surface.Surface;

import java.net.URL;
import java.util.ResourceBundle;

public class SliderController implements Initializable {

    private final int MAX_SPEED = 50;
    private MainObject mainObject;
    private ForceController forceController;
    private CheckboxController checkboxController;
    private Surface surface;

    private Timeline sliderTimeline;



    @FXML
    Slider slider;
    @FXML
    Label forceLabel;


    public SliderController(MainObject mainObject,Surface surface, ForceController forceController, CheckboxController checkboxController){
        this.mainObject =  mainObject;

        this.surface = surface;
        this.forceController = forceController;
        this.checkboxController = checkboxController;
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

            if (mainObject.getVelocity() > 50 && slider.getValue() > 0){
                this.slider.setValue(0);

            } else if (mainObject.getVelocity() < -50 && slider.getValue() < 0) {
                this.slider.setValue(0);
            } else {
                if (sliderTimeline != null){sliderTimeline.stop();}
                Duration newDuration = Duration.millis(10);
                KeyFrame newKeyFrame = new KeyFrame(newDuration,event -> {

                    update();
                });

                forceController.updateAllForce(slider.getValue(), mainObject.calculateFrictionForces(slider.getValue(), surface), checkboxController.getForceBox(),checkboxController.getSumBox(), checkboxController.getValueBox());

                sliderTimeline = new Timeline(newKeyFrame);
                sliderTimeline.setCycleCount(Animation.INDEFINITE);
                sliderTimeline.play();
                String formattedValue = String.format("%.0f", slider.getValue());
                forceLabel.setText(formattedValue + " newtons");
            }


        });
        slider.setOnMouseReleased(e -> {
            slider.setValue(0);
            String formattedValue = String.format("%.0f", slider.getValue());
            forceLabel.setText(formattedValue + " newtons");

            forceController.updateAllForce(slider.getValue(), mainObject.calculateFrictionForces(slider.getValue(), surface), checkboxController.getForceBox(),checkboxController.getSumBox(), checkboxController.getValueBox());



            if (sliderTimeline != null){sliderTimeline.stop();}
            Duration newDuration = Duration.millis(10);
            KeyFrame newKeyFrame = new KeyFrame(newDuration, event -> {
                update();
            });
            sliderTimeline = new Timeline(newKeyFrame);
            sliderTimeline.setCycleCount(Animation.INDEFINITE);
            sliderTimeline.play();
        });
    }

    void update(){
        double appliedForce = slider.getValue();
        double friction = mainObject.calculateFrictionForces(slider.getValue(), surface);
        double acceleration = mainObject.calculateAcceleration(appliedForce, surface);

        forceController.updateAllForce(slider.getValue(), friction , checkboxController.getForceBox(),checkboxController.getSumBox(), checkboxController.getValueBox());

        if (mainObject instanceof Cylinder){
            ((Cylinder) mainObject).updateAttribute(appliedForce, surface);
        } else {
            mainObject.updateAttribute(appliedForce, surface);
        }
    }

    void setDisableSlider(boolean bool){
        this.slider.setDisable(bool);
    }
    void updateLimitVelo(){
        if (mainObject.getVelocity() > MAX_SPEED && slider.getValue() > 0){
            this.slider.setValue(0);
        } else if (mainObject.getVelocity() < -MAX_SPEED && slider.getValue() < 0) {
            this.slider.setValue(0);
        }
    }
}
