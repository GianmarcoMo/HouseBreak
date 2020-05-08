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
    
    //Setting della stanza corrente per il giocatore
    private final Room currentRoom=null;
    
    public Room getCurrentRoom(){
        return this.currentRoom;
    }
    
    public ArrayList<Command> getCommand(){
        return this.commands;
    }
    
    public ArrayList<Room> getRoom(){
        return this.rooms;
    }
    
    public ArrayList<GameObject> getObject(){
        return this.objects;
    }
    
    public abstract void inizializzazione() throws Exception;

    //public abstract void nextMove();
    
    
}
