package GameComponents;
import java.lang.String;

/**
 *
 * @author Moresi Gianmarco
 */
public class Weapon extends GameObject{
    private int munizioni;
    private int vita;
    private int danno;
    private GameObject tipoMunizioni = null;
    
    public Weapon(){
        this.munizioni = 0;
        this.vita = 0;
        this.danno = 0;
        this.tipoMunizioni = null;
    }
    
    public Weapon(String lineaInput, GameObject munizioni){
        this.munizioni=0;
        this.vita=100;
        this.danno= 0;
        this.tipoMunizioni = munizioni;
        acquisizoneInputFile(lineaInput);
    }
    
    /**
     * Imposta il danno dell'arma
     * @param dannoInput - danno inserito in input
     */
    public void setDanno(int dannoInput){
        this.danno = dannoInput;
    }

    public void aumentaMunizioni(int numeroMunizioni){
        this.munizioni+=numeroMunizioni;
    }

    public void attacca(){
        if(this.getVita() > 0 && this.munizioni > 0){
            //diminuisce le munizioni, se esistono
            this.munizioni-=1;
            //diminuisce la vita
            this.diminuisciVita();
        }else{
            System.out.println("Arma rotta! Buttala! Occupa solo spazio!");
        }
    }
    
    public int getMunizioni(){
        //Se il tipo munizioni è diverso da null
        //Allora restituisci le munizioni
        if(this.tipoMunizioni != null){
            return this.munizioni;
        }else{
        //se non esiste il tipo munizioni
        //l'arma non può avere delle munizioni
        //esempio mazza, padella ecc...
            nonHaMunizioni();
            return 0;
        }
    }

    //messaggio che avvisa che l'arma non ha munizioni
    private void nonHaMunizioni(){
        System.out.println("Quest'arma non ha munizioni");
    }

    //diminuisce la vita dell'arma
    private void diminuisciVita(){
        //la vita diminuirà di un numero casuale 
        //ogni volta che viene usata per sparare/colpire
        this.vita-= Math.random()*10;
        if(this.vita <= 0){
            System.out.println("Arma rotta!");
        }
    }

    private int getVita(){
        if(vita == 0){
            System.out.println("Arma rotta! Inutilizzabile!");
            return 0;
        }
        if(vita > 50){
            return this.vita;
        }else{ 
            vitaArmaRischio();
            return this.vita;
        }
    }

    //messaggio che avvisa che l'arma si trova in pessime condizioni
    private void vitaArmaRischio(){
        System.out.println("Attento! La tua arma non durerà molto!");
        System.out.println("Ti conviene cambiarla e buttare sto cesso a terra!");
    }

    public int getDanno(){
        return this.danno;
    }

    public GameObject getTipoMunizioni(){
        return this.tipoMunizioni;
    }
}