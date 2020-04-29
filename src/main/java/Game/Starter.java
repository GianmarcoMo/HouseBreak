package Game;

import GameComponents.*;
import Utente.User;
import java.util.Scanner;

public class Starter {
    //private final GameComponents gioco;

    /*public Starter(GameComponents gioco){
        this.gioco= gioco;
        try{
            //inizializzazione delle componenti del gioco
            this.gioco.inizializzazione();
        }catch(Exception error){
            System.err.println(error);
        }
    }*/

    private void Story(){
        System.out.println("\n\n\t|||||||||||||||||||||||||||||||||");
        System.out.println("\t");
        System.out.println("\t|||| BEVENUTO IN HOUSE BREAK ||||");
        System.out.println("\t");
        System.out.println("\t|||||||||||||||||||||||||||||||||\n\n");
        System.out.println("\tTi chiami Will, un ex-agente dei servizi segreti Americani, che sa cose che non dovrebbe sapere. ");
        System.out.println("\tSono passati diversi anni dal tuo congedo e l'attuale presidente degli Stati Uniti non accetta ");
        System.out.println("\tche tu sia in giro, ma non vuole ammazzarti.");
        System.out.println("\tTi trovi al supermercato quando una guardia del corpo del presidente ti colpisce alla testa");
        System.out.println("\te svieni. \n\tTi risvegli in una stanza, vuota, solo con un termosifone, dove ti hanno legato, ");
        System.out.println("\tcerca di liberarti, e scappare da questo incubo, mi raccomando, occhi aperti!");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t Buona fortuna!\n\n");
    }

    /* Permette di avviare una partita
        verrà indicata la stanza corrente, ovvero quella iniziale
        verranno inseriti in input i comandi dall'utente
        
        Quando verrà avviato questo metodo, tutti gli objects, 
        rooms e comandi saranno stati inizializzati nel gioco.
    */
    public void run(){
        Story();
        
    }

    public static void main(String[] args){
        Starter game= new Starter();
        game.Story();
    }
}