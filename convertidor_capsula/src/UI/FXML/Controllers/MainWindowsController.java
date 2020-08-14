package UI.FXML.Controllers;

import Logic.ApplyFormat;
import Logic.ControlPoints;
import Logic.Drag;
import Logic.FormatExpr;
import Logic.Invert;
import Logic.Rotate;
import Logic.Translate;
import java.io.IOException;
import java.net.URL;
import java.text.Normalizer;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
public class MainWindowsController implements Initializable, Rotate, Translate, ApplyFormat, Invert, Drag, ControlPoints{
    @FXML private TextField wordsField;
    @FXML private TextField xField;
    @FXML private TextField yField;
    @FXML private TextField rotationField;
    @FXML private TextField exprField;
    @FXML private AnchorPane canvas;
    private TextFlow phrase;
    private Font regularFont = Font.loadFont("file:regularfix.ttf", 18);
    private Text alert;
    @FXML private Text phraseAlert;
    @FXML private Text translateAlert;
    @FXML private Text rotateAlert;
    @FXML private Text exprAlert;
    @FXML private BorderPane mainPane;
    private Pattern pat;
    String oldW;
    String oldX;
    String oldY;
    String oldR;
    String oldE;
    @FXML
    private Label ncaracteres;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        phrase = new TextFlow();
        pat = Pattern.compile("[a-zA-Z0-9?,]");
        
        this.drag(mainPane);
        canvas.getChildren().add(phrase);
        xField.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
        yField.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
        exprField.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
        wordsField.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
        rotationField.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
        
    }    
    public static void limitTextField(TextField textField, int limit, Label ncaracteres) {
        UnaryOperator<Change> textLimitFilter = change -> {
            if (change.isContentChange()) {
                int newLength = change.getControlNewText().length();
                String cadena = newLength+"/"+(limit+1);
                ncaracteres.setText(cadena);
                if (newLength > limit) {
                    String trimmedText = change.getControlNewText().substring(0, limit);
                    change.setText(trimmedText);
                    int oldLength = change.getControlText().length();
                    change.setRange(0, oldLength);
                }
            }
            return change;
        };
        textField.setTextFormatter(new TextFormatter(textLimitFilter));
    } 
    
    @FXML
    private void wordsTyped(KeyEvent event) {
        limitTextField(wordsField, 199, ncaracteres);
        if(event.isControlDown()){
            wordsField.undo();
            event.consume();
            return;
        }
        char pressed = event.getCharacter().charAt(0);
        Matcher mat = pat.matcher(pressed+"");
        if(!(mat.matches() || pressed == ' ') && pressed != 'ñ' && pressed != 'Ñ'){
            event.consume();
        }
    }
    
    public static String normalizeString(String str){
        str=Normalizer.normalize(str,Normalizer.Form.NFKD);
        return str.replaceAll("[^a-z,^A-Z,^0-9]", "");
    }   
    
    @FXML
    private void XTyped(KeyEvent event) {
        if(event.isControlDown()){
            xField.undo();
            event.consume();
            return;
        }
        char pressed = event.getCharacter().charAt(0);
        if(!Character.isDigit(pressed))event.consume(); //Limit to only numbers
        
 
    }

    @FXML
    private void yTyped(KeyEvent event) {
        if(event.isControlDown()){
            yField.undo();
            event.consume();
            return;
        }
        char pressed = event.getCharacter().charAt(0);
        if(!Character.isDigit(pressed))event.consume(); //Limit to only numbers
       
    }

    @FXML
    private void rotationTyped(KeyEvent event) {
        if(event.isControlDown()){
            event.consume();
            rotationField.undo();
            return;
        }
        char pressed = event.getCharacter().charAt(0);
        if(!Character.isDigit(pressed)){
            event.consume();
        }
    }

    @FXML
    private void exprTyped(KeyEvent event) {
        Pattern patExpr = Pattern.compile("[,+nks]|[1-4]");
        char pressed = event.getCharacter().charAt(0);
        if(event.isControlDown()){
            event.consume();
            exprField.undo();
            return;
        }
        Matcher mat = patExpr.matcher(pressed+"");
        if(!(mat.matches())){
            event.consume();
        }
    }

    @FXML
    private void buttonHelp(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UI/FXML/helpWindows.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.showAndWait();
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
        this.translate(phrase, 10, 10);
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
            }
            else exprAlert.setText("Expresión no válida");
        }
        
        /*Rota la palabra si y solo si el campo de texto de los grados no esté
        vacío. Utiliza la interface "Rotate".*/
        if(!rotationField.getText().trim().isEmpty() && !isNumeric(rotationField.getText())){
            double degrees = Double.parseDouble(rotationField.getText());
            if(degrees >= 0 && degrees <= 360) {
                this.rotate(phrase, degrees);
            }
            else rotateAlert.setText("Grado no válido");
        }
        
        /*Obtiene una coordenada cartesiana para trasladar la palabra si y solo
        si el campo de cada componente no está vacío. Utiliza la interface 
        "Translate".*/
        if(!xField.getText().trim().isEmpty() && !yField.getText().trim().isEmpty() && !isNumeric(xField.getText()) && !isNumeric(yField.getText())){
            double x = Double.parseDouble(xField.getText());
            double y = Double.parseDouble(yField.getText());
            this.translate(phrase, x, y);

        }
    }
    
    private static boolean isNumeric(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    }
    
    @FXML
    private void buttonMinimize(ActionEvent event) {
        ((Stage)mainPane.getScene().getWindow()).setIconified(true);
    }

    @FXML
    private void buttonClose(ActionEvent event) {
         System.exit(0);
    }

    @FXML
    private void points(ActionEvent event) {
        phrase = this.showControlPoints(phrase);
    }
}
