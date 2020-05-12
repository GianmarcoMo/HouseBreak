package GameComponents;

import java.util.ArrayList;
import Utente.User;
/**
 *
 * @author Moresi Gianmarco
 */
public abstract class GameComponents {
    private final User utente= new User();

    //Contiene tutte le stanze del gioco
    private final ArrayList<Room> rooms= new ArrayList<>();
    
    //Contiene tutti i comandi del gioco
    private final ArrayList<Command> commands= new ArrayList<>();
    
    //Contiene tutti gli oggetti del gioco
    private final ArrayList<GameObject> objects= new ArrayList<>();
    
    //Contiene le direzioni per muoversi
    private final ArrayList<Direzione> direzioni= new ArrayList<>();
    
    //Setting della stanza corrente per il giocatore
    private Room stanzaCorrente=null;
    
    public Room getCurrentRoom(){
        return this.stanzaCorrente;
    }
    
    public ArrayList<Command> getCommand(){
        return this.commands;
    }
    
    public ArrayList<Direzione> getDirezione(){
        return this.direzioni;
    }
    
    public ArrayList<Room> getRoom(){
        return this.rooms;
    }
    
    public void setStanzaCorrente(Room stanzaInput){
        this.stanzaCorrente=stanzaInput;
    }
    
    public ArrayList<GameObject> getObject(){
        return this.objects;
    }
    
    public abstract void inizializzazione() throws Exception;
    
    //public abstract void nextMove();
    
    
}
