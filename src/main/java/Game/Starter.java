package Game;

import GameComponents.*;
import Utente.User;
//import Utente.User;
import java.util.Scanner;

public class Starter {
    private GameComponents gioco;
    private int idSalvataggio ;
    public Starter(GameComponents giocoInput, User giocatoreAttuale, int idSalvataggioInput){
        this.gioco= giocoInput;
        idSalvataggio = idSalvataggioInput;
        
        try{
            //inizializzazione delle componenti del gioco
            this.gioco.inizializzazione(giocatoreAttuale, idSalvataggio);
        }catch(Exception error){
            System.err.println(error);
        }
    }

    /**
     * Mostra a monitor l'introduzione al gioco
     */
    private void Story(){
        System.out.println("\n\nBEVENUTO IN HOUSE BREAK\n");
        System.out.println("Ti chiami Will, un ex-agente dei servizi segreti Americani, che sa cose che non dovrebbe sapere. ");
        System.out.println("Sono passati diversi anni dal tuo congedo e l'attuale presidente degli Stati Uniti non accetta ");
        System.out.println("che tu sia in giro, ma non vuole ammazzarti.");
        System.out.println("Ti trovi al supermercato quando una guardia del corpo del presidente ti colpisce alla testa");
        System.out.println("e svieni. \nTi risvegli in una stanza, vuota, solo con un termosifone, dove ti hanno legato, ");
        System.out.println("cerca di liberarti, e scappare da questo incubo, mi raccomando, occhi aperti!");
        System.out.println("\nBuona fortuna!\n\n");
        System.out.println("\n< ----------------- > \n");
    }

    /**
     * Permette di avviare una partita
        verrà indicata la stanza corrente, ovvero quella iniziale
        verranno inseriti in input i comandi dall'utente
        
        Quando verrà avviato questo metodo, tutti gli objects, 
        rooms e comandi saranno stati inizializzati nel gioco.
     */
    public void run(){
        if(idSalvataggio==0)
            Story(); 
        
        //Scanner per input dell'utente.
        Scanner scan= new Scanner(System.in);
        
        Parser parser;
        while(true){
            System.out.println("\nCosa vuoi fare?");
            System.out.print("->");
            parser= new Parser(scan.nextLine(), gioco.getCommand(),
                    gioco.getObject(), gioco.getDirezione());

            if(parser.getComando() != null){
                if(parser.getComando().containsCommand("esci")){
                    System.out.println("Che brutta fine... Ciao piccolo angelo :(\n\n");
                    scan.close();
                    System.exit(0);
                }else{
                    gioco.onUpdate(parser);
                }
            }else {
                System.out.println("Non capisco cosa vuoi fare!\n"
                    + "Se vuoi consulta 'help' per i comandi. ");
            }
        }
    }
}