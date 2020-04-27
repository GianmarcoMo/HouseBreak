package GameComponents;

import java.util.ArrayList;

/**
 *
 * @author Moresi Gianmarco
 */
public class GameObject {
    //identifica un modo univoco l'oggetto
    private final int id;
    private String name;
    private String descrizione;
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

    public GameObject(int id, String name, String description){
        this.id=id;
        this.name=name;
        this.descrizione=description;
    }

    //Inserisce tutti gli alias passati in input
    public void setAlias(String[] alias){
        for(int k=0; k<alias.length; k++){
            this.alias.add(k,alias[k]);
        }
    }
    
    public String getNome(){
        return this.name;
    }
    
    public String getDescrizione(){
        return this.descrizione;
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
        return this.open;
    }
    //restituisce vero o falso se l'oggetto è aperto
    public boolean isOpen(){
        return this.open;
    }
    
    //Controlla se esiste l'oggetto inserito in input
    //effettua un controllo anche nella lista alias
    public boolean containsObject(String object){
        //controllo principale
        if(this.name.equals(object)){
            return true;
        }else{ //se il nome non è uguale, cerca negli alias
            if(this.containsAlias(object)){
                return true;
            }else{
                return false;
            }
        }
    }
    
    public boolean containsAlias(String alias){
        return this.alias.contains(alias);
    }

    @Override
    public boolean equals(Object obj){
        final GameObject object= (GameObject) obj;
        if(this.id == object.id){
            return true;
        }else{
            return false;
        }
    }
    
}
