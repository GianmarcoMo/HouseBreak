package GameComponents;

import java.util.ArrayList;
import java.util.Objects;
/**
 *
 * @author Moresi Gianmarco
 */
public class Command {
    //Indica il nome del comando
    private final String name;
    //Indica la descrizione, utile per fornire la responsabilità del comando
    private final String description;
    //Indica gli alias del comando
    private ArrayList<String> alias;
    
    //Costruttore
    public Command(String nome, String descrizione, String[] alias){
        this.name=nome;
        this.description=descrizione;
        this.setAlias(alias); //richiama il meotod implementato giù per inserire gli alias
    }
    
    //Inserisce tutti gli alias passati in input
    public void setAlias(String[] alias){
        for(int k=0; k<alias.length; k++){
            this.alias.add(k,alias[k]);
            
        }
    }
    
    public String getNameCommand(){
        return this.name;
    }
    
    public String getDescriptionCommand(){
        return this.description;
    }
    
    public ArrayList<String> getAlias(){
        return this.alias;
    }
    
    //Controlla se il comando inserito è uguale
    //controlla anche la lista degli alias
    public boolean containsCommand(String command){
        //Controllo principale
        if(this.name.equals(command)){
            return true;
        }else{ //Se non esiste, controlla la lista degli alias
            if(this.containsAlias(command)){
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
