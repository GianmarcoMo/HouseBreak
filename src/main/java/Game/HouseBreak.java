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
        Scanner scan;
        
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
        //Comando per premere un pulsante
        Command premi = new Command(scan.nextLine());
        getCommand().add(premi);
        //Comando per unire le munizioni all'arma.
        Command combina = new Command(scan.nextLine());
        getCommand().add(combina);
        //Comando per attaccare
        Command attacca = new Command(scan.nextLine());
        getCommand().add(attacca);
        //Comando per uscire
        Command esci = new Command(scan.nextLine());
        getCommand().add(esci);
        //Comando per lasciare un oggetto
        Command lascia = new Command(scan.nextLine());
        getCommand().add(lascia);
        //Comando per visualizzare i comandi del gioco
        Command help = new Command(scan.nextLine());
        getCommand().add(help);
        //Comando per utilizzare un oggetto
        Command usa = new Command(scan.nextLine());
        getCommand().add(usa);

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
        //--- OGGETTI CURATORI
        scan= new Scanner(new BufferedReader(new FileReader(file.getAbsoluteFile() + "/curatori.dat")));
        
        //Oggetto benda
        Curatore benda = new Curatore(scan.nextLine());
        benda.setPuntiVita(45);
        getObject().add(benda);
        magazzino.getObject().add(benda);
        
        //oggetto soda
        Curatore soda = new Curatore(scan.nextLine());
        soda.setPuntiVita(25);
        getObject().add(soda);
        cucina.getObject().add(soda);
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
        getUser().diminuisciVita(0);
        scan.close();

    }

    @Override
    public void onUpdate(final Parser parser) {
        //COMANDO MOVIMENTO
        if(parser.getComando().containsCommand("vai")){
            if (parser.getDirezione() != null) {
                this.movimentoPlayer(parser.getDirezione().getDirezione());
            }else{
                System.out.println("Nessuna direzione inserita.");
            }
            //COMANDO PER GUARDARE LA STANZA
        }else if(parser.getComando().containsCommand("osserva")){
            this.guardaStanza();
            this.getCurrentRoom().getDescrizioneOggetti();
            //COMANDO PER RACCOGLIERE OGGETTI
        }else if(parser.getComando().containsCommand("raccogli")){
            //OGGETTO
            if(parser.getObject() != null){
                raccogliOggetto(parser.getObject());
                //ARMA
            }else if(parser.getArma() != null){
                raccogliArma(parser.getArma());
            }else{
                System.out.println("Che oggetto vuoi prendere?");
            }
            //COMANDO PER LASCIARE UN OGGETTO/ARMA
        }else if(parser.getComando().containsCommand("lascia")){
            //OGGETTO
            if (parser.getObject() != null) {
                lasciaOggetto(parser.getObject());
                //ARMA
            } else if (parser.getArma() != null){
                lasciaArma(parser.getArma());
            }else{
                System.out.println("Che oggetto vuoi lasciare?");
            }
            //COMANDO PER GUARDARE GLI OGGETTI/ARMI NELL'INVENTARIO
        }else if(parser.getComando().containsCommand("inventario")){
            getUser().getInvetario().guardaInventario();
            //COMANDO PER VISUALIZZARE I COMANDI DISPONIBILI
        }else if(parser.getComando().containsCommand("help")){
            mostraComandi();
            //COMANDO PER UTILIZZARE DEGLI OGGETTI
        }else if(parser.getComando().containsCommand("usa")){
            if(parser.getObject()!= null){
                if(getUser().getInvetario().containsObject(parser.getObject())){
                    usaCuratore((Curatore) parser.getObject());
                }else{
                    System.out.println("Non hai "+parser.getObject().getNome()+" nell'inventario.");
                }
            }else{
                System.out.println("Penso che non esiste l'oggetto che vuoi usare.");
            }
        }else if(parser.getComando().containsCommand("premi")){
            //TODO
        }else if(parser.getComando().containsCommand("combina")){
            //TODO
        }else if(parser.getComando().containsCommand("spara")){
            //TODO
        }
    }
    
    /**
     * Funzione che gestisce il movimento del player da una stanza all'altra
     * controlla se esiste una stanza nella direzione interessata,
     * controlla se e' bloccata, e agisce di conseguenza.
     * @param direzioneInput - direzione inserita in input
     */
    private void movimentoPlayer(String direzioneInput) {
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
    
    /**
     * Fornisce in output l'ambientazione della stanza.
     */
    private void guardaStanza(){
        System.out.println(this.getCurrentRoom().getUltimoAmbiente());
    }

    /**
     * Gestisce la raccolta dell'oggetto indicato
     * dall'utente in input, viene inserito nell'inventario
     * @param oggettoInput - oggetto riconosciuto dal parser
     */
    private void raccogliOggetto(GameObject oggettoInput) {
        if (this.getCurrentRoom().containsObject(oggettoInput)) {
            //Se esiste l'oggetto, controlla se il player
            //ha almeno uno slot libero
            if (this.getUser().getInvetario().getSizeInvetory() > 0) {
                getUser().getInvetario().addObject(oggettoInput);
                this.getCurrentRoom().deleteObject(oggettoInput);
            }
        }else{
            System.out.println("Non esiste nessun oggetto con quel nome!");
        }
    }
    
    /**
     * Gestisce la raccolta dell'arma indicata
     * dall'utente in input, viene inserita nell'inventario.
     * @param armaInput - amra riconosciuta dal parser
     */
    private void raccogliArma(Weapon armaInput){
        if (this.getCurrentRoom().containsWeapon(armaInput)){
            //Se esiste l'arma, controlla se il player
            //ha almeno uno slot libero
            if(this.getUser().getInvetario().getSizeInvetory() > 0){
                getUser().getInvetario().addArma(armaInput);
                this.getCurrentRoom().deleteArma(armaInput);
            }
        }else{
            System.out.println("Non esiste nessun'arma con quel nome!");
        }
    }

    /**
     * Elimina dall'inventario dell'utente l'oggetto indicato
     *
     * @param oggettoInput - oggetto indicato in input dall'utente
     */
    private void lasciaOggetto(GameObject oggettoInput) {
        //Controlla se l'oggetto esiste nell'inventario
        if (getUser().getInvetario().containsObject(oggettoInput)) {
            //Eliminazione oggetto dall'inventario
            getUser().getInvetario().dropObject(oggettoInput);
            //Inserisco l'oggetto alla stanza.
            getCurrentRoom().getObject().add(oggettoInput);
        } else {
            System.out.println("Non hai questo oggetto.");
        }
    }
    
    /**
     * Elimina l'arma dall'inventario
     * @param armaInput - arma riconosciuta da parser
     */
    private void lasciaArma(Weapon armaInput) {
        //Controlla se esiste l'arma nell'inventario
        if (getUser().getInvetario().containsArma(armaInput)) {
            getUser().getInvetario().dropArma(armaInput);
            getCurrentRoom().getArmi().add(armaInput);
        } else {
            System.out.println("Non hai un'arma con questo nome.");
        }
    }

    /**
     * Mostra i comandi disponibili
     */
    private void mostraComandi(){
        System.out.println("+------------------------------------ Comandi House Break --------------------------------------+");
        getCommand().forEach((comando) ->{
            System.out.println(" | "+comando.getNomeComando()+" \t"+comando.getDescrizioneComando());
        });
        System.out.println("+-------------------------------------------------------------------------------------------------------------+");
    }
    
    private void usaCuratore(Curatore oggettoCuratore){
        if(getUser().getVita() < 100){
            getUser().aumentaVita(oggettoCuratore.getPuntiVita());
            System.out.println("Vita attuale: "+ getUser().getVita());
        }else{
            System.out.println("La tua vita è al 100%");
        }
    }
    
}

