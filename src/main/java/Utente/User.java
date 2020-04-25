package Utente;

public class User {
    private String username;
    private String email;
    
    public User(){
        username=null;
        email=null;
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

}