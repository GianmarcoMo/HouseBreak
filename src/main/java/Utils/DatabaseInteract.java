package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Utente.User;

/**
 *
 * @author Moresi Gianmarco
 */
public class DatabaseInteract {
    public Boolean utenteEsistente(String emailInput, String usernameInput) throws SQLException{
        Boolean risultatoB= false;
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://housebreak-db.cafdhyoaqv4t.eu-west-2.rds.amazonaws.com:3306/HouseBreak","admin", "housebreak");
            ResultSet risultato;
            try (PreparedStatement queryDati = conn.prepareStatement("SELECT count(email) FROM Utente WHERE email='"+emailInput+"' "
                    + "or username='"+ usernameInput+"'")) {
                risultato = queryDati.executeQuery();
                while(risultato.next()){
                    if(risultato.getInt(1)==1){
                        risultatoB=true;
                    }
                }
                conn.close();
                risultato.close();
                queryDati.close();
            }
            return risultatoB;
        }catch( SQLException ex){
            System.out.println(ex);
        }
        return false;
    }
    
    public String convertiPassword(char[] passwordInput){
        StringBuilder password = new StringBuilder();
        
        for (int i =0; i < passwordInput.length ; i++){
            password.append(passwordInput[i]);
        }
        return password.toString();
    }

    public Boolean controlloDatiLogin(String email, char[] passwordInput, User giocatoreLogin) throws SQLException{
        Boolean risultatoB = false;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://housebreak-db.cafdhyoaqv4t.eu-west-2.rds.amazonaws.com:3306/HouseBreak","admin", "housebreak");
            ResultSet risultato;
            try(PreparedStatement userDate = conn.prepareStatement("SELECT username FROM Utente WHERE email='"
                    +email+"' AND password='"+convertiPassword(passwordInput)+"';")){
                risultato = userDate.executeQuery();
                while(risultato.next()){
                    if(!risultato.getString(1).equals("")){
                        giocatoreLogin.setEmail(email);
                        giocatoreLogin.setUsername(risultato.getString(1));
                        risultatoB=true;
                    }
                }
                risultato.close();
                conn.close();
                userDate.close();
            }
            return risultatoB;
        } catch (SQLException ex) {
            System.out.println("Nessuna connessione al database");
        }
        return false;
    }
}
    