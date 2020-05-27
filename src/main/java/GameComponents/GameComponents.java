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

    //Contiene tutti gli oggetti curatori
    private final ArrayList<Curatore> curatori = new ArrayList<>();

    //Contiene le direzioni per muoversi
    private final ArrayList<Direzione> direzioni = new ArrayList<>();

    private final Bussola bussolaUtente = new Bussola();
    
    //Setting della stanza corrente per il giocatore
    private Room stanzaCorrente=null;

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
    
    public Bussola getBussola(){
        return bussolaUtente;
    }
    
    public void setStanzaCorrente(Room stanzaInput){
        this.stanzaCorrente=stanzaInput;
    }
    
    public ArrayList<GameObject> getObject(){
        return this.objects;
    }
    
    public ArrayList<Curatore> getCuratori(){
        return this.curatori;
    }
    
    public abstract void inizializzazione() throws Exception;
    
    public abstract void onUpdate(Parser parser);
    
    
}
