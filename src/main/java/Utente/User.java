package Utente;

import GameComponents.Inventory;
import GameComponents.Weapon;
import GameComponents.Bussola;

public class User {
    private String username;
    private String email;
    private int vita;
    private boolean bloccato;
    private String stato = "amico";
    private final Inventory inventario;
    private Weapon armaEquipaggiata;
    private final Bussola bussolaUtente = new Bussola();
    
    
    public User(){
        username=null;
        email=null;
        vita=100;
        bloccato=false;
        inventario=new Inventory();
    }
    
    public String getStato(){
        return this.stato;
    }
    
    public void setStatoAmichevole(){
        this.stato = "amico";
    }
    
    public void setStatoNemico(){
        this.stato = "nemico";
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
    
    public void setArmaEquipaggiata(Weapon armaInput){
        this.armaEquipaggiata = armaInput;
    }
    
    public void lasciaArmaEquipaggiata(){
        System.out.println("Hai lasciato l'arma.");
        this.armaEquipaggiata = null;
    }
    
    public Weapon getArmaEquipaggiata(){
        return this.armaEquipaggiata;
    }

    public Inventory getInvetario(){
        return this.inventario;
    }

    //controlla se il giocatore è bloccato
    public boolean bloccato(){
        if(this.bloccato) {
            
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
        this.vita-= Math.random() * valore;
        if(this.vita < 0){
            this.vita = 0;
        }
    }
    
    /**
    * Attacca un bersaglio
    * @param bersaglio - nemico da colpire
    */
    public void attaccaConArma(User bersaglio) {
        if(bersaglio.getVita() > 0){
            //diminuisce la vita e le munizioni dell'arma
            getArmaEquipaggiata().attacca();
            //Diminuisce la vita di un numero random tra 0 e il danno massimo dell'arma
            bersaglio.diminuisciVita(getArmaEquipaggiata().getDanno());
        }else{
            System.out.println("Il nemico è morto!");
        }
    }
    
    /**
     * Attacca il nemico con i pugni
     * @param bersaglio bersaglio da attaccare
     */
    public void attaccaConPugni(User bersaglio){
        if(bersaglio.getVita() > 0){
            bersaglio.diminuisciVita(10);
        }else{
            System.out.println("Il nemico è morto!");
        }
    }
    
        
    public Bussola getBussola(){
        return bussolaUtente;
    }

}