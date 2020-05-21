package GameComponents;

import java.util.ArrayList;
import Utils.EditorParola;
/**
 *
 * @author Moresi Gianmarco
 */
public class GameObject implements Input {
    //identifica un modo univoco l'oggetto
    private static int ID;

    private StringBuilder name;

    private StringBuilder descrizione;
    
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
    public boolean containsObject(String objectInput){
        //controllo principale
        if(this.name.toString().equals(objectInput)){
            return true;
        }else{ //se il nome non è uguale, cerca negli alias
            return this.containsAlias(objectInput);
        }
    }
    
    public boolean containsAlias(String aliasInput){
        return alias.stream().anyMatch((oggetto) -> (oggetto.equals(aliasInput)));
    }

    @Override
    public void acquisizoneInputFile(String lineaInput) {
        //edito che permette di eliminare il trailing space
        EditorParola editor= new EditorParola();
        //divide la parola in input in tokens
        String[] tokens= lineaInput.split("\\s+");
        int index=0;
        //Acquisizione nome oggetto
        while(!tokens[index].equals(".")){
            this.name.append(tokens[index]).append(" ");
            index++;
        }
        //elimino il trailing space
        this.name = editor.rimozzioneTrailingSpace(this.name);

        //Salto carattere per dividere argomenti
        index++;

        while(!tokens[index].equals(".")){
            this.descrizione.append(tokens[index]).append(" ");
            index++;
        }
        this.descrizione= editor.rimozzioneTrailingSpace(this.descrizione);
        index++;

        //Acquisizione dei vari alias
        while(!tokens[index].equals(".")){
            this.alias.add(tokens[index]);
            index++;
        }
    }
}
