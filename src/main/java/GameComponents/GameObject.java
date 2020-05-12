package GameComponents;

import java.util.ArrayList;
import GameComponents.Input;
/**
 *
 * @author Moresi Gianmarco
 */
public class GameObject implements Input {
    //identifica un modo univoco l'oggetto
    private static int ID;

    private final StringBuilder name;

    private final StringBuilder descrizione;
    
    private ArrayList<String> alias;
    
    //indica se l'oggetto può essere aperto
    private boolean openable =false;
    
    //indica se l'oggetto è aperto 
    private boolean open =false;
    
    //indica se l'oggetto può essere raccolto
    private boolean pickable=true;
    
    //indica se l'oggetto può essere premuto
    private boolean pushable=false;
    
    //indica se l'oggetto è stato premuto
    private boolean push=false;

    public GameObject(){
        ID++;
        this.name=new StringBuilder();
        this.descrizione=new StringBuilder();
        this.alias=new ArrayList<>();
    }

    public GameObject(String lineaInput){
        ID++;
        this.name=new StringBuilder();
        this.descrizione=new StringBuilder();
        this.alias=new ArrayList<>();
        acquisizoneInputFile(lineaInput);
    }

    //Inserisce tutti gli alias passati in input
    public void setAlias(String aliasInput){
        alias.add(aliasInput);
    }
    
    public String getNome(){
        return this.name.toString();
    }
    
    public String getDescrizione(){
        return this.descrizione.toString();
    }
    //Setta l'oggetto pushable, può essere premuto
    public void setPushable(){
        this.pushable=true;
    }

    //restituisce vero o false se l'oggetto può essere premuto
    public boolean isPushable(){
        return this.pushable;
    }
    //restituisce vero o falso se l'oggetto è stato premuto
    public boolean isPushed(){
        return this.push;
    }

    //setta l'oggetto apribile
    public void setOpenable(){
        this.openable=true;
    }
    //restituisce vero o falso se l'oggetto può essere apribile
    public boolean isOpenable(){
        return this.openable;
    }
    //restituisce vero o falso se l'oggetto è aperto
    public boolean isOpen(){
        return this.open;
    }

    public void setPickable(){
        this.pickable=true;
    }
    
    public void deletePickable(){
        this.pickable=false;
    }

    public boolean isPickable(){
        return this.pickable;   
    }
    
    //Controlla se esiste l'oggetto inserito in input
    //effettua un controllo anche nella lista alias
    public boolean containsObject(String object){
        //controllo principale
        if(this.name.equals(object)){
            return true;
        }else{ //se il nome non è uguale, cerca negli alias
            return this.containsAlias(object);
        }
    }
    
    public boolean containsAlias(String alias){
        return this.alias.contains(alias);
    }

    @Override
    public boolean equals(Object obj){
        final GameObject object= (GameObject) obj;
        if(this.ID == object.ID){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void acquisizoneInputFile(String lineaInput) {
        int index=0;
        //Acquisizione nome oggetto
        while(lineaInput.charAt(index)!= '-'){
            this.name.append(lineaInput.charAt(index));
            index++;
        }
        //Salto carattere per dividere argomenti
        index++;
        
        while(lineaInput.charAt(index) != '-'){
            this.descrizione.append(lineaInput.charAt(index));
            index++;
        }
        index++;
        
        //Acquisizione dei vari alias
        StringBuilder singoloAlias=new StringBuilder();
        while(lineaInput.charAt(index) != '-'){
            if(lineaInput.charAt(index)==','){
                //inserisco l'alias estratto nella lista
                alias.add(singoloAlias.toString());
                //pulisco lo stringbuilder per contenere altri alias
                singoloAlias.delete(0, singoloAlias.length());
            }else{
                //Compongo il singolo alias
                singoloAlias.append(lineaInput.charAt(index));
            }
            index++;
        }
    }
    

   
}
