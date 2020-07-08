/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Invierte una frase enviando al principio la última palabra.
 * @author leunam
 */
public interface Invert {
    /**
     * Invierte una frase enviando al principio la última palabra.
     * @param text Contenedor gráfico de las palabras de la frase.
     * @return Contenedor gráfico de las palabras con los cambios ya aplicados. 
     */
    default TextFlow invert(TextFlow text){
        ObservableList textChildList = text.getChildren();
        ArrayList<Object> aux = new ArrayList();
        
        int listSize = textChildList.size();
        for (int i = listSize - 1; i >= 0; i--) {
            Object obj = textChildList.remove(i);
            aux.add(0, obj);
        }
        
        textChildList.clear();
        for (int i = 0; i < listSize; i++) {
            Object obj = aux.remove(0);
           textChildList.add(0, obj);
        }     
         
        return text;
    }
}
