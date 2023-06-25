package soict.dsai.group12.forcesimulation;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import soict.dsai.group12.forcesimulation.Object.CubicBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SceneController implements Initializable {
    private Duration originalDuration = Duration.millis(10);
    private CubicBox cubicBox;
    private double staticCoefficient, kineticCoefficient;


    RoadController roadController;

    @FXML
    AnchorPane roadPane;
    @FXML
    AnchorPane sliderPane;
    @FXML
    Button btnStop, btnPlay, btnRestart;



    KeyFrame keyFrame = new KeyFrame(originalDuration, event -> {
        roadController.move(cubicBox.getVelocity());

    });
    Timeline timeline = new Timeline(keyFrame);






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        staticCoefficient = 0.4;
        kineticCoefficient = 0.3;
        cubicBox = new CubicBox(20, 20,0,0);
        //Load road

        FXMLLoader loader = new FXMLLoader(getClass().getResource("road.fxml"));

        roadController = new RoadController();
        loader.setController(roadController);
        try {
            roadPane.getChildren().add(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Load slider
        FXMLLoader loaderSlider = new FXMLLoader(getClass().getResource("slider.fxml"));

        SliderController sliderController = new SliderController(cubicBox, staticCoefficient, kineticCoefficient);
        loaderSlider.setController(sliderController);
        try {
            sliderPane.getChildren().add(loaderSlider.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    @FXML
    void btnStopPressed(){
        timeline.stop();
    }
    @FXML
    void btnPlayPressed(){
        timeline.play();
    }
    @FXML
    void btnRestartPressed(){
        cubicBox.setVelocity(0);
        roadController.restartRoad();
        timeline.play();
    }


}
