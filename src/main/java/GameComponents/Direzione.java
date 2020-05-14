package GameComponents;

import java.util.ArrayList;
/**
 *
 * @author Moresi Gianmarco
 */
public class Direzione implements Input{
    //Nome della direzione
    private StringBuilder direzione;
    //Alias per la direzione
    private ArrayList<String> alias;
    
    public Direzione(String lineaInput){
        this.direzione=new StringBuilder();
        this.alias= new ArrayList<>();
        
        acquisizoneInputFile(lineaInput);
    }
    
    public Direzione(){
        this.direzione=null;
    }
    
    public void setDirezione(String direzioneInput){
        this.direzione.append(direzioneInput);
    }
    
    public String getDirezione(){
        return this.direzione.toString();
    }
    
    public boolean equals(String direzioneInput){
        if(this.direzione.toString().equals(direzioneInput)){
            return true;
        }else{
            for(int i=0; i<this.alias.size(); i++){
                if(this.alias.get(i).equals(direzioneInput)){
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public void acquisizoneInputFile(String lineaInput) {
        int index = 0;

        //Acquisizione nome direzione.
        while (lineaInput.charAt(index) != '.') {
            direzione.append(lineaInput.charAt(index));
            index++;
        }
        index++;

        //Acquisizione dei vari alias
        StringBuilder singoloAlias = new StringBuilder();

        while (lineaInput.charAt(index) != '.') {
            //Se incontra la virgola, che divide i vari alias
            //aggiungi l'alias trovato all'ArrayList e resetta lo StringBuilder
            if (lineaInput.charAt(index) == ',') {
                alias.add(alias.size(), singoloAlias.toString());
                singoloAlias.delete(0, singoloAlias.length());
            } else {    
                //Se non incontra la virgola, continua a inserire i caratteri
                singoloAlias.append(lineaInput.charAt(index));
            }
            index++;
        }
    }
}
