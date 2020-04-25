package GameComponents;

import java.util.ArrayList;

/**
 *
 * @author Moresi Gianmarco
 */
public class Object {
    private final String name;
    private final String descrizione;
    private ArrayList<String> alias;
    
    public Object(String nome, String descrizione, String[] alias){
        this.name=nome;
        this.descrizione=descrizione;
        this.setAlias(alias);
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
    
}
