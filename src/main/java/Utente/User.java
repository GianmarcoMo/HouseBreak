package Utente;

import GameComponents.Inventory;

public class User {
    private String username;
    private String email;
    private int vita;
    private boolean bloccato;
    private final Inventory inventario;
    
    public User(){
        username=null;
        email=null;
        vita=100;
        bloccato=false;
        inventario=new Inventory();
    }
    
    public void setUsername(String nome){
        this.username=nome;
    }
    public void setEmail(String email){
        this.email=email;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public String getEmail(){
        return this.email;
    }

    public Inventory getInvetario(){
        return this.inventario;
    }

    //controlla se il giocatore è bloccato
    public boolean bloccato(){
        if(this.bloccato) {
            System.out.println("Sei bloccato! Cerca il modo di liberarti!");
            return this.bloccato;
        }else{
            return this.bloccato;
        }
    }

    //blocca il giocatore, di solito avviene dopo un evento nel gioco
    public void bloccaGiocatore(){
        this.bloccato=true;
    }

    //sblocca il giocatore
    public void sbloccaGiocatore(){
        this.bloccato=false;
    }

    public int getVita(){
        return this.vita;
    }

    public void aumentaVita(int valore){
        this.vita+=valore;
        if(this.vita > 100){
            vita=100;
        }
    }

    public void diminuisciVita(int valore){
        this.vita-=valore;
    }

}