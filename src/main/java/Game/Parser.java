package Game;

import GameComponents.Command;
import GameComponents.GameObject;
import GameComponents.Weapon;

import java.util.ArrayList;

/**
 *
 * @author Moresi Gianmarco
 */
public class Parser {
    //prende il comando inserito dall'utente, se valido
    private Command comandoInput=null;
    private GameObject oggetto=null;
    private Weapon arma=null;
    
    /*
    Costruttore che prende in input i vari componenti del gioco e effettua i controlli
    */
    public Parser(String comandoUtente, ArrayList<Command> comandiGioco, ArrayList<GameObject> oggettiGioco,ArrayList<Weapon> armi){
        //Controllo che la stringa non è vuota.
        //Se la stringa inserita dall'utente è vuota
        //Non esegue nessuna istruzione e restituisce tutti gli attributi a null
        if (comandoUtente.equals(" ")) {
            
            //rendo tutti i caratteri dell'input minuscoli
            comandoUtente = comandoUtente.toLowerCase();
            //Divido la stringa in input, cosi da controllare input per input
            String[] tokens = comandoUtente.split("\\s+");
            
            //Controlla che il primo token sia un comando
            int indexComando= controllaComando(tokens[0],comandiGioco);
            //Se il comando esiste, restituisce il suo index
            //Se è -1, il comando non esiste
            if( indexComando != -1){
                //Se esiste il comando, lo inserisce in comandoInput
                //estraendolo dall'array 
                comandoInput=comandiGioco.get(indexComando);
                
                if(tokens.length>1){
                    int indexOggetto=controllaOggetto(tokens[1], oggettiGioco);
                    //Controlla se l'index è diverso da -1
                    //quindi che l'oggetto esiste
                    if(indexOggetto != -1){
                        //estrae dall'array l'oggetto che si trova in quella posizione
                        //lo inserire nell'attributo della classe
                        oggetto= oggettiGioco.get(indexOggetto);
                    }else{
                        //Controlla se il secondo comando è un'arma
                        int indexArma=controllaArma(tokens[1], armi);
                        //Controlla se l'index è diverso da -1
                        //quindi che l'arma esiste
                        if(indexArma != -1){
                            //estrae dall'array l'arma che si trova in quella posizione
                            //lo inserire nell'attributo della classe
                            arma=armi.get(indexArma);
                        }
                    }
                }
            }
        }
    }
    
    //Controlla se esiste il comando inserito dall'utente
    //restituisce l'index del comando nell'Array, se esiste
    private int controllaComando(String comandoInput, ArrayList<Command> comandi){
        for(int i=0; i<comandi.size(); i++){
            if(comandi.get(i).containsCommand(comandoInput)){
                return i;
            }
        }
        return -1;
    }
    
    //Controllas se esiste l'oggetto cercato
    //restituisce il suo index, se esiste
    private int controllaOggetto(String oggettoInput, ArrayList<GameObject> oggetti){
        for(int i=0; i<oggetti.size(); i++){
            if(oggetti.get(i).containsObject(oggettoInput)){
                return i;
            }
        }
        return -1;
    }
    
    //Controllas se esiste l'oggetto cercato
    //restituisce il suo index, se esiste
    private int controllaArma(String armaInput, ArrayList<Weapon> armi){
        for(int i=0; i<armi.size(); i++){
            if(armi.get(i).equals(armaInput)){
                return i;
            }
        }
        return -1;
    }
    
}
