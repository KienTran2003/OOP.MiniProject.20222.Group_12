package soict.dsai.group12.forcesimulation;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import soict.dsai.group12.forcesimulation.Object.CubicBox;
import soict.dsai.group12.forcesimulation.Object.Cylinder;
import soict.dsai.group12.forcesimulation.Object.MainObject;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class SceneController implements Initializable {
    private Duration originalDuration = Duration.millis(10);
    private CubicBox cubicBox;
    private Cylinder cylinder;
    private double staticCoefficient, kineticCoefficient;
    private MainObject mainObject;
    private Rotate rotation = new Rotate();
    private SliderController sliderController;



    RoadController roadController;

    @FXML
    AnchorPane roadPane;
    @FXML
    AnchorPane sliderPane;
    @FXML
    Button btnStop, btnPlay, btnRestart;
    @FXML
    Circle circle;
    @FXML
    Rectangle recBox;
    @FXML
    TextField textMass;

    KeyFrame keyFrame = new KeyFrame(originalDuration, event -> {
        roadController.move(sliderController.getMainObject().getVelocity());

        rotation.setAngle(rotation.getAngle() + cylinder.getVelocity());

    });
    Timeline timeline = new Timeline(keyFrame);






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        staticCoefficient = 0.4;
        kineticCoefficient = 0.3;
        cubicBox = new CubicBox(20, 20);
        cylinder = new Cylinder(5,20);
        rotation.setPivotX(circle.getCenterX()); // Set pivot point at the center of the circle
        rotation.setPivotY(circle.getCenterY());
        rotation.setAngle(0); // Set rotation speed (in degrees per frame)
        circle.getTransforms().add(rotation);


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

        sliderController = new SliderController(cylinder, staticCoefficient, kineticCoefficient);

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

    }
    @FXML
    void setBox(){
        if (recBox.getLayoutX() == 500){
            if (cubicBox.getVelocity() == 0) {
                recBox.setLayoutX(300);
                recBox.setLayoutY(640);
                sliderController.setDisableSlider(true);
            }

        } else {
//            if (cylinder.getVelocity() == 0) {
//                if (circle.getLayoutX() == 600) {
//                    circle.setLayoutX(160);
//                    circle.setLayoutY(740);
//                }
//                recBox.setLayoutX(500);
//                recBox.setLayoutY(300);
//                sliderController.setMainObject(cubicBox);
//                sliderController.setDisableSlider(false);
//
//            }
            cubicBoxInput();
        }
    }
    @FXML
    void setCylinder(){
        if (circle.getLayoutX() == 600){
            if (cylinder.getVelocity() == 0) {
                circle.setLayoutX(160);
                circle.setLayoutY(740);
                sliderController.setDisableSlider(true);
            }
        } else {
//            if (cubicBox.getVelocity() == 0) {
//                if (recBox.getLayoutX() == 500) {
//                    recBox.setLayoutX(300);
//                    recBox.setLayoutY(640);
//                }
//                circle.setLayoutX(600);
//                circle.setLayoutY(400);
//                sliderController.setMainObject(cylinder);
//                sliderController.setDisableSlider(false);
//                cylinderInput();
//            }
            cylinderInput();
        }
    }

    void cylinderInput(){

        GridPane gridPane = new GridPane();

        gridPane.setHgap(10); // Set horizontal gap between elements
        gridPane.setVgap(10); // Set vertical gap between elements

        // Create labels for mass and radius
        Label massLabel = new Label("Mass:");
        Label radiusLabel = new Label("Radius:");

        // Create text fields for mass and radius
        TextField massTextField = new TextField();
        TextField radiusTextField = new TextField();

        // Add labels and text fields to the GridPane
        gridPane.add(massLabel, 0, 0);
        gridPane.add(massTextField, 1, 0);
        gridPane.add(radiusLabel, 0, 1);
        gridPane.add(radiusTextField, 1, 1);

        // Create an alert dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cylinder Property");
        alert.setHeaderText(null);
        alert.getDialogPane().setContent(gridPane);

        // Show the dialog and wait for the user input
        Optional<ButtonType> result = alert.showAndWait();

        // Process the user input
        result.ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                String massInput = massTextField.getText();
                String radiusInput = radiusTextField.getText();
                try {
                    double mass = Double.parseDouble(massInput);
                    double radius = Double.parseDouble(radiusInput);
                    cylinder.setMass(mass);
                    cylinder.setSide(radius);
                    if (cubicBox.getVelocity() == 0) {
                        if (recBox.getLayoutX() == 500) {
                            recBox.setLayoutX(300);
                            recBox.setLayoutY(640);
                        }
                        circle.setLayoutX(600);
                        circle.setLayoutY(400);
                        sliderController.setMainObject(cylinder);
                        sliderController.setDisableSlider(false);
                    }

                } catch (NumberFormatException e) {
                    // Handle invalid input
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Invalid Input");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Invalid mass or radius input. Please enter numeric values.");
                    errorAlert.showAndWait();
                    cylinderInput();
                }
            }

        });

    }
    void cubicBoxInput(){
        GridPane gridPane = new GridPane();

        gridPane.setHgap(10); // Set horizontal gap between elements
        gridPane.setVgap(10); // Set vertical gap between elements

        // Create labels for mass and radius
        Label massLabel = new Label("Mass:");
        Label radiusLabel = new Label("Side Length:");

        // Create text fields for mass and radius
        TextField massTextField = new TextField();
        TextField sideTextField = new TextField();

        // Add labels and text fields to the GridPane
        gridPane.add(massLabel, 0, 0);
        gridPane.add(massTextField, 1, 0);
        gridPane.add(radiusLabel, 0, 1);
        gridPane.add(sideTextField, 1, 1);

        // Create an alert dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cubic Box Property");
        alert.setHeaderText(null);
        alert.getDialogPane().setContent(gridPane);

        // Show the dialog and wait for the user input
        Optional<ButtonType> result = alert.showAndWait();

        // Process the user input
        result.ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                String massInput = massTextField.getText();
                String sideInput = sideTextField.getText();
                try {
                    double mass = Double.parseDouble(massInput);
                    double radius = Double.parseDouble(sideInput);
                    cubicBox.setMass(mass);
                    cubicBox.setSide(radius);
                    if (cylinder.getVelocity() == 0) {
                        if (circle.getLayoutX() == 600) {
                            circle.setLayoutX(160);
                            circle.setLayoutY(740);
                        }
                        recBox.setLayoutX(500);
                        recBox.setLayoutY(300);
                        sliderController.setMainObject(cubicBox);
                        sliderController.setDisableSlider(false);

                    }

                } catch (NumberFormatException e) {
                    // Handle invalid input
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Invalid Input");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Invalid mass or side length input. Please enter numeric values.");
                    errorAlert.showAndWait();
                    cubicBoxInput();;
                }
            }

        });
    }

}
