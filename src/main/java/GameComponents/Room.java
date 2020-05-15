package GameComponents;

import java.util.ArrayList;
import GameComponents.Input;
import Utils.EditorParola;
/**
 *
 * @author  Moresi Gianmarco
 */
public class Room implements Input{
    //ID per identificare una stanza nel caso ci siano due stanze con lo stesso nome
    private static int id;
    
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
    
    private boolean nemico;
    
    //lista di stanza confinanti con quella attuale
    //divise in base alla loro direzione
    private Room nord=null;
    private Room sud=null;
    private Room ovest=null;
    private Room est=null;
    
    //Lista di oggetti presenti nella stanza
    private ArrayList<GameObject> objects;
    
    //Lista delle armi 
    private ArrayList<Weapon> armi;
    
    public Room(){
        id++;
        this.nome=new StringBuilder();
        this.descrizione=new StringBuilder();
        this.ambienteNord= new StringBuilder();
        this.ambienteEst= new StringBuilder();
        this.ambienteOvest= new StringBuilder();
        this.ambienteSud= new StringBuilder();
        this.nemico=false;
        this.objects= new ArrayList<>();
        this.armi= new ArrayList<>();
    }
    
    public Room(String lineaFile){
        id++;
        this.nome=new StringBuilder();
        this.descrizione=new StringBuilder();
        this.ambienteNord= new StringBuilder();
        this.ambienteEst= new StringBuilder();
        this.ambienteOvest= new StringBuilder();
        this.ambienteSud= new StringBuilder();
        this.nemico=false;
        this.objects= new ArrayList<>();
        this.armi= new ArrayList<>();
        acquisizoneInputFile(lineaFile);
    }
    
    //Aggiunge un nemico alla stanza
    public void aggiungiNemico(){
        this.nemico=true;
    }
    
    //blocca la stanza indicata
    public void bloccaStanza(){
        this.bloccata=true;
    }
    
    //Restituisce l'insieme degli oggetti della stanza
    public ArrayList<GameObject> getObject(){
        return this.objects;
    }
    
    //Restituisce l'array di armi
    public ArrayList<Weapon> getArmi(){
        return this.armi;
    }
    
    //sblocca la stanza indicata
    public void sbloccaStanza(){
        this.bloccata=false;
    }
    
    //restituisce se la porta è bloccata
    public boolean bloccata(){
        if(bloccata){
            System.out.println("La porta è bloccata! Cerca il modo di aprirla!");
        }
        return this.bloccata;
    }
    
    //elimina nemico dalla stanza
    //usato quandno viene ammazzato dal giocatore
    public void eliminaNemico(){
        this.nemico=false;
    }
    
    //controlla se in questa stanza c'è un nemico
    //Avvisa l'utente.
    public boolean nemico(){
        if(nemico){
            System.out.println("Attento c'è un nemico! Ammazzalo prima che lui ammazzi te!! ");
            return true;
        }else{
            System.out.println("Non ci sono nemici in questa stanza!");
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
    
    //Controlla se nella stanza ci sono degli oggetti 
    //che il giocatore può usare.
    public void oggetti() {
        if (this.objects.size() >= 1 || this.armi.size() >= 1) {
            int k = 0;
            System.out.println("Ci sono degli oggetti nella stanza!");
            System.out.print("Nella stanza è presente: ");

            if (this.objects.size() >= 1) {
                System.out.print(this.objects.get(k).getNome());
                for (k = 1; k < this.objects.size(); k++) {
                    System.out.print(", ");
                    System.out.print(this.objects.get(k).getNome());
                }
                System.out.print(".\n");

            }
            k = 0;
            if (this.armi.size() >= 1) {
                System.out.print("Armi: "+this.armi.get(k).getNomeArma());
                for (k = 1; k < this.armi.size(); k++) {
                    System.out.print(", ");
                    System.out.print(this.armi.get(k).getNomeArma());
                }
                System.out.print(".\n");
            }

        }
    }
    
    //inserisce l'oggetto che ha rimosso dall'inventario l'utente
    public void setObjectDropped(GameObject object){
        this.objects.add(object);
    }    
         
    //Rimuove l'oggetto in input, se esiste.
    public void deleteObject(String nameObject){
        //se la stanza contiene l'oggetto
        if(this.containsObject(nameObject)){
            //rimuove l'oggetto dalla lista tramite l'index trovato con la funzione
            // .getIndexObject
            this.objects.remove(this.getIndexObject(nameObject));
        }
    }
    
    //Restituisce true o false se la stanza contiene l'oggetto.
    public boolean containsObject(String object){
        int k=0;
        while(this.objects.get(k)!=null){
            //richiama il metodo di objects per effettuare il confronto con l'oggetto.
            if(this.objects.get(k).containsObject(object)){
                return true;
            }
        }
        return false;
    }
    
    //Resistuisce l'index dell'oggetto cercato, se esiste.
    private int getIndexObject(String object){
        int index=0;
        while(this.objects.get(index)!=null){
            if(this.objects.get(index).containsObject(object)){
                return index;
            }
        }
        return -1;
    }
    
    public StringBuilder getNomeStanza(){
        return this.nome;
    }
    
    public StringBuilder getDescrizioneStanza(){
        return this.descrizione;
    }
    
    public StringBuilder getAmbienteNord(){
        //richiama il metodo per vedere se ci sono oggetti nella stanza.
        this.oggetti();
        return this.ambienteNord;
    }
    public StringBuilder getAmbienteSud(){
        //richiama il metodo per vedere se ci sono oggetti nella stanza.
        this.oggetti();
        return this.ambienteSud;
    }
    public StringBuilder getAmbienteEst(){
        //richiama il metodo per vedere se ci sono oggetti nella stanza.
        this.oggetti();
        return this.ambienteEst;
    }
    public StringBuilder getAmbienteOvest(){
        //richiama il metodo per vedere se ci sono oggetti nella stanza.
        this.oggetti();
        return this.ambienteOvest;
    }
    
    public int getId(){
        return this.id;
    }

    @Override
    public void acquisizoneInputFile(final String lineaInput) {
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
