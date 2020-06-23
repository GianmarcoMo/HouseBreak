package Game;

import GameComponents.*;
import Utente.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import Database.DatabaseInteract;

import java.io.File;
import java.sql.SQLException;

public class HouseBreak extends GameComponents {  
    @Override
    public void inizializzazione(User giocatoreAttuale, int idSalvataggioInput) throws IOException, SQLException {     
        setIdSalvataggio(idSalvataggioInput);  
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
        Command ricarica = new Command(scan.nextLine());
        getCommand().add(ricarica);
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
        //Comando per equipaggiare un'arma dall'inventario
        Command equipaggia = new Command(scan.nextLine());
        getCommand().add(equipaggia);
        //Comando per tagliare
        Command taglia = new Command(scan.nextLine());
        getCommand().add(taglia);
        //Comando per cercare nell'inventario dei nemici
        Command cerca = new Command(scan.nextLine());
        getCommand().add(cerca);
        //Comando per salvare la partita del giocatore
        Command salva = new Command(scan.nextLine());
        getCommand().add(salva);

        //----------------------------------------------------------
        //nuovo scanner per acquisizione da file per le direzioni
        scan = new Scanner(new BufferedReader(new FileReader(file.getAbsolutePath() + "/direzioni.dat")));

        //Direzione nord
        Direzione nord = new Direzione(scan.nextLine());
        getDirezione().add(nord);
        //direzione sud
        Direzione sud = new Direzione(scan.nextLine());
        getDirezione().add(sud);
        //Direzione est
        Direzione est = new Direzione(scan.nextLine());
        getDirezione().add(est);
        //Direzione ovest
        Direzione ovest = new Direzione(scan.nextLine());
        getDirezione().add(ovest);

        //----------------------------------------------------------
        //nuovo scanner per acquisizione da file per le stanze
        scan = new Scanner(new BufferedReader(new FileReader(file.getAbsolutePath() + "/rooms.dat")));

        //Stanza prigioniero
        Room prigione = new Room(scan.nextLine());
        getRoom().add(prigione);
        prigione.setUltimoAmbiente(prigione.getAmbienteNord().toString());

        //Stanza corridoio
        Room corridoio = new Room(scan.nextLine());
        getRoom().add(corridoio);

        //Stanza magazzino
        Room magazzino = new Room(scan.nextLine());
        magazzino.aggiungiNemico(); //inserisco nemico nella stanza
        getNemici().add(magazzino.getNemico());
        getRoom().add(magazzino);

        //Stanza Cucina
        Room cucina = new Room(scan.nextLine());
        getRoom().add(cucina);

        //stanza bagno
        Room bagno = new Room(scan.nextLine());
        //bagno.aggiungiNemico(); //inserisco nemico nella stanza
        getRoom().add(bagno);

        //stanza salone
        Room salone = new Room(scan.nextLine());
        //salone.aggiungiNemico(); //inserisco nemico nella stanza
        getRoom().add(salone);

        //stanza sicurezza
        Room sicurezza = new Room(scan.nextLine());
        sicurezza.bloccaStanza(); //blocca la stanza
        getRoom().add(sicurezza);
        sicurezza.aggiungiNemico();

        //uscita
        Room uscita = new Room(scan.nextLine());
        uscita.bloccaStanza(); //blocca la porta d'uscita.
        getRoom().add(uscita);

        //Confini stanza prigioniero
        prigione.setConfini("nord", corridoio);

        //confini stanza corridoio
        corridoio.setConfini("sud", prigione);
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

        //----------------------------------------------------------
        scan = new Scanner(new BufferedReader(new FileReader(file.getAbsoluteFile() + "/objects.dat")));
        //oggetto tessera
        GameObject tessera = new GameObject(scan.nextLine());
        getObject().add(tessera);
        tessera.setUsabile();

        //oggetto rivista
        GameObject rivista = new GameObject(scan.nextLine());
        getObject().add(rivista);

        //oggetto carta
        GameObject carta = new GameObject(scan.nextLine());
        getObject().add(carta);

        //oggetto scarafaggio
        GameObject scarafaggio = new GameObject(scan.nextLine());
        getObject().add(scarafaggio);
        scarafaggio.setPushable();
        scarafaggio.deletePickable();

        //Munizioni per la pistola
        GameObject munizioniGlock = new GameObject(scan.nextLine());
        getObject().add(munizioniGlock);

        //Corda per legare il giocatore
        GameObject corda = new GameObject(scan.nextLine());
        getObject().add(corda);
        
        //Munizioni arma nemico
        GameObject munizioniUzi = new GameObject(scan.nextLine());
        getObject().add(munizioniUzi);
        
        //Computer per la stanza della sicurezza
        GameObject computer = new GameObject(scan.nextLine());
        getObject().add(computer);
        computer.deletePickable();
        computer.setUsabile();
        //----------------------------------------------------------
        //--- OGGETTI CURATORI
        scan = new Scanner(new BufferedReader(new FileReader(file.getAbsoluteFile() + "/curatori.dat")));

        //Oggetto benda
        Curatore benda = new Curatore(scan.nextLine());
        benda.setPuntiVita(50);
        benda.setCuratore();
        getObject().add(benda);

        //oggetto soda
        Curatore soda = new Curatore(scan.nextLine());
        soda.setPuntiVita(25);
        soda.setCuratore();
        getObject().add(soda);
        //----------------------------------------------------------

        scan = new Scanner(new BufferedReader(new FileReader(file.getAbsoluteFile() + "/weapons.dat")));

        //Inizializzo la pistola
        Weapon glock = new Weapon(scan.nextLine(), munizioniGlock);
        glock.setDanno(67);
        glock.setEquipaggiabile();
        getObject().add(glock);

        //Inzializzo il coltello
        Weapon coltello = new Weapon(scan.nextLine(), null);
        coltello.setDanno(35);
        coltello.setEquipaggiabile();
        getObject().add(coltello);

        //coltellino del giocatore
        Weapon coltellino = new Weapon(scan.nextLine(), null);
        coltellino.setDanno(15);
        coltellino.setEquipaggiabile();
        getObject().add(coltellino);
        
        //Uzi per il nemico nel magazzino
        Weapon uzi = new Weapon(scan.nextLine(), munizioniUzi);
        uzi.setDanno(25);
        uzi.aumentaMunizioni(20);
        uzi.setEquipaggiabile();
        getObject().add(uzi);

        getUser().setEmail(giocatoreAttuale.getEmail());
        getUser().setUsername(giocatoreAttuale.getUsername());
        scan.close();
        
        if(idSalvataggioInput == 0){
            //Stanza iniziale.
            setStanzaCorrente(prigione);
            //INSERIMENTO NORMALE
            //ASSEGNAZIONE OGGETTI
            getUser().getInvetario().addObject(coltellino);
            getUser().getInvetario().addObject(corda);
            
            magazzino.getNemico().getInvetario().addObject(tessera);
            System.out.println("tessera");
            magazzino.getNemico().getInvetario().addObject(munizioniUzi);
            magazzino.getNemico().setArmaEquipaggiata(uzi);
            sicurezza.getNemico().setArmaEquipaggiata(uzi);
            
            salone.getObject().add(rivista);
            bagno.getObject().add(carta);
            salone.getObject().add(scarafaggio);
            magazzino.getObject().add(munizioniGlock);           
            sicurezza.getObject().add(computer);
            bagno.getObject().add(benda);
            cucina.getObject().add(soda);
            magazzino.getObject().add(glock);
            cucina.getObject().add(coltello);
            
            //Blocco il giocatore
            getUser().bloccaGiocatore();
        }else{
            DatabaseInteract database = new DatabaseInteract();
            //INSERIMENTO STANZA CORRENTE
            database.initStanzaCorrente(idSalvataggioInput, this);
            //INSERIMENTO INVENTARIO UTENTE
            database.initInventarioUtente(idSalvataggioInput, this);
            //INSERIMENTO STATS UTENTE
            database.initUtente(idSalvataggioInput, this);
            //INSERIMENTO NEMICI
            database.initNemici(idSalvataggioInput, this);
            //INSERIMENTO INVENTARIO UTENTE
            database.initInventarioNemico(idSalvataggioInput, this);
            //INSERIMENTO STANZA
            database.initStanze(idSalvataggioInput, this);
            System.out.println("Partita caricata, buon divertimento!\n");
            System.out.println(getCurrentRoom().getDescrizioneStanza());
        }
        
    }

    
    @Override
    public void onUpdate(final Parser parser) {
        //COMANDO MOVIMENTO
        if (parser.getComando().containsCommand("vai")) {
            if (parser.getDirezione() != null ) {
                movimentoPlayer(parser.getDirezione().getDirezione());
                if(getCurrentRoom().nemico() && getCurrentRoom().getNemico().getVita() > 0){
                    System.out.println("Attento c'è un nemico! Ammazzalo prima che lui ammazzi te!! ");
                }
            } else {
                System.out.println("Nessuna direzione inserita.");
            }
            
            if(getCurrentRoom().getNomeStanza().toString().contains("Uscita")){
                System.out.println("\n Spero che ti sia divertito! Alla prossima!\n Ciao ciao!!\n\n");
            }

            //COMANDO PER GUARDARE LA STANZA
        } else if (parser.getComando().containsCommand("osserva")) {
            if (getCurrentRoom().nemico() && getCurrentRoom().getNemico().getVita() > 0) {
                getCurrentRoom().getNemico().attaccaConPugni(getUser());
                attaccoNemico();
            }
            guardaStanza();
            getCurrentRoom().getDescrizioneOggetti();

            //COMANDO PER RACCOGLIERE OGGETTI
        } else if (parser.getComando().containsCommand("raccogli")) {
            //OGGETTO
            if (parser.getObject() != null) {
                if (getCurrentRoom().nemico() && getCurrentRoom().getNemico().getVita() > 0) {
                    getCurrentRoom().getNemico().attaccaConPugni(getUser());
                    attaccoNemico();
                }
                raccogliOggetto(parser.getObject());
            } else {
                System.out.println("Che oggetto vuoi prendere?");
            }

            //COMANDO PER LASCIARE UN OGGETTO/ARMA
        } else if (parser.getComando().containsCommand("lascia")) {
            //OGGETTO
            if (parser.getObject() != null) {
                //Controlla che l'utente non è bloccato
                if (!getUser().bloccato()) {
                    if (getCurrentRoom().nemico() && getCurrentRoom().getNemico().getVita() > 0) {
                        getCurrentRoom().getNemico().attaccaConPugni(getUser());
                        attaccoNemico();   
                    }
                    lasciaOggetto(parser.getObject());
                }else{
                    System.out.println("Sei bloccato! Cerca il modo di liberarti!");
                }
            } else {
                System.out.println("Che oggetto vuoi lasciare?");
            }

            //COMANDO PER GUARDARE GLI OGGETTI/ARMI NELL'INVENTARIO
        } else if (parser.getComando().containsCommand("inventario")) {
            if (getCurrentRoom().nemico() && getCurrentRoom().getNemico().getVita() > 0) {
                getCurrentRoom().getNemico().attaccaConPugni(getUser());
                attaccoNemico();
            }
            getUser().getInvetario().guardaInventario();

            //COMANDO PER VISUALIZZARE I COMANDI DISPONIBILI
        } else if (parser.getComando().containsCommand("help")) {
            mostraComandi();

            //COMANDO PER UTILIZZARE DEGLI OGGETTI
        } else if (parser.getComando().containsCommand("usa")) {
            if (parser.getObject() != null) {
                //Controlla che l'utente non è bloccato
                if (!getUser().bloccato()) {
                    if (getCurrentRoom().nemico() && getCurrentRoom().getNemico().getVita() > 0) {
                        getCurrentRoom().getNemico().attaccaConPugni(getUser());
                        attaccoNemico();
                    }
                    if(parser.getObject().getCuratore()){
                        usaCuratore((Curatore) parser.getObject());
                    }else if(parser.getObject().getUsabile()){
                        usaOggetto(parser.getObject());
                    }
                }else{
                    System.out.println("Sei bloccato! Cerca il modo di liberarti!");
                }
            } else {
                System.out.println("Che vuoi usare?");
            }
        } else if (parser.getComando().containsCommand("premi")) {
            if (parser.getObject() != null) {
                if (!getUser().bloccato()) {
                    if(getCurrentRoom().nemico() && getCurrentRoom().getNemico().getVita() > 0) {
                        getCurrentRoom().getNemico().attaccaConPugni(getUser());
                        attaccoNemico();
                    }
                    premiOggetto(parser.getObject());
                }else{
                    System.out.println("Sei bloccato! Cerca il modo di liberarti!");
                }
            } else {
                System.out.println("Non capisco che oggetto vuoi premere.");
            }
            
            //COMANDO PER EQUIPAGGIARE L'ARMA SELEZIONATA
        } else if (parser.getComando().containsCommand("equipaggia")) {
            if (parser.getObject() != null) {
                //Il nemico attacca il giocatore
                if (getCurrentRoom().nemico() && getCurrentRoom().getNemico().getVita() > 0) {
                    getCurrentRoom().getNemico().attaccaConPugni(getUser());
                    attaccoNemico();
                    
                }
                if (parser.getObject().getEquipaggiabile()) {
                    equipaggiaArma((Weapon) parser.getObject());
                } else {
                    System.out.println("Non puoi equipaggiare questo oggetto!");
                }
            } else {
                System.out.println("Devi inserire l'arma da equipaggiare!");
            }
            
            //COMANDO PER RICARICARE L'ARMA
        } else if (parser.getComando().containsCommand("ricarica")) {
            if (parser.getObject() != null) {
                //Controlla che l'utente non è bloccato
                if (!getUser().bloccato()) {
                    //Il nemico attacca il giocatore
                    if (getCurrentRoom().nemico() && getCurrentRoom().getNemico().getVita() > 0) {
                        getCurrentRoom().getNemico().attaccaConPugni(getUser());
                        attaccoNemico();   
                    }
                    cercaArmaRicarica((Weapon) parser.getObject());
                }else{
                    System.out.println("Sei bloccato! Cerca il modo di liberarti!");
                }
            } else {
                System.out.println("Arma errata o non l'hai inserita.");
            }
            
            //COMANDO PER TAGLIARE UN OGGETO
        } else if (parser.getComando().containsCommand("taglia")) {
            if (parser.getObject() != null) {
                if(getCurrentRoom().nemico() && getCurrentRoom().getNemico().getVita() > 0){
                    getCurrentRoom().getNemico().attaccaConPugni(getUser());
                    attaccoNemico();
                }
                tagliaOggetto(parser.getObject());
            } else {
                System.out.println("Cosa vuoi tagliare?");
            }
            
            //COMANDO PER ATTACCARE IL NEMICO
        } else if (parser.getComando().containsCommand("spara")) {
            if (!getUser().bloccato()) {
                if (getCurrentRoom().nemico()) {
                    if (getCurrentRoom().getNemico().getVita() > 0) {
                        if (getUser().getArmaEquipaggiata() != null) {
                            //Il giocatore attacca il nemico con l'arma
                            getUser().attaccaConArma(getCurrentRoom().getNemico());
                            attaccoUtente();
                            //Il nemico attacca l'utente
                            getCurrentRoom().getNemico().attaccaConArma(getUser());
                            attaccoNemico();
                            
                        } else {
                            //Il giocatore attacca il nemico con i pugni
                            getUser().attaccaConPugni(getCurrentRoom().getNemico());
                            attaccoUtente();
                            //Il nemico attacca l'utente
                            getCurrentRoom().getNemico().attaccaConPugni(getUser());
                            attaccoNemico();
                        }
                    } else {
                        System.out.println("Il nemico è morto!");
                    }
                }else{
                    System.out.println("Non c'è nessun nemico qui.");
                }
            }else{
                System.out.println("Sei bloccato! Cerca il modo di liberarti!");
            }
            
            //COMANDO PER CERCARE DAL CADAVERE IN STANZA
        }else if (parser.getComando().containsCommand("cerca")){
            if(!getUser().bloccato()){
                //Controlla se esiste un nemico nella stanza
                if(getCurrentRoom().nemico()){
                    //Controlla se il nemico è morto
                    if(getCurrentRoom().getNemico().getVita() == 0){
                        if(getCurrentRoom().getNemico().getInvetario().getObjects().size() > 0){
                            estraiOggetti(getCurrentRoom().getNemico().getInvetario());
                            System.out.println("Oggetti del cadavere inseriti nella stanza.");
                        }else{
                            System.out.println("L'inventario del cadavere è vuoto!");
                        }
                    } else { //Se il nemico non è morto, attacca
                        System.out.println("Devi prima amazzare il nemico prima di cercare nel suo inventario!!");
                        //Il nemico attacca l'utente
                        getCurrentRoom().getNemico().attaccaConPugni(getUser());
                        attaccoNemico();
                    }
                }else{
                    System.out.println("In questa stanza non c'è nessuno.");
                }
            }else{
                System.out.println("Sei bloccato! Cerca il modo di liberarti!");
            }
        }else if(parser.getComando().containsCommand("salva")){
            Salvataggio salvataggio = new Salvataggio(this, getIdSalvataggio());
            
            try {
                System.out.println("Salvataggio in corso... Attendere...");                
                salvataggio.salvaPartita();
                setIdSalvataggio(salvataggio.getIdSalvataggio());
                System.out.println("\b\b\b\b\b");
                System.out.println("Partita salvata con successo!");
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            
        }
    }

    //========================================================
    /**
     * Estra gli oggetti del cadavere dall'inventario
     * inserendoli nella stanza.
     * @param inventarioCadavere 
     */
    private void estraiOggetti(Inventory inventarioCadavere){
        for (int i = 0; i < inventarioCadavere.getObjects().size(); i++) {
            getCurrentRoom().getObject().add(inventarioCadavere.getObjects().get(i));
        }
        inventarioCadavere.getObjects().clear();
        
    }
    /**
     * Notifica che l'utente ha attacco il nemico
     * informando la vita del nemico.
     */
    private void attaccoUtente(){
        System.out.println("Hai attacco il nemico!");
        if(getCurrentRoom().getNemico().getVita()>0){
            System.out.println("Vita del nemico: "+getCurrentRoom().getNemico().getVita());
        }else{
            System.out.println("Nemico morto!");
        }
    }
   
    /**
     * Notifica l'utente che è stato attaccato dal nemico
     * informando della vita rimasta.
     */
    private void attaccoNemico() {
        //Il nemico attacca il giocatore con l'arma
        System.out.println("Il nemico ti ha attaccato! ");
        if (getUser().getVita() > 0) {
            System.out.println("Vita attuale: " + getUser().getVita());
        } else {
            System.out.println("\nSei morto!");
            System.exit(0);
        }
    }
    
    /**
     * Metodo per tagliare un oggetto
     * con uno strumento
     * @param oggettoInput oggetto da tagliare 
     */
    private void tagliaOggetto(GameObject oggettoInput) {
        switch (oggettoInput.getNome()) {
            case "corda":
                if (getUser().getInvetario().containsObject(oggettoInput)) {
                    if (getUser().getArmaEquipaggiata() != null) {
                        if (getUser().getArmaEquipaggiata().containsObject("coltellino")) {
                            getUser().sbloccaGiocatore();
                            getUser().getInvetario().dropObject(oggettoInput);
                            System.out.println("Hai tagliato la corda!");
                            System.out.println("Il coltellino si è rotto!");
                            getUser().setArmaEquipaggiata(null);
                        } else {
                            System.out.println("Non puoi tagliare la corda.");
                        }
                    } else {
                        System.out.println("Devi equipaggiare qualcosa per tagliare!");
                    }
                }
                break;
        }
    }

    /**
     * Controlla se l'arma è equipaggiata
     *
     * @param armaInput - arma inserita in input dall'utente
     */
    private void cercaArmaRicarica(Weapon armaInput) {
        if (armaInput.getEquipaggiabile()) {
            //Controlla se l'arma inserita è equipaggiata
            if (getUser().getArmaEquipaggiata() != null && getUser().getArmaEquipaggiata().containsObject(armaInput.getNome())) {
                ricaricaArma();
            } else {
                System.out.println("Devi equipaggiare l'arma prima di ricaricarla.");
            }
        } else {
            System.out.println("Non puoi ricaricare quest'arma.");
        }

    }

    /**
     * Ricarica l'arma dell'utente, se ci sono delle munizioni nell'inventario o
     * nella stanza.
     */
    private void ricaricaArma() {
        if (getUser().getArmaEquipaggiata().getTipoMunizioni() != null) {
            if (getUser().getInvetario().containsObject(getUser().getArmaEquipaggiata().getTipoMunizioni())) {
                int munizioniDaAumentare = (int) (Math.random() * 15);

                while (munizioniDaAumentare < 3) {
                    munizioniDaAumentare = (int) (Math.random() * 15);
                }

                //ricarica l'arma
                getUser().getArmaEquipaggiata().aumentaMunizioni(munizioniDaAumentare);
                System.out.println("Arma ricaricata!");
                System.out.println("Munizioni arma: " + getUser().getArmaEquipaggiata().getMunizioni());
                //elimina le munizioni dall'inventario
                getUser().getInvetario().dropObject(getUser().getArmaEquipaggiata().getTipoMunizioni());
                //Cerca le munizioni nella stanza
            } else {
                System.out.println("Non ci sono munizioni per quest'arma.");
            }
        } else {
            System.out.println("Quest'arma non supporta munizioni.");
        }
    }

    /**
     * Permette di equipaggiare un arma inserita in input Se il giocatore ha già
     * un'arma equipaggiata, viene effettuato il cambio
     *
     * @param armaInput arma inserita in input dall'utente e riconosciuta dal
     * parser
     */
    private void equipaggiaArma(Weapon armaInput) {
        if (getUser().getArmaEquipaggiata() != null) {
            Weapon armaTemporanea = new Weapon();
            armaTemporanea = getUser().getArmaEquipaggiata();

            if (getUser().getInvetario().containsObject(armaInput)) {
                getUser().setArmaEquipaggiata(armaInput);
                getUser().getInvetario().dropObject(armaInput);
                getUser().getInvetario().addObject(armaTemporanea);
                System.out.println("Hai equipaggiato " + armaInput.getNome());
            } else {
                System.out.println("Non hai " + armaInput.getNome());
            }
        } else {
            if (getUser().getInvetario().containsObject(armaInput)) {
                getUser().setArmaEquipaggiata(armaInput);
                getUser().getInvetario().dropObject(armaInput);
                System.out.println("Hai equipaggiato " + armaInput.getNome());
            } else {
                System.out.println("Non hai " + armaInput.getNome());
            }
        }
    }

    /**
     * Metodo per premere un oggetto, provoca un evento
     *
     * @param oggettoInput
     */
    private void premiOggetto(GameObject oggettoInput) {
        if (getCurrentRoom().containsObject(oggettoInput)) {
            if (oggettoInput.isPushable()) {
                if (!oggettoInput.isPushed()) {
                    oggettoInput.push();
                    switch (oggettoInput.getNome()) {
                        case "scarafaggio imbalsamato":
                            //viene sbloccata la porta della sicurezza
                            getCurrentRoom().getRoom("sud").sbloccaStanza();
                            System.out.println("Stanza sbloccata: " + getCurrentRoom().getRoom("sud").getNomeStanza() + ".");
                            System.out.println("La stanza si trova alla tua destra.");
                            break;
                    }
                } else {
                    System.out.println("L'oggetto è stato già premuto.");
                }
            } else {
                System.out.println("L'oggetto non può essere premuto.");
            }
        }
    }

    /**
     * Funzione che gestisce il movimento del player da una stanza all'altra
     * controlla se esiste una stanza nella direzione interessata, controlla se
     * e' bloccata, e agisce di conseguenza.
     *
     * @param direzioneInput - direzione inserita in input
     */
    private void movimentoPlayer(String direzioneInput) {
        //La stanza è appena illuminata da una candela, e tu sei ammanettato al termosifone, trova il modo di liberarti! 
        //Hai un coltellino svizzero nella tasca, e davanti a te c'è una porta.
        if (getCurrentRoom().getRoom(getUser().getBussola()
                .getPosizioneUtente(direzioneInput)) != null) {
            //Se esiste la stanza esiste, controlla se il giocatore è bloccato
            if (!getUser().bloccato()) {
                //Se la stanza esiste, controlla se è bloccata
                if (!getCurrentRoom().getRoom(getUser().getBussola()
                        .getPosizioneUtente(direzioneInput)).bloccata()) {

                    setStanzaCorrente(getCurrentRoom().getRoom(getUser().getBussola()
                            .getPosizioneUtente(direzioneInput)));
                    System.out.println(getCurrentRoom().getDescrizioneStanza());

                    getCurrentRoom().setUltimoAmbiente(getCurrentRoom().getAmbienteRoom(getUser().getBussola()
                            .getPosizioneUtente(direzioneInput)).toString());

                    getUser().getBussola().spostamentoInput(getUser().getBussola()
                            .getPosizioneUtente(direzioneInput));
                }else{
                    System.out.println("La porta è bloccata! Cerca il modo di aprirla!");
                }
                getCurrentRoom().nemico();
            }else{
                System.out.println("Sei bloccato! Cerca il modo di liberarti!");
            }
        } else {
            System.out.println("Non esiste nessuna stanza nella direzione in cui ti vuoi muovere!");
        }
    }

    /**
     * Fornisce in output l'ambientazione della stanza.
     */
    private void guardaStanza() {
        if(this.getCurrentRoom().getUltimoAmbiente()!=null){
            System.out.println(this.getCurrentRoom().getUltimoAmbiente());
        }else{
            System.out.println(this.getCurrentRoom().getDescrizioneStanza());
        }
    }

    /**
     * Gestisce la raccolta dell'oggetto indicato dall'utente in input, viene
     * inserito nell'inventario
     *
     * @param oggettoInput - oggetto riconosciuto dal parser
     */
    private void raccogliOggetto(GameObject oggettoInput) {
        if (this.getCurrentRoom().containsObject(oggettoInput)) {
            //Se esiste l'oggetto, controlla se il player
            //ha almeno uno slot libero
            if (oggettoInput.isPickable()) {
                if (this.getUser().getInvetario().getSizeInvetory() > 0) {
                    getUser().getInvetario().addObject(oggettoInput);
                    System.out.println("Hai raccolto: " + oggettoInput.getNome());
                    this.getCurrentRoom().deleteObject(oggettoInput);
                }else{
                    System.out.println("Inventario pieno! Elimina qualcosa!");
                }
            } else {
                System.out.println("Non puoi prendere " + oggettoInput.getNome() + ".");
            }

        } else {
            System.out.println("Non esiste nessun oggetto con quel nome.");
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
            System.out.println("Hai lasciato: " + oggettoInput.getNome());
            //Inserisco l'oggetto alla stanza.
            getCurrentRoom().getObject().add(oggettoInput);

            //controlla se l'oggetto inserito è equipaggiato
        } else if (getUser().getArmaEquipaggiata().containsObject(oggettoInput.getNome())) {
            getCurrentRoom().getObject().add(getUser().getArmaEquipaggiata());
            System.out.println("Hai lasciato: " + oggettoInput.getNome());
            getUser().setArmaEquipaggiata(null);
        } else {
            System.out.println("Non hai questo oggetto.");
        }
    }

    /**
     * Mostra i comandi disponibili.
     */
    private void mostraComandi() {
        System.out.println("+------------------------------------ Comandi House Break --------------------------------------+");
        getCommand().forEach((comando) -> {
            System.out.println(" |" + comando.getNomeComando() + " \t " + comando.getDescrizioneComando());
        });
        System.out.println("+-------------------------------------------------------------------------------------------------------------+");
    }

    /**
     * Metodo che permette di utilizzare un oggetto per incrementare la vita al
     * giocatore
     *
     * @param oggettoCuratore - oggetto scelto dal giocatore
     */
    private void usaCuratore(Curatore oggettoCuratore) {
        if (getUser().getInvetario().containsObject(oggettoCuratore)) {
            if (getUser().getVita() < 100) {
                getUser().aumentaVita(oggettoCuratore.getPuntiVita());
                //Eliminazione oggetto dall'inventario
                getUser().getInvetario().dropObject(oggettoCuratore);
                System.out.println("Vita attuale: " + getUser().getVita());
            } else {
                System.out.println("Non ti serve, la vita è al 100%");
            }
        } else {
            System.out.println("Non hai " + oggettoCuratore.getNome() + " nell'inventario.");
        }
    }
    
    /**
     * Usa l'oggetto fornito in input
     * @param oggettoInput 
     */
    private void usaOggetto(GameObject oggettoInput){
        if (oggettoInput.getNome().contains("tessera") || oggettoInput.getNome().contains("Computer sicurezza")){
            if(getUser().getInvetario().containsObject(oggettoInput)){
                int indexUscita = cercaPortaUscita();
                if (indexUscita != 1) {
                    getRoom().get(indexUscita).sbloccaStanza();
                    System.out.println("La porta per uscire è stata sbloccata! Scappa via!!");
                }
            }else{
                System.out.println("Hai bisogno di una tessera per usare il computer.");
            }
        }
    }
    
    /**
     * Ritorna l'index della stanza uscita
     * @return int
     */
    private int cercaPortaUscita(){
        for(int i=0; i < getRoom().size(); i++){
            if(getRoom().get(i).getNomeStanza().toString().equalsIgnoreCase("Uscita")){
                return i;
            }
        }
        return -1;
    }
}

