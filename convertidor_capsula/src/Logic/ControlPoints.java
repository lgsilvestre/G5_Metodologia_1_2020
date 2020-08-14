/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import javafx.collections.ObservableList;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author leunam
 */
public interface ControlPoints {
    default TextFlow showControlPoints(TextFlow text){
        
        ObservableList textChildList = text.getChildren();
        
        for (int i = 0; i < textChildList.size(); i++) {
            Text textChild = (Text) textChildList.get(i);
            if (!textChild.getText().equalsIgnoreCase(" ")){
                if (textChild.getText().charAt(textChild.getText().length()-1) != '*')
                    textChild.setText(textChild.getText()+"*");
                else break;
            }
        }
        
        return text;
    }
}
