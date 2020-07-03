/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.FXML.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
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
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    }
    
}
