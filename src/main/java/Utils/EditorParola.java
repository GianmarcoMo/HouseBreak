/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author Moresi Gianmarco
 */
public class EditorParola {

    public String rimozzioneTrailingSpace(String parolaInput) {
        if (parolaInput.charAt(parolaInput.length()-1) != ' ') {
            String parolaModificata =(String) parolaInput.subSequence(0, parolaInput.length() - 1);
            //Ritorna un insieme di charSequence, dal carattere principale fino al penultimo
            //eliminando l'ultimo, ovvero il trailing space
            return parolaModificata; 
        }
        //Se non esiste il trailing space, ritorna la parola originale.
        return parolaInput;
    }
    
    public StringBuilder rimozzioneTrailingSpace(StringBuilder parolaInput) {
        if (parolaInput.charAt(parolaInput.length()-1) == ' ') {
            StringBuilder parolaModificata= new StringBuilder(parolaInput.subSequence(0, parolaInput.length()-1));
            //Ritorna un insieme di charSequence, dal carattere principale fino al penultimo
            //eliminando l'ultimo, ovvero il trailing space
            return  parolaModificata;
        }
        //Se non esiste il trailing space, ritorna la parola originale.
        return parolaInput;
    }

}
