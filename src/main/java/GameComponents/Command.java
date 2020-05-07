package GameComponents;

import java.util.ArrayList;
/**
 *
 * @author Moresi Gianmarco
 */
public class Command {
    //Indica il nome del comando
    private final StringBuilder nome;
    //Indica la descrizione, utile per fornire la responsabilità del comando
    private final StringBuilder description;
    //Indica gli alias del comando
    private final ArrayList<String> alias;
    
    //Costruttore
    public Command(){
        this.nome=new StringBuilder();
        this.description=new StringBuilder();
        this.alias = new ArrayList<>();
    }
    
    //Costruttore con input linea del file
    public Command(String line){
        this.nome=new StringBuilder();
        this.description=new StringBuilder();
        this.alias = new ArrayList<>();
        
        int index=0;
        //Acquisizione nome comando.
        while(line.charAt(index)!='.'){
            nome.append(line.charAt(index));
            index++;
        }
        index++;
        
        //Acquisizione descrizione comando
        while(line.charAt(index)!='.'){
            description.append(line.charAt(index));
            index++;
        }
        index++;
        //Acquisizione dei vari alias
        StringBuilder singoloAlias=new StringBuilder();
        
        while(line.charAt(index)!='.'){
            if(line.charAt(index)==','){
                alias.add(alias.size(), singoloAlias.toString());
                singoloAlias.delete(0, singoloAlias.length());
            }else{
                singoloAlias.append(line.charAt(index));
            }
            index++;
        }
    }

    public void setNomeComando(String nomeInput){
        this.nome.append(nomeInput);
    }

    public void setAlias(String aliasInput){
        this.alias.add(alias.size(), aliasInput);
    }

    public void setDescrizione(String descrizioneInput){
        this.description.append(descrizioneInput);
    }
    
    public StringBuilder getNomeComando(){
        return this.nome;
    }
    
    public StringBuilder getDescrizioneComando(){
        return this.description;
    }
    
    public ArrayList<String> getAlias(){
        return this.alias;
    }
    
    //Controlla se il comando inserito è uguale
    //controlla anche la lista degli alias
    public boolean containsCommand(String command){
        //Controllo principale
        if(this.nome.toString().equals(command)){
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
