package soict.dsai.group12.forcesimulation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class RoadController implements Initializable {
    private Rectangle[] recList ;
    @FXML
    private Rectangle rec1, rec2, rec3, rec4, rec5, rec6, rec7, rec8, rec9, rec10, rec11, rec12;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recList = new Rectangle[]{rec1, rec2, rec3, rec4, rec5, rec6, rec7, rec8, rec9, rec10, rec11, rec12};
    }
    public void move(double deltaX){
        for (Rectangle rec: recList){
            if (rec.getLayoutX() - deltaX < -180){
                rec.setLayoutX(1500 - (deltaX -180 - rec.getLayoutX()));
            } else if (rec.getLayoutX() - deltaX  > 1500){
                rec.setLayoutX(-180 - (deltaX - rec.getLayoutX() + 1500));
            } else {
                rec.setLayoutX(rec.getLayoutX() - deltaX);
            }
        }
    }
    public void restartRoad(){
        for (int i = 0; i< recList.length; i++){
            recList[i].setLayoutX(i*140 - 140);
        }
    }
}
