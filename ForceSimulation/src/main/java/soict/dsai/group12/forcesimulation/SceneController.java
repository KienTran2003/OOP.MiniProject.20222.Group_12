package soict.dsai.group12.forcesimulation;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import soict.dsai.group12.forcesimulation.Object.CubeBox;
import soict.dsai.group12.forcesimulation.Object.Cylinder;
import soict.dsai.group12.forcesimulation.Object.MainObject;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class SceneController implements Initializable {
    private Duration originalDuration = Duration.millis(10);
    private CubeBox cubeBox;
    private Cylinder cylinder;
    private double staticCoefficient, kineticCoefficient;
    private MainObject mainObject;
    private Rotate rotation = new Rotate();
    private SliderController sliderController;
    private InfoController infoController;
    private CheckboxController checkboxController;
    private BackgroundController backgroundController;
    private ForceController forceController;



    private RoadController roadController;

    @FXML
    AnchorPane roadPane;
    @FXML
    AnchorPane sliderPane;
    @FXML
    AnchorPane infoPane;
    @FXML
    AnchorPane checkboxPane;
    @FXML
    AnchorPane backgroundPane;
    @FXML
    AnchorPane vectorPane;

    @FXML
    Button btnStop, btnRestart;
    @FXML
    Circle circle;
    @FXML
    Rectangle recBox;
    @FXML
    TextField textMass;
    @FXML
    Slider staticSlider, kineticSlider;

    KeyFrame keyFrame = new KeyFrame(originalDuration, event -> {
        roadController.move(sliderController.getMainObject().getVelocity());


        rotation.setAngle(rotation.getAngle() + cylinder.getVelocity());
        textMass.setText(sliderController.getMainObject().getMass() + " Kg");
        if (recBox.getLayoutX() == 500 || circle.getLayoutX() == 600){
            textMass.setVisible(checkboxController.getMassBox());
        }
        infoController.showAccelerate(checkboxController.getAccelerateBox());
        infoController.showPosi(checkboxController.getPosiBox());
        infoController.showVelo(checkboxController.getVeloBox());

        backgroundController.move(sliderController.getMainObject().getVelocity());



    });
    Timeline timeline = new Timeline(keyFrame);






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        staticCoefficient = 0;
        kineticCoefficient = 0;
        cubeBox = new CubeBox(20, 20);
        cylinder = new Cylinder(5, 20);
        rotation.setPivotX(circle.getCenterX()); // Set pivot point at the center of the circle
        rotation.setPivotY(circle.getCenterY());
        rotation.setAngle(0); // Set rotation speed (in degrees per frame)
        circle.getTransforms().add(rotation);

        recBox.setFill(new ImagePattern(new Image("C:\\Users\\Lenovo\\Desktop\\HUST\\20222\\OOP\\MiniProject\\OOP.MiniProject.20222.Group_12\\ForceSimulation\\src\\main\\resources\\soict\\dsai\\group12\\forcesimulation\\Image\\cube_image.png")));
//        btnStop.getStyleClass().add("button");
//        FileInputStream input;
//        try {
//            input = new FileInputStream("C:\\Users\\Lenovo\\Desktop\\HUST\\20222\\OOP\\MiniProject\\OOP.MiniProject.20222.Group_12\\ForceSimulation\\src\\main\\resources\\soict\\dsai\\group12\\forcesimulation\\Image\\btn.jpg");
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        Image image = new Image(input);
//        ImageView imageView = new ImageView(image);
//        btnStop.setGraphic(imageView);
        //Load road

        FXMLLoader loader = new FXMLLoader(getClass().getResource("road.fxml"));

        roadController = new RoadController();
        loader.setController(roadController);
        try {
            roadPane.getChildren().add(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Load vector pane

        FXMLLoader loaderVector = new FXMLLoader(getClass().getResource("force.fxml"));
        forceController = new ForceController();
        loaderVector.setController(forceController);
        try {
            vectorPane.getChildren().add(loaderVector.load());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Load check box pane
        FXMLLoader loaderCheckBox = new FXMLLoader(getClass().getResource("checkbox.fxml"));
        checkboxController = new CheckboxController();
        loaderCheckBox.setController(checkboxController);
        try {
            checkboxPane.getChildren().add(loaderCheckBox.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Load slider
        FXMLLoader loaderSlider = new FXMLLoader(getClass().getResource("slider.fxml"));

        sliderController = new SliderController(cylinder, staticCoefficient, kineticCoefficient, forceController, checkboxController);

        loaderSlider.setController(sliderController);

        try {
            sliderPane.getChildren().add(loaderSlider.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Load information slider
        FXMLLoader loaderInfo = new FXMLLoader(getClass().getResource("info.fxml"));

        infoController = new InfoController(sliderController.getMainObject());

        loaderInfo.setController(infoController);

        try {
            infoPane.getChildren().add(loaderInfo.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //Load background pane
        FXMLLoader loaderBackground = new FXMLLoader(getClass().getResource("background.fxml"));
        backgroundController = new BackgroundController();
        loaderBackground.setController(backgroundController);
        try {
            backgroundPane.getChildren().add(loaderBackground.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //Set funtion on slider to change friction coefficient
        staticSlider.setOnMouseDragged(e -> {
            this.staticCoefficient = staticSlider.getValue();
            sliderController.setStaticCoefficient(staticCoefficient);
        });
        staticSlider.setOnMouseClicked(e -> {
            this.staticCoefficient = staticSlider.getValue();
            sliderController.setStaticCoefficient(staticCoefficient);
        });
        kineticSlider.setOnMouseDragged(e -> {
            this.kineticCoefficient = kineticSlider.getValue();
            sliderController.setKineticCoefficient(kineticCoefficient);
        });
        kineticSlider.setOnMouseClicked(e -> {
            this.kineticCoefficient = kineticSlider.getValue();
            sliderController.setKineticCoefficient(kineticCoefficient);
        });

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }
    @FXML
    void btnStopPressed(){

        if (timeline.getStatus() == Animation.Status.RUNNING){
            timeline.stop();
            btnStop.setText("Play");
        } else {
            timeline.play();
            btnStop.setText("Stop");
        }
//        timeline.stop();

    }

    @FXML
    void btnRestartPressed(){
        sliderController.getMainObject().resetObject();
        roadController.restartRoad();
        btnStop.setText("Stop");
        timeline.play();
    }
    @FXML
    void setBox(){
        if (recBox.getLayoutX() == 500){
            if (cubeBox.getVelocity() == 0) {
                recBox.setLayoutX(300);
                recBox.setLayoutY(640);
                sliderController.setDisableSlider(true);
                textMass.setVisible(false);
            }

        } else {
            if (cylinder.getVelocity() == 0){
                cubicBoxInput();
            }

        }
    }
    @FXML
    void setCylinder(){
        if (circle.getLayoutX() == 600){
            if (cylinder.getVelocity() == 0) {
                circle.setLayoutX(160);
                circle.setLayoutY(740);
                sliderController.setDisableSlider(true);
                textMass.setVisible(false);
            }
        } else {
            if (cubeBox.getVelocity() == 0){
                cylinderInput();
            }

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

                    if (recBox.getLayoutX() == 500) {
                        recBox.setLayoutX(300);
                        recBox.setLayoutY(640);
                    }
                    circle.setLayoutX(600);
                    circle.setLayoutY(400);
                    cylinder.resetObject();
                    sliderController.setMainObject(cylinder);
                    infoController.setMainObject(cylinder);
                    sliderController.setDisableSlider(false);





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
                    cubeBox.setMass(mass);
                    cubeBox.setSide(radius);

                    if (circle.getLayoutX() == 600) {
                        circle.setLayoutX(160);
                        circle.setLayoutY(740);
                    }
                    recBox.setLayoutX(500);
                    recBox.setLayoutY(300);
                    cubeBox.resetObject();
                    sliderController.setMainObject(cubeBox);
                    infoController.setMainObject(cubeBox);
                    sliderController.setDisableSlider(false);




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
