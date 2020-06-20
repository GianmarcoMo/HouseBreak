package GameComponents;

import java.util.ArrayList;
import Utente.User;
import Utils.EditorParola;
/**
 *
 * @author  Moresi Gianmarco
 */
public class Room implements Input{   
    //nome stanza
    private StringBuilder nome;
    
    //descrizione stanza
    private StringBuilder descrizione;
    
    //indica se la stanza è bloccata o no
    private boolean bloccata=false;
    
    //ambiente viene utilizzato per descrivere la stanza 
    //quando il giocatore si guarda attorno
    private StringBuilder ambienteNord;
    private StringBuilder ambienteEst;
    private StringBuilder ambienteOvest;
    private StringBuilder ambienteSud;
    //quando viene effettuato lo spostamento, in base alla direzione,
    //viene inserito uno dei 4 ambienti in ultimoAmbiente
    //Cosi quando viene effettuato il comando "guarda"
    //viene mostrato ultimoAmbiente che coincide con la direzione del giocatore.
    private String ultimoAmbiente;
    
    private User nemico = null;
    
    //lista di stanza confinanti con quella attuale
    //divise in base alla loro direzione
    private Room nord=null;
    private Room sud=null;
    private Room ovest=null;
    private Room est=null;
    
    //Lista di oggetti presenti nella stanza
    private final ArrayList<GameObject> objects;
    
    public Room(){
        this.nome=new StringBuilder();
        this.descrizione=new StringBuilder();
        this.ambienteNord= new StringBuilder();
        this.ambienteEst= new StringBuilder();
        this.ambienteOvest= new StringBuilder();
        this.ambienteSud= new StringBuilder();
        this.objects= new ArrayList<>();
    }
    
    public Room(String lineaFile){
        this.nome=new StringBuilder();
        this.descrizione=new StringBuilder();
        this.ambienteNord= new StringBuilder();
        this.ambienteEst= new StringBuilder();
        this.ambienteOvest= new StringBuilder();
        this.ambienteSud= new StringBuilder();
        this.ultimoAmbiente=null;
        this.objects= new ArrayList<>();
        acquisizoneInputFile(lineaFile);
    }
    
    //Aggiunge un nemico alla stanza
    public void aggiungiNemico(){
        this.nemico = new User();
    }
    
    public User getNemico(){
        return this.nemico;
    }
    
    public void setUltimoAmbiente(String ambienteInput){
        this.ultimoAmbiente=ambienteInput;
    }
    
    public String getUltimoAmbiente(){
        return this.ultimoAmbiente;
    }
    
    //blocca la stanza indicata
    public void bloccaStanza(){
        this.bloccata=true;
    }
    
    //Restituisce l'insieme degli oggetti della stanza
    public ArrayList<GameObject> getObject(){
        return this.objects;
    }
    
    //sblocca la stanza indicata
    public void sbloccaStanza(){
        this.bloccata=false;
    }
    
    //restituisce se la porta è bloccata
    public boolean bloccata(){
        return this.bloccata;
    }
    
    //controlla se in questa stanza c'è un nemico
    //Avvisa l'utente.
    public boolean nemico(){
        if(nemico != null){
            return true;
        }else{
            return false;
        }
    }
    
    //funzione che passa in input la posizione della stanza 
    //nord-sud-est-ovest e setta la stanza, se esiste una
    //la sostituisce
    public void setConfini(String posizione, Room room){
        //setta stanza al nord
        if(posizione.contains("nord")){
            this.nord=room;
        //setta stanza al sud
        }else if(posizione.contains("sud")){
            this.sud=room;
        //setta stanza a ovest
        }else if(posizione.contains("ovest")){
            this.ovest=room;
        //Setta stanza a est
        }else if(posizione.contains("est")){
            this.est=room;
        }
    }

    public Room getRoom(String direzione){
        if(direzione.contains("nord")){
            return this.nord;
        }else if(direzione.contains("sud")){
            return this.sud;
        }else if(direzione.contains("ovest")){
            return this.ovest;
        }else if(direzione.contains("est")){
            return this.est;
        }else{
            return null;
        }
    }

    //Controlla se nella stanza ci sono degli oggetti 
    //che il giocatore può usare.
    public void getDescrizioneOggetti() {
        if (this.objects.size() >= 1) {
            System.out.println("\nOggetti:");
            if (this.objects.size() >= 1) {
                objects.forEach((elemento) -> {
                    System.out.println("-"+elemento.getNome());
                });
            }
        }
    }
    
