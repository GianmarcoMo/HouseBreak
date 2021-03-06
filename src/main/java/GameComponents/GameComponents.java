package GameComponents;

import java.util.ArrayList;
import Game.Parser;
import Utente.User;
/**
 *
 * @author Moresi Gianmarco
 */
public abstract class GameComponents {

    private final User utente = new User();
    
    //Contiene tutte le stanze del gioco
    private final ArrayList<Room> rooms = new ArrayList<>();

    //Contiene tutti i comandi del gioco
    private final ArrayList<Command> commands = new ArrayList<>();

    //Contiene tutti gli oggetti del gioco
    private final ArrayList<GameObject> objects = new ArrayList<>();

    //Contiene le direzioni per muoversi
    private final ArrayList<Direzione> direzioni = new ArrayList<>();
    
    //Lista dei nemici nel gioco
    private final ArrayList<User> nemici = new ArrayList<>();
    
    //Setting della stanza corrente per il giocatore
    private Room stanzaCorrente=null;

    private int idSalvataggio =0;
    public User getUser(){
        return this.utente;
    }
    
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
    
    public ArrayList<User> getNemici(){
        return this.nemici;
    }

    public int getIdSalvataggio(){
        return this.idSalvataggio;
    }

    public void setIdSalvataggio(int idSalInput){
        this.idSalvataggio = idSalInput;
    }
    
    public abstract void inizializzazione(User giocatore, int idSalvataggioInput) throws Exception;
    
    public abstract void onUpdate(Parser parser);

}
