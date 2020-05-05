package GameComponents;

import java.util.ArrayList;
/**
 *
 * @author Moresi Gianmarco
 */
public class Command {
    //Indica il nome del comando
    private StringBuilder nome;
    //Indica la descrizione, utile per fornire la responsabilità del comando
    private StringBuilder description;
    //Indica gli alias del comando
    private ArrayList<String> alias;
    
    //Costruttore
    public Command(){
        this.nome=new StringBuilder();
        this.description=new StringBuilder();
        this.alias = new ArrayList<>();
    }
    
    //Costruttore con stringa
    public Command(String linea){
        this.nome=new StringBuilder();
        this.description=new StringBuilder();
        this.alias = new ArrayList<>();
        dividiAcqisizione(linea);
    }

    public void setNomeComando(char carattere){
        this.nome.append(carattere);
    }

    public void setAlias(String aliasInput){
        this.alias.add(alias.size(), aliasInput);
    }

    public void setDescrizione(char carattere){
        this.description.append(carattere);
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
        if(this.nome.equals(command)){
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
    
    //nord Direzione per il giocatore. n,N,Nord,NORD,avanti,Avanti,AVANTI,
    public void dividiAcqisizione(String line){
        int index=0;
        //Acquisizione nome comando.
        while(line.charAt(index)!='.'){
            this.setNomeComando(line.charAt(index));
            index++;
        }
        index++;
        
        //Acquisizione descrizione comando
        while(line.charAt(index)!='.'){
            this.setDescrizione(line.charAt(index));
            index++;
        }
        index++;
        //Acquisizione dei vari alias
        StringBuilder singoloAlias=new StringBuilder();
        
        while(line.charAt(index)!='.'){
            if(line.charAt(index)==','){
                this.setAlias(singoloAlias.toString());
                singoloAlias.delete(0, singoloAlias.length());
            }else{
                singoloAlias.append(line.charAt(index));
            }
            index++;
        }
        
    }
   
}
