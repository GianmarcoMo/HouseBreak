package Game;

import GameComponents.Command;
import GameComponents.GameObject;
import GameComponents.Weapon;
import GameComponents.Direzione;

import java.util.ArrayList;

/**
 *
 * @author Moresi Gianmarco
 */
public class Parser {

    //prende il comando inserito dall'utente, se valido
    private Command comandoInput = null;
    private GameObject oggetto = null;
    private Weapon arma = null;
    private Direzione direzione = null;

    /*
    Costruttore che prende in input i vari componenti del gioco e effettua i controlli
     */
    public Parser(String comandoUtente, final ArrayList<Command> comandiGioco,
            final ArrayList<GameObject> oggettiGioco, final ArrayList<Weapon> armi,
            final ArrayList<Direzione> direzioni) {
        //Controllo che la stringa non è vuota.
        //Se la stringa inserita dall'utente è vuota
        //Non esegue nessuna istruzione e restituisce tutti gli attributi a null
        if (!comandoUtente.equals(" ")) {
            //rendo tutti i caratteri dell'input minuscoli
            comandoUtente = comandoUtente.toLowerCase();
            //Divido la stringa in input, cosi da controllare input per input
            String[] tokens = comandoUtente.split("\\s+");

            //Controlla che il primo token sia un comando
            int indexComando = controllaComando(tokens[0], comandiGioco);
            //Se il comando esiste, restituisce il suo index
            //Se è -1, il comando non esiste
            if (indexComando != -1) {
                //Se esiste il comando, lo inserisce in comandoInput
                //estraendolo dall'array 
                comandoInput = comandiGioco.get(indexComando);
                
                //Verifica se esiste un altro token es. oggetto/arma/direzione movimento.
                if (tokens.length > 1) {
                    //Controlla se il secondo token è un oggetto.
                    int indexSecondoToken = controllaOggetto(tokens[1], oggettiGioco);
                    //Controlla se l'index è diverso da -1
                    //quindi che l'oggetto esiste
                    if (indexSecondoToken != -1) {
                        /*
                        se l'oggetto esiste, viene estratto dall'arrayList 
                        e viene inserito nell'attributo oggetto.
                        */
                        oggetto = oggettiGioco.get(indexSecondoToken);
                    } else {
                        //Controlla se il secondo token è un'arma
                        indexSecondoToken = controllaArma(tokens[1], armi);
                        //Controlla se l'index è diverso da -1
                        //quindi che l'arma esiste
                        if (indexSecondoToken != -1) {
                            /*
                                se l'arma esiste, viene estratta dall'arrayList 
                                e viene inserito nell'attributo arma.
                            */
                            arma = armi.get(indexSecondoToken);
                        }else{
                            //Controlla se il secondo token è una direzione
                            indexSecondoToken = controllaDirezione(tokens[1], direzioni);
                            
                            //se il secondo token è una direzione
                            if(indexSecondoToken != -1){
                                //estra l'oggetto direzione e viene assegnato all'attributo.
                                this.direzione=direzioni.get(indexSecondoToken);
                            }
                        }
                    }
                }
            }
        }
    }

    Parser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Controlla se esiste il comando inserito dall'utente
    //restituisce l'index del comando nell'Array, se esiste
    private int controllaComando(String comandoInput, ArrayList<Command> comandi) {
        for (int i = 0; i < comandi.size(); i++) {
            if (comandi.get(i).containsCommand(comandoInput)) {
                return i;
            }
        }
        return -1;
    }

    //Controllas se esiste l'oggetto cercato
    //restituisce il suo index, se esiste
    private int controllaOggetto(String oggettoInput, ArrayList<GameObject> oggetti) {
        for (int i = 0; i < oggetti.size(); i++) {
            if (oggetti.get(i).containsObject(oggettoInput)) {
                return i;
            }
        }
        return -1;
    }

    //Controllas se esiste l'oggetto cercato
    //restituisce il suo index, se esiste
    private int controllaArma(String armaInput, ArrayList<Weapon> armi) {
        for (int i = 0; i < armi.size(); i++) {
            if (armi.get(i).containsArma(armaInput)) {
                return i;
            }
        }
        return -1;
    }
    
    //Controlla se il secondo token è una direzione.
    private int controllaDirezione(String direzioneInput, ArrayList<Direzione> direzioni){
        for(int i=0; i<direzioni.size(); i++){
            if(direzioni.get(i).equals(direzioneInput)){
                return i;
            }
        }
        return -1;
    }
    
    public Command getComando(){
        return this.comandoInput;
    }
    
    public Direzione getDirezione(){
        return this.direzione;
    }
    
    public GameObject getObject(){
        return this.oggetto;
    }
    
    public Weapon getArma(){
        return this.arma;
    }

}
