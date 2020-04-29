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
    private final ArrayList<Object> objects= new ArrayList<>();
    
    //Setting della stanza corrente per il giocatore
    private final Room currentRoom=null;

    //Inventario del giocatore
    private final Inventory inventory=new Inventory(utente);
    
    public Room getCurrentRoom(){
        return this.currentRoom;
    }
    
    public ArrayList<Command> getCommands(){
        return this.commands;
    }
    
    public ArrayList<Room> getRooms(){
        return this.rooms;
    }
    
    public ArrayList<Object> getObjects(){
        return this.objects;
    }

    public Inventory getInventory(){
        return this.inventory;
    }
    
    public abstract void inizializzazione() throws Exception;

    //public abstract void nextMove();
    
    
}
