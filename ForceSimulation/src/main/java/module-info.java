module soict.dsai.group12.forcesimulation {
    requires javafx.controls;
    requires javafx.fxml;


    exports soict.dsai.group12.forcesimulation.Object;
    opens soict.dsai.group12.forcesimulation.Object to javafx.fxml;
    exports soict.dsai.group12.forcesimulation.Controller;
    opens soict.dsai.group12.forcesimulation.Controller to javafx.fxml;
    exports soict.dsai.group12.forcesimulation;
    opens soict.dsai.group12.forcesimulation to javafx.fxml;
}