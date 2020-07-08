/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import javafx.scene.text.TextFlow;

/**
 * Aplica la rotación a la frase.
 * @author leunam
 */
public interface Rotate {
    /**
     * Aplica la rotación a la frase.
     * @param text Contenedor gráfico de las palabras de la frase.
     * @param degrees Grados de rotación.
     * @return Contenedor gráfico de las palabras con los cambios ya aplicados. 
     */
    default TextFlow rotate(TextFlow text, double degrees){
        //-degrees para que rote en sentido contrario a las agujas del reloj.
        text.setRotate(-degrees); 
        return text;
    }
}