    //inserisce l'oggetto che ha rimosso dall'inventario l'utente
    public void setObjectDropped(GameObject object){
        this.objects.add(object);
    }    
         
    //Rimuove l'oggetto in input
    public void deleteObject(GameObject objectInput) {
        this.objects.remove(this.getIndexObject(objectInput));
    }

    //Resistuisce l'index dell'oggetto cercato, se esiste.
    private int getIndexObject(GameObject object) {
        for (int index = 0; index < objects.size(); index++) {
            if (object == objects.get(index)) {
                return index;
            }
        }
        return -1;
    }
    
    /**
     * Restituisce true o false se la stanza contiene l'oggetto.
     * @param oggettoInput - oggetto passato in input
     * @return boolean - se l'oggetto e' presente in stanza o meno
     */
    public boolean containsObject(GameObject oggettoInput){
        //controlla gli oggetti dell'arrayList objects e controlla se
        //e' uguale all'oggetto in input restituendo un boolean
        return objects.stream().anyMatch((elemento) -> (elemento.containsObject(oggettoInput.getNome())));
    }
    
    public StringBuilder getNomeStanza(){
        return this.nome;
    }
    
    public StringBuilder getDescrizioneStanza(){
        return this.descrizione;
    }
    
    public StringBuilder getAmbienteNord(){
        return this.ambienteNord;
    }
    public StringBuilder getAmbienteSud(){
        return this.ambienteSud;
    }
    public StringBuilder getAmbienteEst(){
        return this.ambienteEst;
    }
    public StringBuilder getAmbienteOvest(){
        return this.ambienteOvest;
    }
    
    public StringBuilder getAmbienteRoom(String direzione){
        if(direzione.contains("nord")){
            return this.ambienteNord;
        }else if(direzione.contains("sud")){
            return this.ambienteSud;
        }else if(direzione.contains("ovest")){
            return this.ambienteOvest;
        }else if(direzione.contains("est")){
            return this.ambienteEst;
        }else{
            return null;
        }
    }
    
    public boolean equals(String nomeStanzaInput){
        return this.nome.toString().equals(nomeStanzaInput);
    }

    @Override
    public final void acquisizoneInputFile(final String lineaInput) {
        int index=0;
        //edito che permette di eliminare il trailing space
        EditorParola editor= new EditorParola();
        //divide la parola in input in tokens
        String[] tokens= lineaInput.split("\\s+");
        
        //Acquisizione il nome della stanza
        while(!tokens[index].equals(".")){
            this.nome.append(tokens[index]).append(" ");
            index++;
        }
        this.nome=editor.rimozzioneTrailingSpace(this.nome);
        //Salta la posizione dove si trova il punto.
        index++;
        
        //Acquisizone descrizione stanza
        while(!tokens[index].equals(".")){
            this.descrizione.append(tokens[index]).append(" ");
            index++;
        }
        this.descrizione= editor.rimozzioneTrailingSpace(this.descrizione);
        //Salta la posizione dove si trova il punto
        index++;
        
        //acquisizione ambiente nord
        while(!tokens[index].equals(".")){
            this.ambienteNord.append(tokens[index]).append(" ");
            index++;
        }
        this.ambienteNord= editor.rimozzioneTrailingSpace(this.ambienteNord);
        index++;
        
        //acquisizione ambiente est
        while(!tokens[index].equals(".")){
            this.ambienteEst.append(tokens[index]).append(" ");
            index++;
        }
        this.ambienteEst= editor.rimozzioneTrailingSpace(this.ambienteEst);
        index++;
        
        //acquisizione ambiente sud
        while(!tokens[index].equals(".")){
            this.ambienteSud.append(tokens[index]).append(" ");
            index++;
        }
        this.ambienteSud= editor.rimozzioneTrailingSpace(this.ambienteSud);
        index++;
        
        //acquisizione ambiente Ovest
        while(!tokens[index].equals(".")){
            this.ambienteOvest.append(tokens[index]).append(" ");
            index++;
        }
        this.ambienteOvest= editor.rimozzioneTrailingSpace(this.ambienteOvest);
        index++;
    }
    
}
