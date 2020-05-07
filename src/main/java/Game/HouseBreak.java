package Game;

import GameComponents.*;
import Utente.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;


/**

    nord n,N,Nord,NORD,avanti,Avanti,AVANTI,
    sud s,S,Sud,SUD,indietro,Indietro,INDIETRO,
    est e,E,Est,EST,destra,Destra,DESTRA,
    ovest o,O,Ovest,OVEST,sinistra,Sinistra,SINISTRA,
    inventario inv,i,I,zaino,ZAINO,Zaino,bag,Bag,b,
    fine esci,end,ESCI,Esci,END,End,muori,ammazzati,ucciditi,
    osserva guarda,scruta,analizza,
    raccogli pick,prendi,inserisci,
    apri sfonda,              
    premi spingi,push,pugno,


 */

public class HouseBreak extends GameComponents{

    @Override
    public void inizializzazione() throws IOException {
        Scanner scan = null;
        /*
         * specifio la cartella dove sono situati i vari files per i comandi, oggetti
         * ecc..
         * 
         * Indico solo la cartella cosi per ogni inizializzazione la cartella è la
         * stessa, e bisogna solo aggiungere il nome del file.
         */
        final String FOLDER = "resources";
        File file = new File(FOLDER);

        //Nuovo scanner per acquisizione da file per i comandi
        scan = new Scanner(new BufferedReader(new FileReader(file.getAbsolutePath() + "/comandi.dat")));

        //Comando per andare avanti
        Command nord = new Command(scan.nextLine());
        //Comando per andare indietro
        Command sud = new Command(scan.nextLine());
        //Comando per andare a destra
        Command est = new Command(scan.nextLine());
        //Comando per andare a sinistra
        Command ovest = new Command(scan.nextLine());
        //Comando per l'inventario
        Command inventario = new Command(scan.nextLine());
        //Comando per terminare gioco
        Command fine = new Command(scan.nextLine());
        //Comando per osservare la stanza
        Command osserva = new Command(scan.nextLine());
        //Comando per raccogliere oggetti/armi
        Command raccogli = new Command(scan.nextLine());
        //Comando per aprire contenitore
        Command apri = new Command(scan.nextLine());
        //Comando per premere un pulsante
        Command premi = new Command(scan.nextLine());

        //nuovo scanner per acquisizione da file per le stanze
        scan = new Scanner(new BufferedReader(new FileReader(file.getAbsolutePath() + "/rooms.dat")));
        //si posizione sulla linea del commento
        //al prossimo scan.nextline si trova sulla prima stanza
        scan.nextLine();
        //Stanza prigioniero
        Room prigioniero = new Room(scan.nextLine());
        //Stanza corridoio
        Room corridoio = new Room(scan.nextLine());
        //Stanza magazzino
        Room magazzino = new Room(scan.nextLine());
        magazzino.aggiungiNemico(); //inserisco nemico nella stanza
        //Stanza Cucina
        Room cucina = new Room(scan.nextLine());
        //stanza bagno
        Room bagno = new Room(scan.nextLine());
        bagno.aggiungiNemico(); //inserisco nemico nella stanza
        //stanza salone
        Room salone = new Room(scan.nextLine());
        salone.aggiungiNemico(); //inserisco nemico nella stanza
        //stanza sicurezza
        Room sicurezza = new Room(scan.nextLine());
        sicurezza.bloccaStanza(); //blocca la stanza
        //uscita
        Room uscita= new Room(scan.nextLine());
        uscita.bloccaStanza(); //blocca la porta d'uscita.
        
        //Confini stanza prigioniero
        prigioniero.setConfini("nord", corridoio);
        
        //confini stanza corridoio
        corridoio.setConfini("sud", prigioniero);
        corridoio.setConfini("est", magazzino);
        corridoio.setConfini("ovest", cucina);
        
        //confini stanza magazzino
        magazzino.setConfini("ovest", corridoio);
        
        //confini stanza cucina
        cucina.setConfini("nord", bagno);
        cucina.setConfini("est", corridoio);
        
        //Confini stanza bagno
        bagno.setConfini("est", salone);
        bagno.setConfini("sud", cucina);
        
        //Confini stanza salone
        salone.setConfini("est", uscita);
        salone.setConfini("sud", sicurezza);
        salone.setConfini("ovest", bagno);
        
        //confini stanza sicurezza
        sicurezza.setConfini("nord", salone);

        scan.close();

    }
    
}

