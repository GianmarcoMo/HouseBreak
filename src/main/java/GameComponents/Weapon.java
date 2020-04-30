package GameComponents;

import java.util.Scanner;

public class Weapon extends GameObject {
    private int munizioni;
    private int vita;
    private int danno;
    private String tipoMunizioni;

    public Weapon(){
        this.munizioni=0;
        this.vita=0;
        this.danno=0;
    }

    public Weapon(int munizioni, int vita, int danno){
        this.munizioni=munizioni;
        this.vita=vita;
        this.danno=danno;
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

    public int getMunizioni(){
        //Se il tipo munizioni è diverso da null
        //Allora restituisci le munizioni
        if(this.tipoMunizioni != null)
            return this.munizioni;
        else
        //se non esiste il tipo munizioni
        //l'arma non può avere delle munizioni
        //esempio mazza, padella ecc...
            nonHaMunizioni();
            return 0;
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
        if(vita>50)
            return this.vita;
        else 
            vitaArmaRischio();
            return this.vita;
            
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
        return this.tipoMunizioni;
    }

    public boolean munizioniCompatibili(String munizioni){
        if(this.tipoMunizioni == munizioni)
            return true;
        else
            munizioniNonCompatibili();
            return false;

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

}