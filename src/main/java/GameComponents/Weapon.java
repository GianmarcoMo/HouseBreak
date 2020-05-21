package GameComponents;

import java.util.Scanner;

/**
 *
 * @author Moresi Gianmarco
 */
public class Weapon implements Input {
    private final StringBuilder nome;
    private int munizioni;
    private int vita;
    private final int danno;
    private final StringBuilder tipoMunizioni;

    public Weapon(){
        this.nome= new StringBuilder();
        this.munizioni=0;
        this.vita=(int) (Math.random() * 100);
        this.danno=(int) (Math.random() * 90);
        this.tipoMunizioni= new StringBuilder();
    }

    public Weapon(String lineaInput){
        this.nome= new StringBuilder();
        this.munizioni=0;
        this.vita=(int) (Math.random() * 100);
        this.danno=(int) (Math.random() * 90);
        this.tipoMunizioni= new StringBuilder();
        acquisizoneInputFile(lineaInput);
    }

    public void aumentaMunizioni(int numeroMunizioni){
        this.munizioni+=numeroMunizioni;
    }

    public void attacca(int numeroAttacchi){
        while(numeroAttacchi!=0 && vita>0){
            //diminuisce le munizioni, se esistono
            this.munizioni-=1;
            //diminuisce il numero di attacchi
            numeroAttacchi-=1;
            //diminuisce la vita
            this.diminuisciVita();
        }
    }

    public String getNomeArma(){
        return this.nome.toString();
    }
    
    public int getMunizioni(){
        //Se il tipo munizioni è diverso da null
        //Allora restituisci le munizioni
        if(!"".equals(this.tipoMunizioni.toString())){
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
    public void diminuisciVita(){
        //la vita diminuirà di un numero casuale 
        //ogni volta che viene usata per sparare/colpire
        this.vita-= Math.random()*15;
    }

    public int getVita(){
        if(vita>50){
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

    public String tipoMunizioni(){
        return this.tipoMunizioni.toString();
    }

    public boolean munizioniCompatibili(String munizioni){
        if(this.tipoMunizioni.toString().equals(munizioni)){
            return true;
        }else{
            munizioniNonCompatibili();
            return false;
        }
    }

    private void munizioniNonCompatibili(){
        System.out.println("Le munizioni non sono compatibili, trova altre munizioni. ");
        System.out.println("Oppure usa l'arma come una mazza");
        System.out.println("Basta scrivere 'usa arma mani' ");
        troll();
        
    }

    private void troll(){
        Scanner scann= new Scanner(System.in);
        String troll= scann.nextLine();
        System.out.println(("AHAHAHA ti prendevo in giro, non esiste nessun comando"));
    }

    @Override
    public final void acquisizoneInputFile(String lineaInput) throws NumberFormatException{
        int index=0;
        //Serve per prendere gli interi dal file.
        String numeroFile= new String();
        //divide la parola in input in tokens
        String[] tokens= lineaInput.split("\\s+");
        
        //Nome arma
        while(!tokens[index].equals(".")){
            this.nome.append(tokens[index]);
            index++;
        }
        index++;
        
        //Tipo Munizioni arma
        while(!tokens[index].equals(".")){
            this.tipoMunizioni.append(tokens[index]);
            index++;
        }
        index++;
    }
    
    public boolean containsArma(String nomeInput){
        return this.nome.toString().contains(nomeInput);
    }
}