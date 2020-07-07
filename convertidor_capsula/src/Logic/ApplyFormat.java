/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import javafx.collections.ObservableList;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Aplica los formatos a cada palabra de la frase.
 * @author leunam
 */
public interface ApplyFormat {
    /**
     * Aplica los formatos a cada palabra de la frase.
     * @param text Contenedor gráfico de las palabras de la frase.
     * @param expr Expresión de formatos para cada palabra.
     * @return Contenedor gráfico de las palabras con los cambios ya aplicados. 
     */
    default TextFlow applyFormat(TextFlow text, String expr){
        double FSIZE_11 = 11;
        double FSIZE_12 = 22;
        double FSIZE_13 = 33;
        double FSIZE_14 = 44;
        FormatExpr formatExpr = new FormatExpr(expr);
        
        //Si la expresión es válida, se aplican los formatos
        if(formatExpr.isValid()){
            System.out.println("Format expr valid");
            ObservableList textItems = text.getChildren();
            String[][] charactList = formatExpr.getCharactList();
            
            for (int i = 0; i < charactList.length && i < textItems.size(); i++) {
                Text actualWord = (Text) textItems.get(i);
                String[] characts = charactList[i];
                for (String charact : characts) {
                    if(charact.equals("n")){
                        actualWord.setStyle("-fx-font-weight: bold");
                    }
                    
                    else if(charact.equals("s")){
                        actualWord.setUnderline(true);
                    }
                    
                    else if(charact.equals("k")){
                        actualWord.setStyle("-fx-font-style: italic");
                    }
                    else{
                        if(charact.equals("11"))
                            actualWord.setStyle("-fx-font-size: "+Double.toString(FSIZE_11));
                        else if(charact.equals("12"))
                            actualWord.setStyle("-fx-font-size: "+Double.toString(FSIZE_12));
                        else if(charact.equals("13"))
                            actualWord.setStyle("-fx-font-size: "+Double.toString(FSIZE_13));
                        else
                            actualWord.setStyle("-fx-font-size: "+Double.toString(FSIZE_14));
                        
                    }
                }
                
            }
        }
        
        return null;
    }
}
