package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Game.HouseBreak;
import GameComponents.GameObject;
import GameComponents.Room;
import GameComponents.Weapon;
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

    public Boolean controlloDatiLogin(String email, char[] passwordInput, User giocatoreLogin) throws SQLException, ClassNotFoundException{
        Boolean risultatoB = false;
        try {
            //Class.forName("com.mysql.jdbc.Driver");
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
            System.out.println(ex);
        }
        return false;
    }

    public boolean inserimentoUtente(String inputEmail, char[] inputPassword, String inputUsername) throws SQLException{
        DatabaseInteract databaseManager = new DatabaseInteract();
        try {
            if(!databaseManager.utenteEsistente(inputEmail ,inputUsername)){
                Connection conn = DriverManager.getConnection("jdbc:mysql://housebreak-db.cafdhyoaqv4t.eu-west-2.rds.amazonaws.com:3306/HouseBreak","admin", "housebreak");
                try (PreparedStatement userDate = conn.prepareStatement("INSERT INTO Utente VALUES (?, ?, ?)")) {
                    userDate.setString(1, inputEmail);
                    userDate.setString(2, inputUsername);
                    userDate.setString(3, databaseManager.convertiPassword(inputPassword));
                    userDate.executeUpdate();
                    userDate.close();
                    conn.close();
                    return true;
                }
            }else{
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    public void initInventarioNemico(int idSalvataggioInput, HouseBreak game) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://housebreak-db.cafdhyoaqv4t.eu-west-2.rds.amazonaws.com:3306/HouseBreak","admin", "housebreak")) {
            PreparedStatement statsUtente;
            ResultSet risultatoStats;
            statsUtente = conn.prepareStatement("SELECT su.stanzaCorrente, su.codInventario FROM StatsUtente su, Salvataggio s, OggettoInventario o WHERE s.codSalvataggio = ? AND su.codSalvataggio = s.codSalvataggio AND su.codStats != s.codStatsUtente and o.codInventario = su.codInventario");
            statsUtente.setInt(1, idSalvataggioInput);
            risultatoStats = statsUtente.executeQuery();
            
            Room stanzaCorrenteNemico;
            while(risultatoStats.next()){
                stanzaCorrenteNemico = cercaStanza(risultatoStats.getString(1), game);

                stanzaCorrenteNemico.getNemico().getInvetario().addObject(cercaOggetto(risultatoStats.getString(2), game));
            }
            
            risultatoStats.close();
            statsUtente.close();
            conn.close();
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }

    public void initNemici(int idSalvataggioInput, HouseBreak game) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://housebreak-db.cafdhyoaqv4t.eu-west-2.rds.amazonaws.com:3306/HouseBreak","admin", "housebreak")) {
            PreparedStatement statsUtente;
            ResultSet risultatoStats;
            statsUtente = conn.prepareStatement("SELECT su.vita, su.armaEquipaggiata, su.bloccato, su.numeroMunizioni, su.stanzaCorrente FROM StatsUtente su, Salvataggio s WHERE s.codSalvataggio = ? AND su.codSalvataggio = s.codSalvataggio AND su.codStats != s.codStatsUtente");
            statsUtente.setInt(1, idSalvataggioInput);
            risultatoStats = statsUtente.executeQuery();
            
            Room stanzaCorrenteNemico;
            while(risultatoStats.next()){
                stanzaCorrenteNemico = cercaStanza(risultatoStats.getString(5), game);

                stanzaCorrenteNemico.getNemico().setVita(risultatoStats.getInt(1));
                stanzaCorrenteNemico.getNemico().setArmaEquipaggiata((Weapon)cercaOggetto(risultatoStats.getString(2), game));
                stanzaCorrenteNemico.getNemico().getArmaEquipaggiata().setMunizioni(risultatoStats.getInt(4));
            }
            
            risultatoStats.close();
            statsUtente.close();
            conn.close();
        }catch (SQLException ex){
            System.out.println(ex);
        }

    }

    public void initStanzaCorrente(int idSalvataggio, HouseBreak game) throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://housebreak-db.cafdhyoaqv4t.eu-west-2.rds.amazonaws.com:3306/HouseBreak","admin", "housebreak")) {
            PreparedStatement stanzaCorrente;
            ResultSet nomeStanza;
            stanzaCorrente = conn.prepareStatement("SELECT su.stanzaCorrente FROM Salvataggio s, StatsUtente su WHERE s.codSalvataggio = ? AND s.codStatsUtente= su.codStats");
            stanzaCorrente.setInt(1, idSalvataggio);
            nomeStanza = stanzaCorrente.executeQuery();
            
            if(nomeStanza.next()){
                for (Room stanza : game.getRoom()) {
                    if(stanza.equals(nomeStanza.getString(1))){
                        game.setStanzaCorrente(stanza);
                        break;
                    }
                }
            }
            
            nomeStanza.close();
            stanzaCorrente.close();
            conn.close();
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }
    
    public void initInventarioUtente(int idSalvataggio, HouseBreak game)throws  SQLException{
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://housebreak-db.cafdhyoaqv4t.eu-west-2.rds.amazonaws.com:3306/HouseBreak","admin", "housebreak")) {
            PreparedStatement oggettiInventario;
            ResultSet oggetti;
            oggettiInventario = conn.prepareStatement("SELECT o.nomeOggetto FROM Salvataggio s, StatsUtente su, Inventario i, OggettoInventario o WHERE s.codSalvataggio = ? AND s.codStatsUtente=su.codStats AND su.codInventario=i.codInventario ANd o.codInventario=i.codInventario");
            oggettiInventario.setInt(1, idSalvataggio);
            oggetti = oggettiInventario.executeQuery();
            
            //Per ogni risultato, cerca l'oggetto, lo restituisce e lo inserisce nell'inventario dell'utente.
            while(oggetti.next()){
                game.getUser().getInvetario().addObject(cercaOggetto(oggetti.getString(1), game));
            }
            
            
            oggetti.close();
            oggettiInventario.close();
            conn.close();
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }
    
    public void initStanze(int idSalvataggio, HouseBreak game)throws SQLException{
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://housebreak-db.cafdhyoaqv4t.eu-west-2.rds.amazonaws.com:3306/HouseBreak","admin", "housebreak")) {
            PreparedStatement stanze;
            ResultSet risultatoStanze;
            stanze = conn.prepareStatement("SELECT s.nomeStanza, o.nomeOggetto, s.bloccata FROM Stanza s, Inventario i, OggettoInventario o WHERE s.codSalvataggio= ? AND s.codInventario = i.codInventario AND o.codInventario=i.codInventario");
            stanze.setInt(1,  idSalvataggio);
            risultatoStanze = stanze.executeQuery();
            
            Room stanzaSelezionata;
            while(risultatoStanze.next()){
                stanzaSelezionata = cercaStanza(risultatoStanze.getString(1), game);
                stanzaSelezionata.getObject().add(cercaOggetto(risultatoStanze.getString(2), game));
                if(risultatoStanze.getInt(3)==1){
                    stanzaSelezionata.bloccaStanza();
                }
            }
            
            risultatoStanze.close();
            stanze.close();
            conn.close();
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }
    
    public void initUtente(int idSalvataggio, HouseBreak game){
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://housebreak-db.cafdhyoaqv4t.eu-west-2.rds.amazonaws.com:3306/HouseBreak","admin", "housebreak")) {
            PreparedStatement statsUtente;
            ResultSet risultatoStats;
            statsUtente = conn.prepareStatement("SELECT su.vita, su.armaEquipaggiata, su.bloccato, su.numeroMunizioni, b.avanti, b.sinistra, b.destra, b.giu FROM Salvataggio s, StatsUtente su, Bussola b WHERE s.codSalvataggio=? AND s.codSalvataggio=su.codSalvataggio AND s.codStatsUtente=su.codStats AND su.codBussola=b.codBussola");
            statsUtente.setInt(1, idSalvataggio);
            risultatoStats = statsUtente.executeQuery();
            
            if(risultatoStats.next()){
                //Inserimento vita
                game.getUser().setVita(risultatoStats.getInt(1));
                //Inserimento arma equipaggiata
                game.getUser().setArmaEquipaggiata((Weapon)cercaOggetto(risultatoStats.getString(2), game));
                //Inserimento bloccato/sbloccato
                if(risultatoStats.getInt(3)==1)
                    game.getUser().bloccaGiocatore();
                else
                    game.getUser().sbloccaGiocatore();
                if(game.getUser().getArmaEquipaggiata()!=null){
                    //inserimento numero munizioni
                    game.getUser().getArmaEquipaggiata().setMunizioni(risultatoStats.getInt(4));
                }
                
                //Inserimento bussola
                game.getUser().getBussola().setAvanti(risultatoStats.getString(5));
                game.getUser().getBussola().setSinistra(risultatoStats.getString(6));
                game.getUser().getBussola().setDestra(risultatoStats.getString(7));
                game.getUser().getBussola().setGiu(risultatoStats.getString(8));
            }
            
            risultatoStats.close();
            statsUtente.close();
            conn.close();
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }
    
    private Room cercaStanza(String nomeStanza, HouseBreak game){
        int id=0;
        for(Room stanza : game.getRoom()){
            if(stanza.getNomeStanza().toString().equals(nomeStanza)){
                return game.getRoom().get(id);
            }
            id++;
        }
        return null;
    }
    
    private GameObject cercaOggetto(String nomeOggetto, HouseBreak game){
        int id=0;
        for(GameObject oggetto : game.getObject()){
            if(oggetto.getNome().equals(nomeOggetto)){
                return  game.getObject().get(id);
            }
            id++;
        }
        return null;
    }
}
    