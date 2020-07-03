/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import javafx.scene.text.TextFlow;

/**
 *
 * @author leunam
 */
public interface Translate {
    default TextFlow translate(TextFlow text, double x, double y){
        return null;
    }
}
