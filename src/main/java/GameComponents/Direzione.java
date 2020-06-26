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
        if(this.direzione.toString().contains(direzioneInput)){
            return true;
        }else{
            for(int i=0; i<this.alias.size(); i++){
                if(this.alias.get(i).contains(direzioneInput)){
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public void acquisizoneInputFile(String lineaInput) {
        String[] tokens= lineaInput.split("\\s+");
        int index = 0;

        //Acquisizione nome direzione.
        while (!tokens[index].equals(".")) {
            this.direzione.append(tokens[index]).append(" ");
            index++;
        }
        index++;

        //Acquisizione dei vari alias

        while (!tokens[index].equals(".")) {
            this.alias.add(tokens[index]);
            index++;
        }
    }
}
