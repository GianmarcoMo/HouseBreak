package GameComponents;

import java.util.ArrayList;
import GameComponents.Input;
/**
 *
 * @author  Moresi Gianmarco
 */
public class Room implements Input{
    //ID per identificare una stanza nel caso ci siano due stanze con lo stesso nome
    private static int id;
    
    //nome stanza
    private final StringBuilder nome;
    
    //descrizione stanza
    private final StringBuilder descrizione;
    
    //ambiente viene utilizzato per descrivere la stanza 
    //quando il giocatore si guarda attorno
    private final StringBuilder ambienteNord;
    private final StringBuilder ambienteEst;
    private final StringBuilder ambienteOvest;
    private final StringBuilder ambienteSud;
    
    //lista di stanza confinanti con quella attuale
    //divise in base alla loro direzione
    private Room nord=null;
    private Room sud=null;
    private Room ovest=null;
    private Room est=null;
    
    //Lista di oggetti presenti nella stanza
    private ArrayList<GameObject> objects;
    
    public Room(){
        id++;
        this.nome=new StringBuilder();
        this.descrizione=new StringBuilder();
        this.ambienteNord= new StringBuilder();
        this.ambienteEst= new StringBuilder();
        this.ambienteOvest= new StringBuilder();
        this.ambienteSud= new StringBuilder();
    }
    
    public Room(String lineaFile){
        id++;
        this.nome=new StringBuilder();
        this.descrizione=new StringBuilder();
        this.ambienteNord= new StringBuilder();
        this.ambienteEst= new StringBuilder();
        this.ambienteOvest= new StringBuilder();
        this.ambienteSud= new StringBuilder();
        acquisizoneInputFile(lineaFile);
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
    
    //Inserisce gli oggetti all'interno della stanza
    public void setObjects(GameObject[] objects){
        for(int k=0; k<objects.length; k++){
            this.objects.add(k, objects[k]);
        }
    }
    
    //inserisce l'oggetto che ha rimosso dall'inventario l'utente
    public void setObjectDropped(GameObject object){
        this.objects.add(this.objects.size(), object);
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
    
    public int getId(){
        return this.id;
    }

    @Override
    public void acquisizoneInputFile(String lineaInput) {
        int index=0;
        
        //Acquisizione il nome della stanza
        while(lineaInput.charAt(index)!= '.'){
            this.nome.append(lineaInput.charAt(index));
            index++;
        }
        //Salta la posizione dove si trova il punto.
        index++;
        
        //Acquisizone descrizione stanza
        while(lineaInput.charAt(index)!='.'){
            this.descrizione.append(lineaInput.charAt(index));
            index++;
        }
        
        //Salta la posizione dove si trova il punto
        index++;
        
        //acquisizione ambiente nord
        while(lineaInput.charAt(index)!='.'){
            this.ambienteNord.append(lineaInput.charAt(index));
            index++;
        }
        index++;
        
        //acquisizione ambiente est
        while(lineaInput.charAt(index)!='.'){
            this.ambienteEst.append(lineaInput.charAt(index));
            index++;
        }
        index++;
        
        //acquisizione ambiente sud
        while(lineaInput.charAt(index)!='.'){
            this.ambienteSud.append(lineaInput.charAt(index));
            index++;
        }
        index++;
        
        //acquisizione ambiente Ovest
        while(lineaInput.charAt(index)!='.'){
            this.ambienteOvest.append(lineaInput.charAt(index));
            index++;
        }
        index++;
        
    }
    
}
