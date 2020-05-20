package Game;

import GameComponents.*;
//import Utente.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

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

        //Comando per muoversi nel gioco
        Command vai = new Command(scan.nextLine());
        getCommand().add(vai);   
        //Comando per l'inventario
        Command inventario = new Command(scan.nextLine());
        getCommand().add(inventario);
        //Comando per osservare la stanza
        Command osserva = new Command(scan.nextLine());
        getCommand().add(osserva);
        //Comando per raccogliere oggetti/armi
        Command raccogli = new Command(scan.nextLine());
        getCommand().add(raccogli);
        //Comando per aprire contenitore
        Command apri = new Command(scan.nextLine());
        getCommand().add(apri);
        //Comando per premere un pulsante
        Command premi = new Command(scan.nextLine());
        getCommand().add(premi);
        //Comando per unire le munizioni all'arma.
        Command combina= new Command(scan.nextLine());
        getCommand().add(combina);
        //Comando per attaccare
        Command attacca= new Command(scan.nextLine());
        getCommand().add(attacca);
        //Comando per uscire
        Command esci= new Command(scan.nextLine());
        getCommand().add(esci);
        
        //nord sud est ovest
        //----------------------------------------------------------
        //nuovo scanner per acquisizione da file per le direzioni
        scan = new Scanner(new BufferedReader(new FileReader(file.getAbsolutePath() + "/direzioni.dat")));
        
        //Direzione nord
        Direzione nord= new Direzione(scan.nextLine());
        getDirezione().add(nord);
        //direzione sud
        Direzione sud= new Direzione(scan.nextLine());
        getDirezione().add(sud);
        //Direzione est
        Direzione est= new Direzione(scan.nextLine());
        getDirezione().add(est);
        //Direzione ovest
        Direzione ovest= new Direzione(scan.nextLine());
        getDirezione().add(ovest);
        
        //----------------------------------------------------------
        //nuovo scanner per acquisizione da file per le stanze
        scan = new Scanner(new BufferedReader(new FileReader(file.getAbsolutePath() + "/rooms.dat")));
        
        //Stanza prigioniero
        Room prigioniero = new Room(scan.nextLine());
        getRoom().add(prigioniero);
        prigioniero.setUltimoAmbiente(prigioniero.getAmbienteNord().toString());
        
        //Stanza corridoio
        Room corridoio = new Room(scan.nextLine());
        getRoom().add(corridoio);
        
        //Stanza magazzino
        Room magazzino = new Room(scan.nextLine());
        magazzino.aggiungiNemico(); //inserisco nemico nella stanza
        getRoom().add(magazzino);
        
        //Stanza Cucina
        Room cucina = new Room(scan.nextLine());
        getRoom().add(cucina);
        
        //stanza bagno
        Room bagno = new Room(scan.nextLine());
        bagno.aggiungiNemico(); //inserisco nemico nella stanza
        getRoom().add(bagno);
        
        //stanza salone
        Room salone = new Room(scan.nextLine());
        salone.aggiungiNemico(); //inserisco nemico nella stanza
        getRoom().add(salone);
        
        //stanza sicurezza
        Room sicurezza = new Room(scan.nextLine());
        sicurezza.bloccaStanza(); //blocca la stanza
        getRoom().add(sicurezza);
        
        //uscita
        Room uscita= new Room(scan.nextLine());
        uscita.bloccaStanza(); //blocca la porta d'uscita.
        getRoom().add(uscita);
        
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
        
        //Stanza iniziale.
        setStanzaCorrente(prigioniero);
        
        //TODO DEVI INSERIRE GLI OGGETTI
        
        //----------------------------------------------------------
        scan= new Scanner(new BufferedReader(new FileReader(file.getAbsoluteFile() + "/objects.dat")));

        //oggetto benda
        GameObject benda= new GameObject(scan.nextLine());
        getObject().add(benda);
        magazzino.getObject().add(benda);
        
        //oggetto soda
        GameObject soda= new GameObject(scan.nextLine());
        getObject().add(soda);
        cucina.getObject().add(soda);
        
        //oggetto iphone
        GameObject iphone= new GameObject(scan.nextLine());
        getObject().add(iphone);
        
        //oggetto rivista
        GameObject rivista= new GameObject(scan.nextLine());
        getObject().add(rivista);
        salone.getObject().add(rivista);
        
        //oggetto carta
        GameObject carta= new GameObject(scan.nextLine());
        getObject().add(carta);
        bagno.getObject().add(carta);
        
        //oggetto scarafaggio
        GameObject scarafaggio= new GameObject(scan.nextLine());
        getObject().add(scarafaggio);
        scarafaggio.setPushable();
        scarafaggio.deletePickable();
        salone.getObject().add(scarafaggio);
        
        
        
        //Munizioni per la pistola
        GameObject munizioni= new GameObject(scan.nextLine());
        getObject().add(munizioni);
        bagno.getObject().add(munizioni);
        
        //----------------------------------------------------------
        
        scan= new Scanner(new BufferedReader(new FileReader(file.getAbsoluteFile() + "/weapons.dat")));
        
        //Inizializzo la pistola
        Weapon glock= new Weapon(scan.nextLine());
        magazzino.getArmi().add(glock);
        getArmi().add(glock);
        
        //Inzializzo il coltello
        Weapon coltello= new Weapon(scan.nextLine());
        cucina.getArmi().add(coltello);
        getArmi().add(coltello);
        
        //Blocco il giocatore
        //getUser().bloccaGiocatore();
        
        scan.close();

    }

    @Override
    public void onUpdate(final Parser parser) {
        //Controlla se il comando è per muoversi
        if(parser.getComando().containsCommand("vai")){
            if (parser.getDirezione() != null) {
                this.movimentoPlayer(parser.getDirezione(), parser.getDirezione().getDirezione());
            }else{
                System.out.println("Nessuna stanza inserita.");
            }
        }else if( parser.getComando().containsCommand("osserva")){
            this.guardaStanza();
        }
    }
    
    private void movimentoPlayer(Direzione direzioneParser, String direzioneInput) {
        //La stanza è appena illuminata da una candela, e tu sei ammanettato al termosifone, trova il modo di liberarti! 
        //Hai un coltellino svizzero nella tasca, e davanti a te c'è una porta.
        if (getCurrentRoom().getRoom(getBussola()
                .getPosizioneUtente(direzioneInput)) != null) {

            //Se esiste la stanza esiste, controlla se il giocatore è bloccato
            if (!getUser().bloccato()) {
                //Se la stanza esiste, controlla se è bloccata
                if (!getCurrentRoom().getRoom(getBussola()
                        .getPosizioneUtente(direzioneInput)).bloccata()) {

                    setStanzaCorrente(getCurrentRoom().getRoom(getBussola()
                            .getPosizioneUtente(direzioneInput)));
                    System.out.println(getCurrentRoom().getDescrizioneStanza());

                    getCurrentRoom().setUltimoAmbiente(getCurrentRoom().getAmbienteRoom(getBussola()
                            .getPosizioneUtente(direzioneInput)).toString());
                    
                    getBussola().spostamentoInput(getBussola()
                            .getPosizioneUtente(direzioneInput));
                }
            }
        } else {
            System.out.println("Non esiste nessuna stanza nella direzione in cui ti vuoi muovere!");
        }
    }
    
    private void guardaStanza(){
        System.out.println(this.getCurrentRoom().getUltimoAmbiente());
    }

}

