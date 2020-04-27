package GameComponents;

import java.util.ArrayList;

/**
 *
 * @author  Moresi Gianmarco
 */
public class Room {
    //ID per identificare una stanza nel caso ci siano due stanze con lo stesso nome
    private int id;
    
    private String name;
    private String description;
    //ambiente viene utilizzato per descrivere la stanza 
    //quando il giocatore si guarda attorno
    private String ambiente;
    
    //lista di stanza confinanti con quella attuale
    //divise in base alla loro direzione
    private Room nord;
    private Room sud;
    private Room ovest;
    private Room est;
    
    //Lista di oggetti presenti nella stanza
    private ArrayList<GameObject> objects;
    
    public Room(int id, String description, String ambiente){
        this.id=id;
        this.description=description;
        this.ambiente=ambiente;
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
    
}
