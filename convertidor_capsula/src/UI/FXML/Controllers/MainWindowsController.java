/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.FXML.Controllers;

import Logic.ApplyFormat;
import Logic.Drag;
import Logic.FormatExpr;
import Logic.Invert;
import Logic.Rotate;
import Logic.Translate;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author isanfurg
 */
public class MainWindowsController implements Initializable, Rotate, Translate, ApplyFormat, Invert, Drag{

    @FXML private TextField wordsField;
    @FXML private TextField xField;
    @FXML private TextField yField;
    @FXML private TextField rotationField;
    @FXML private TextField exprField;
    @FXML private Button buttonInvert;
    @FXML private AnchorPane canvas;
    private TextFlow phrase;
    private Font regularFont = Font.loadFont("file:regularfix.ttf", 18);
    private Text alert;
    @FXML private Text phraseAlert;
    @FXML  private Text translateAlert;
    @FXML private Text rotateAlert;
    @FXML private Text exprAlert;
    @FXML
    private BorderPane mainPane;
    String oldW;
    String oldX;
    String oldY;
    String oldR;
    String oldE;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        phrase = new TextFlow();
        this.drag(mainPane);
        phrase.setStyle("-fx-border-color: black;"); //Solo para desarrolladores
        canvas.getChildren().add(phrase);    
    }    
    
    @FXML
    private void wordsTyped(KeyEvent event) {
        if(event.isControlDown()){
            event.consume();
            return;
        }
        char pressed = event.getCharacter().charAt(0);
        if(!(Character.isLetterOrDigit(pressed) || pressed == ' ')){
            event.consume();
            return;
        }
    }

    @FXML
    private void XTyped(KeyEvent event) {
        if(event.isControlDown()){
            event.consume();
            return;
        }
        char pressed = event.getCharacter().charAt(0);
        if(!Character.isDigit(pressed))event.consume(); //Limit to only numbers
        if(xField.getText().length()>0){
            System.out.println(xField.getText()+pressed);
            
            if(Integer.parseInt(xField.getText()+pressed)>400){
                
                event.consume();
            }
        }//Limit to the X position
 
    }

    @FXML
    private void yTyped(KeyEvent event) {
                if(event.isControlDown()){
            event.consume();
            return;
        }
        char pressed = event.getCharacter().charAt(0);
        if(!Character.isDigit(pressed))event.consume(); //Limit to only numbers
        if(yField.getText().length()>0){
            System.out.println(yField.getText()+pressed);
            
            if(Integer.parseInt(yField.getText()+pressed)>400){
                
                event.consume();
            }
        }//Limit to the Y position
    }

    @FXML
    private void rotationTyped(KeyEvent event) {
        if(event.isControlDown()){
            event.consume();
            return;
        }
        char pressed = event.getCharacter().charAt(0);
        if(!Character.isDigit(pressed)){
            event.consume();
            return;
        }
        int onField  = Integer.parseInt(rotationField.getText()+pressed);
        if(onField>=360){
            onField = onField%360;
            rotationField.setText(onField+"");
            event.consume();
        }   

    }

    @FXML
    private void exprTyped(KeyEvent event) {
        char pressed = event.getCharacter().charAt(0);
        if(!(Character.isLetterOrDigit(pressed) || pressed == ' '|| pressed == '+' || pressed == ',')){
            event.consume();
            return;
        }
    }

    @FXML
    private void buttonHelp(ActionEvent event) throws IOException {
        System.out.println("No implemented jet");

    }

    @FXML
    private void buttonCharOne(ActionEvent event) {
        wordsField.setText(wordsField.getText()+"«");
    }

    @FXML
    private void buttonCharTwo(ActionEvent event) {
        wordsField.setText(wordsField.getText()+"»");
    }

    @FXML
    private void buttonCharThree(ActionEvent event) {
        wordsField.setText(wordsField.getText()+"“");
        
    }

    @FXML
    private void buttonCharFour(ActionEvent event) {
        wordsField.setText(wordsField.getText()+"”");
    }

    @FXML
    private void buttonCharFive(ActionEvent event) {
        wordsField.setText(wordsField.getText()+"‘");
    }

    @FXML
    private void buttonCharSix(ActionEvent event) {
        wordsField.setText(wordsField.getText()+"’");
    }

    @FXML
    private void buttonCharSeven(ActionEvent event) {
        wordsField.setText(wordsField.getText()+"...");
    }

    @FXML
    private void buttonInvert(ActionEvent event) {
        this.invert(phrase);
    }
    
    /**
     * Obtiene los datos desde los campos de texto y aplica los cambios respectivos
     * a cada uno.
     */
    @FXML
    private void buttonApply() {
        phraseAlert.setText("");
        exprAlert.setText("");
        rotateAlert.setText("");
        translateAlert.setText("");
        phrase.setRotate(0);
        this.translate(phrase, 0, 0);
        /*Muestra la palabra en pantalla si y solo si el campo de texto correspondiente
        a la palabra no esté vacío y la palabra ingresada no sea la misma que la anterior.*/
        if(!wordsField.getText().trim().isEmpty()){
            ObservableList itemsTF = phrase.getChildren();
            
            String phraseStr = wordsField.getText();
            String[] words = phraseStr.split(" ");
            itemsTF.clear();
            int i = 0;
            for (String word : words) {
                if(!word.equals("")){
                    Text itemTF = new Text(word);
                    itemTF.setFont(regularFont);
                    itemsTF.add(itemTF);
                    i++;
                    if(!(i==words.length)) itemsTF.add(new Text(" "));
                }
            }               
        }
        else phraseAlert.setText("Debe ingresar una frase");
        
        /*Aplica el formato a cada palabra de la frase si y solo si su campo de 
        texto no esté vacío. Utiliza la interface "ApplyFormat".*/
        if(!exprField.getText().trim().isEmpty() ){
            FormatExpr fexp = new FormatExpr(exprField.getText());
            if(fexp.isValid()) {
                this.applyFormat(phrase, exprField.getText());
                if(isOut()){
                    translateAlert.setText("La palabra se sale de los límites");
                    this.applyFormat(phrase, ",,");
                }
            }
            else exprAlert.setText("Expresión no válida");
        }
        
        /*Rota la palabra si y solo si el campo de texto de los grados no esté
        vacío. Utiliza la interface "Rotate".*/
        if(!rotationField.getText().trim().isEmpty()){
            double degrees = Double.parseDouble(rotationField.getText());
            if(degrees >= 0 && degrees <= 360) {
                double oldDegrees = phrase.getRotate();
                this.rotate(phrase, degrees);
                if(isOut()){
                    translateAlert.setText("La palabra se sale de los límites");
                    this.rotate(phrase, oldDegrees);
                }
            }
            else rotateAlert.setText("Grado no válido");
        }
        
        /*Obtiene una coordenada cartesiana para trasladar la palabra si y solo
        si el campo de cada componente no está vacío. Utiliza la interface 
        "Translate".*/
        if(!xField.getText().trim().isEmpty() && !yField.getText().trim().isEmpty()){
            double oldX = phrase.getLayoutX();
            double oldY = phrase.getLayoutY();
            double x = Double.parseDouble(xField.getText());
            double y = Double.parseDouble(yField.getText());
            this.translate(phrase, x, y);
            if(isOut()){
                translateAlert.setText("La palabra se sale de los límites");
                this.translate(phrase, oldX, oldY);
            }
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

    @FXML
    private void buttonMinimize(ActionEvent event) {
        ((Stage)mainPane.getScene().getWindow()).setIconified(true);
    }

    @FXML
    private void buttonClose(ActionEvent event) {
         System.exit(0);
    }
    
}
