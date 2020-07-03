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
public interface ControlPoints {
    default TextFlow showControlPoints(TextFlow text){
        return null;
    }
}
