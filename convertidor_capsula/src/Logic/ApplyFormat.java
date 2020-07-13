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
        double FSIZE_11 = 18;
        double FSIZE_12 = 22;
        double FSIZE_13 = 33;
        double FSIZE_14 = 44;
        Font regularFont = Font.loadFont("file:regular.ttf", 11);
        Font boldFont = Font.loadFont("file:bold.ttf", 11);
        Font italicFont = Font.loadFont("file:italic.ttf", 11);
        Font boldItalicFont = Font.loadFont("file:bold-italic.ttf", 11);
        FormatExpr formatExpr = new FormatExpr(expr);
        
        //Si la expresión es válida, se aplican los formatos
        if(formatExpr.isValid()){
            System.out.println("Format expr valid");
            ObservableList textItems = text.getChildren();
            String[][] charactList = formatExpr.getCharactList();
            
            for (int i = 0; i < charactList.length && i < textItems.size(); i++) {
                Text actualWord = (Text) textItems.get(i);
                actualWord.setFont(regularFont);
                boolean isRegular = false;
                boolean isBold = false;
                boolean isItalic = false;
                Font font = actualWord.getFont();
                double fontSize = font.getSize();
                String[] characts = charactList[i];
                for (String charact : characts) {
                    if(charact.equals("n")){
                        isBold = true;
                    }
                    else if(charact.equals("s")){
                        actualWord.setUnderline(true);
                    }
                    
                    else if(charact.equals("k")){
                        isItalic = true;
                    }
                    else{
                        isRegular = true;
                        if(charact.equals("11")){
                            fontSize = FSIZE_11;
                        }
                            
                        else if(charact.equals("12")){
                            fontSize = FSIZE_12;
                        }
                            
                        else if(charact.equals("13")){
                            fontSize = FSIZE_13;
                        }
                            
                        else{
                            fontSize = FSIZE_14;
                        }   
                    }
                }
                if(isRegular){
                    regularFont = Font.loadFont("file:regular.ttf", fontSize);
                    actualWord.setFont(regularFont);
                    
                }
                if(isBold) {
                    boldFont = Font.loadFont("file:bold.ttf", fontSize);
                    actualWord.setFont(boldFont);
                }
                if (isItalic) {
                    italicFont = Font.loadFont("file:italic.ttf", fontSize);
                    actualWord.setFont(italicFont);
                }
                if(isBold && isItalic){
                    boldItalicFont = Font.loadFont("file:bold-italic.ttf", fontSize);
                    actualWord.setFont(boldItalicFont);
                }

            }
        }
        else System.out.println("Format expr not valid");
        
        return text;
    }
}
