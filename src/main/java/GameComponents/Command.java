package GameComponents;

import java.util.ArrayList;
/**
 *
 * @author Moresi Gianmarco
 */
public final class Command implements Input{
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
    public Command(String lineaInput){
        this.nome=new StringBuilder();
        this.description=new StringBuilder();
        this.alias = new ArrayList<>();
        acquisizoneInputFile(lineaInput);
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
        if(this.nome.toString().contains(command)){
            return true;
        }else{ //Se non esiste, controlla la lista degli alias
            return this.containsAlias(command);
        }
    }
    
    private boolean containsAlias(String alias){
        return this.alias.contains(alias);
    }

    @Override
    public void acquisizoneInputFile(String lineaInput) {
        //divide la parola in input in tokens
        String[] tokens= lineaInput.split("\\s+");
        int index = 0;
        
        //Acquisizione nome comando.
        while (!tokens[index].equals(".")) {
            nome.append(tokens[index]);
            index++;
        }
        index++;

        //Acquisizione descrizione comando
        while (!tokens[index].equals(".")) {
            description.append(tokens[index]).append(" ");
            index++;
        }
        index++;
        
        //Inserimento degli alias
        while (!tokens[index].equals(".")) {
            alias.add(tokens[index]);
            index++;
        }
    }
}
