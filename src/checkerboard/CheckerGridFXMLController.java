/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkerboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author moe
 */
public class CheckerGridFXMLController implements Initializable {
    
   // SET VARIABLES 
    private Stage stage;
    private Scene scene;
    private double boardSize;
    private double boardHeight;
    private int numRowsColumns;
    private Color darkColor;
    private Color lightColor;
    private Grid checkerBoard;
    
    @FXML
    private StackPane stackPane;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DefaultColorScheme();
        setDefaultBoardSize();
    }

    public void ready(Stage stage, Scene scene) {
        this.stage = stage;
        this.scene = scene;
        
        // Lambda expression to allow changing of the window size
        ChangeListener<Number> listener = (ObservableValue<? extends Number> observable, Number oldValue, final Number newValue) -> {
            drawBoard();
        };
        
        scene.widthProperty().addListener(listener);
        scene.heightProperty().addListener(listener);
        
        drawBoard();
    }
    // We're gunna make a switch case so that we can change the size 
    // of the checkerboards
    @FXML
    public void selectSize(ActionEvent event) {
        MenuItem menuItem = (MenuItem)(event.getSource());
        switch(menuItem.getId()) {
            case "16 x 16":
                numRowsColumns = 16;
                break;
            case "10 x 10":
                numRowsColumns = 10;
                break;
            case "8 x 8":
                numRowsColumns = 8;
                break;
            case "3 x 3":
                numRowsColumns = 3;
                break;
            default:
               setDefaultBoardSize();
        }
        drawBoard();
    }

    //Called everytime a new grid is needed
    // use padding to center board
    private void drawBoard() {     
        double sceneWidth = scene.getWidth();
        double sceneHeight = scene.getHeight();
        double horizontalPadding = 0.0;
        double verticalPadding = 0.0;
        
        if (sceneWidth > sceneHeight) {
            boardSize = sceneHeight;
            horizontalPadding = (sceneWidth - boardSize) / 2;
            verticalPadding = 0.0;
        } else {
            boardSize = sceneWidth;
            verticalPadding = (sceneHeight - boardSize) / 2;
            horizontalPadding = 0.0;
        }
        
        stackPane.setPadding(new Insets(verticalPadding, horizontalPadding, verticalPadding, horizontalPadding));
        stackPane.getChildren().clear();
        checkerBoard = new Grid(numRowsColumns, boardSize, lightColor, darkColor);
        stackPane.getChildren().add(checkerBoard.build());
    }

    @FXML
    public void setBoardColorScheme(ActionEvent event) {
        MenuItem menuItem = (MenuItem)(event.getSource());
        switch(menuItem.getId()) {
            case "Blue":
                BlueColorScheme();
                break;
            default:
                DefaultColorScheme();
        }
        drawBoard();
    }

    private void DefaultColorScheme() {
        darkColor = Color.BLACK;
        lightColor = Color.RED;
    }
    
    private void BlueColorScheme() {
        darkColor = Color.DARKBLUE;
        lightColor = Color.SKYBLUE;
    }
    
    private void setDefaultBoardSize() {
        numRowsColumns = 8;
    }
}