/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.FXML.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * FXML Controller class
 *
 * @author isanfurg
 */
public class MainWindowsController implements Initializable {

    @FXML private TextField wordsField;
    @FXML private TextField xField;
    @FXML private TextField yField;
    @FXML private TextField rotationField;
    @FXML private TextField exprField;
    @FXML private Button buttonInvert;
    @FXML private AnchorPane canvas;
    private TextFlow phrase;
    
    
    private double FSIZE_11 = 11;
    private double FSIZE_12 = 22;
    private double FSIZE_13 = 33;
    private double FSIZE_14 = 44;
    private Font defaultFont = Font.loadFont("file:AlexBrush-Regular.ttf", FSIZE_11);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        phrase = new TextFlow();
        phrase.setStyle("-fx-border-color: black;");
        canvas.getChildren().add(phrase);
        
        
    }    


    @FXML
    private void wordsTyped(KeyEvent event) {
    }

    @FXML
    private void XTyped(KeyEvent event) {
    }

    @FXML
    private void yTyped(KeyEvent event) {
    }

    @FXML
    private void rotationTyped(KeyEvent event) {
    }

    @FXML
    private void exprTyped(KeyEvent event) {
    }

    @FXML
    private void buttonHelp(ActionEvent event) {
    }

    @FXML
    private void buttonCharOne(ActionEvent event) {
    }

    @FXML
    private void buttonCharTwo(ActionEvent event) {
    }

    @FXML
    private void buttonCharThree(ActionEvent event) {
    }

    @FXML
    private void buttonCharFour(ActionEvent event) {
    }

    @FXML
    private void buttonCharFive(ActionEvent event) {
    }

    @FXML
    private void buttonCharSix(ActionEvent event) {
    }

    @FXML
    private void buttonCharSeven(ActionEvent event) {
    }

    @FXML
    private void buttonInvert(ActionEvent event) {
    }

    @FXML
    private void buttonApply(ActionEvent event) {
        if(!wordsField.getText().trim().isEmpty()){
            ObservableList itemsTF = phrase.getChildren();
            
            String phraseStr = wordsField.getText();
            String[] words = phraseStr.split(" ");
            
            itemsTF.clear();
            for (String word : words) {
                Text itemTF = new Text(word+" ");
                itemTF.setFont(defaultFont);
                itemsTF.add(itemTF);
            }
            
        }
        if(!exprField.getText().trim().isEmpty())
        if(!xField.getText().trim().isEmpty())
        if(!yField.getText().trim().isEmpty())
        if(!rotationField.getText().trim().isEmpty()){
            
        }
     
    }
    
    private boolean isOut(){
        Bounds textBounds = phrase.getBoundsInParent();
        double widthCanvas = canvas.getWidth();
        double heightCanvas = canvas.getHeight();
        double lowerRightCornerX = textBounds.getMaxX();
        double lowerRightCornerY = textBounds.getMaxY();
        double upperLeftCornerX = textBounds.getMinX();
        double upperLeftCornerY = textBounds.getMinY();
        double upperRightCornerX = textBounds.getMaxX();
        double upperRightCornerY = lowerRightCornerY - upperLeftCornerY;
        double lowerLeftCornerX = textBounds.getMinX();
        double lowerLeftCornerY = lowerRightCornerY - upperLeftCornerY;
        
        
        boolean isOut = false;
        
        //Cuando se sale de los límites
        if(lowerRightCornerX > widthCanvas || lowerRightCornerY > heightCanvas){
            isOut = true;
        }
        
        if(upperRightCornerX > widthCanvas || upperRightCornerY > heightCanvas){
            isOut = true;
        }
        
        if(upperLeftCornerX > widthCanvas || upperLeftCornerY > heightCanvas){
            isOut = true;
        }
        
        if(lowerLeftCornerX > widthCanvas || lowerLeftCornerY > heightCanvas){
            isOut = true;
        }
        
        //Alguna esquina es menor a 0
        if(lowerRightCornerX < 0 || lowerRightCornerY < 0){
            isOut = true;
        }
        
        if(upperRightCornerX < 0 || upperRightCornerY < 0){
            isOut = true;
        }
        
        if(upperLeftCornerX < 0 || upperLeftCornerY < 0){
            isOut = true;
        }
        
        if(lowerLeftCornerX < 0 || lowerLeftCornerY < 0){
            isOut = true;
        }
        
        return isOut;
        
        
        
    }
    
}
