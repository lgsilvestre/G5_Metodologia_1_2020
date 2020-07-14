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
        Font regularFont = Font.loadFont("file:regularfix.ttf", 18);
        FormatExpr formatExpr = new FormatExpr(expr);
        
        //Si la expresión es válida, se aplican los formatos
        if(formatExpr.isValid()){
            System.out.println("Format expr valid");
            ObservableList textItems = text.getChildren();
            String[][] charactList = formatExpr.getCharactList();
            
            int j = 0;
            int i = 0;
            while(i < charactList.length && j < textItems.size()) {
                Text actualWord = (Text) textItems.get(j);
                if(!actualWord.getText().equals(" ")){
                    actualWord.setFont(regularFont);
                    boolean isRegular = false;
                    boolean isBold = false;
                    boolean isItalic = false;
                    double fontSize = FSIZE_11;              
                    String[] characts = charactList[i];

                    for (String charact : characts) {
                        if(!charact.equals("")){
                            if     (charact.equals("n")) isBold = true;
                            else if(charact.equals("s")) actualWord.setUnderline(true);
                            else if(charact.equals("k")) isItalic = true;
                            else{
                                isRegular = true;
                                if     (charact.equals("11")) fontSize = FSIZE_11;
                                else if(charact.equals("12")) fontSize = FSIZE_12;
                                else if(charact.equals("13")) fontSize = FSIZE_13;
                                else                          fontSize = FSIZE_14;
                            }
                        }
                    }
                    Font fontChoosed = regularFont;

                    if(isRegular){
                        fontChoosed = Font.loadFont("file:regularfix.ttf", fontSize);
                    }
                    if(isBold) {
                        fontChoosed = Font.loadFont("file:boldfix.ttf", fontSize);
                    }
                    if (isItalic) {
                        fontChoosed = Font.loadFont("file:italicfix.ttf", fontSize);
                    }
                    if(isBold && isItalic){
                        fontChoosed = Font.loadFont("file:bold-italicfix.ttf", fontSize);
                    }
                    actualWord.setFont(fontChoosed);

                    if(j+1 < textItems.size()){
                        Text spaceWord = (Text) textItems.get(j+1);
                        spaceWord.setFont(fontChoosed);
                    }
                    i++;
                }
                j++;
            }
        }
        
        else System.out.println("Format expr not valid");
        
        return text;
    }
}
