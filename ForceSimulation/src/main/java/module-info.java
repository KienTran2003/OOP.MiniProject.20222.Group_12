module soict.dsai.group12.forcesimulation {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens soict.dsai.group12.forcesimulation to javafx.fxml;
    exports soict.dsai.group12.forcesimulation;
    exports soict.dsai.group12.forcesimulation.Object;
    opens soict.dsai.group12.forcesimulation.Object to javafx.fxml;
}