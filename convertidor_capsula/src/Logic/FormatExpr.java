package Logic;
/**
 * Procesa la expresión de formatos. Si alguna clave o tamaño de letra no corresponde
 * a las claves admitidas (n, s, k, 11, 12, 13 y 14 respectivamente), la expresión 
 * se marca como inválida.
 * @author leunam
 */
public class FormatExpr {
    private String expression;
    private String[] parts;
    private String[][] charactList;
    private boolean isValid = true;
    
    public FormatExpr(String expression){
        this.expression = expression;
        this.parts = expression.split(","); //Expresiones para cada palabra
        this.charactList = new String[parts.length][];
        saveCharacts();
    }
    /**
     * Obtiene cada una de las claves de formato. Si una clave no es válida, marca
     * como inválida toda la expresión de formatos.
     */
    private void saveCharacts(){
        String[] characts;
        int i = 0;
        for (String part : parts) {
            characts = part.split("\\+");
            charactList[i] = characts;
            for (int j = 0; j < characts.length; j++) {
                String charact = characts[j];
                if(!(charact.equals("n") || charact.equals("s") || charact.equals("k") || 
                charact.equals("11") || charact.equals("12") || charact.equals("13") || charact.equals("14")
                        || charact.equals(""))){
                    isValid = false;
                    break;
                }
                if(!isValid) break;
            }
            i++;
        }
    }
    
    /**
     * Devuelve las claves de formato para cada palabra.
     * @return Matriz de cadenas de texto con las claves de formato para cada palabra.
     */
    public String[][] getCharactList(){
        return charactList;
    }
    
    /**
     * Avisa si la expresión ingresada es válida.
     * @return true si es válida, false si es inválida.
     */
    public boolean isValid(){
        return isValid;
        
    }
    
}
