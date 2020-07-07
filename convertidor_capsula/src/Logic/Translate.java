/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import javafx.scene.text.TextFlow;

/**
 * Aplica la traslación a la frase.
 * @author leunam
 */
public interface Translate {
    /**
     * Aplica la traslación a la frase.
     * @param text Contenedor gráfico de las palabras de la frase.
     * @param x Componente x del punto de traslación.
     * @param y Componente y del punto de traslación.
     * @return Contenedor gráfico de las palabras con los cambios ya aplicados
     */
    default TextFlow translate(TextFlow text, double x, double y){
        //Se traslada respecto a su padre usando su esquina superior izquierda.
        text.setLayoutX(x);
        text.setLayoutY(y);
        return text;
    }
}
