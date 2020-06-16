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

    private final ArrayList<String> alias;
    
    //Tipo curatore
    private boolean curatore = false;
    
    //tipo usabile
    private boolean usabile = false;
    
    //Tipo equipaggiabile
    private boolean equipaggiabile = false;

    //indica se l'oggetto può essere raccolto
    private boolean pickable = true;

    //indica se l'oggetto può essere premuto
    private boolean pushable = false;

    //indica se l'oggetto è stato premuto
    private boolean push = false;
    
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
    
    //Imposta se l'oggetto è usabile
    public void setCuratore(){
        this.curatore = true;
    }
    
    /**
     * Imposta l'oggetto usabile.
     */
    public void setUsabile(){
        this.usabile = true;
    }
    
    /**
     * Indica se l'oggetto è
     * usabile o no
     * @return boolean 
     */
    public boolean getUsabile(){
        return this.usabile;
    }
    
    /**
     * Comunica se un oggetto è
     * di tipo curatore
     * @return boolean
     */
    public boolean getCuratore(){
        return this.curatore;
    }
    
    /**
     * Imposta l'oggetto equipaggiabile.
     */
    public void setEquipaggiabile(){
        this.equipaggiabile = true;
    }
    
    /**
     * Comunica se un oggetto 
     * è di tipo equipaggiabile
     * @return boolean 
     */
    public boolean getEquipaggiabile(){
        return this.equipaggiabile;
    }
    
    /**
     * Ritona il nome dell'oggetto
     * @return String
     */
    public String getNome(){
        return this.name.toString();
    }
    
    /**
     * Ritorna la descrizione dell'oggetto
     * @return 
     */
    public String getDescrizione(){
        return this.descrizione.toString();
    }
    /**
     * Setta l'oggetto pushable, 
     * può essere premuto.
     */
    public void setPushable(){
        this.pushable=true;
    }

    /**
     * restituisce vero o false 
     * se l'oggetto può essere premuto.
     */
    public boolean isPushable(){
        return this.pushable;
    }
    
    /**
     * restituisce vero o falso se l'oggetto è stato premuto.
     */
    public boolean isPushed(){
        return this.push;
    }
    
    /**
     * preme l'oggetto.
     */
    public void push(){
        this.push=true;
    }

    /**
     * Setta l'oggetto su pickable,
     * ovvero , può essere preso.
     */
    public void setPickable(){
        this.pickable=true;
    }
    
    /**
     * L'oggetto non può essere preso.
     */
    public void deletePickable(){
        this.pickable=false;
    }

    /**
     * Indica se l'oggetto può essere preso
     * o no
     * @return boolean 
     */
    public boolean isPickable(){
        return this.pickable;
    }
    
    /**
     * Controlla se esiste l'oggetto inserito in input
     * effettua un controllo anche nella lista alias
     * @param objectInput nome dell'oggetto in input
     * @return boolean
     */
    public boolean containsObject(String objectInput){
        //controllo principale
        if(this.name.toString().contains(objectInput)){
            return true;
        }else{ //se il nome non è uguale, cerca negli alias
            return this.containsAlias(objectInput);
        }
    }
    
    /**
     * Controlla se il nome dell'oggetto in input
     * è presente negli alias.
     * @param aliasInput nome oggetto in input
     * @return boolean
     */
    public boolean containsAlias(String aliasInput){
        return alias.stream().anyMatch((oggetto) -> (oggetto.contains(aliasInput)));
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
