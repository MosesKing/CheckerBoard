/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkerboard;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author moe
 */
public class CheckerGrid extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CheckerGridFXML.fxml"));
        Parent root = loader.load();
        CheckerGridFXMLController controller = loader.getController();
        
        Scene scene = new Scene(root, 600, 625);
        
        stage.setTitle("CheckerBoard");
        stage.setScene(scene);
        stage.show();
        
        controller.ready(stage, scene);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
