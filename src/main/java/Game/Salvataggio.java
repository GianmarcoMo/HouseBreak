/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;
import GameComponents.GameObject;
import Utente.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;


/**
 *
 * @author Moresi Gianmarco
 */
public class Salvataggio {
    HouseBreak giocoCorrente;
    private int idSalvataggio = 0;
    
    public Salvataggio(HouseBreak gioco, int idSalvataggioInput){
        this.giocoCorrente = gioco;
        this.idSalvataggio = idSalvataggioInput;
    }

    public int getIdSalvataggio(){
        return this.idSalvataggio;
    }
    
    public Boolean primoSalvataggio(){
        return this.idSalvataggio==0;
    }
    
    public void salvaPartita() throws SQLException{
        if(primoSalvataggio()){
            inserimentoPartita();
        }else{
            cancellaVecchioSalvataggio();

            //EFFETTUA INSERIMENTO DI TUTTO
            inserimentoPartita();
        }
    }
    
    private void inserimentoInventario(Connection conn, int idInventario, int codSalvataggio, ArrayList<GameObject> inventario) throws SQLException{
        PreparedStatement inserimentoOggetto;
        //inserimento codice inventario
        inserimentoOggetto = conn.prepareStatement("INSERT INTO Inventario VALUES (?, ?)");
        if(idInventario == 0){
            inserimentoOggetto.setNull(1, java.sql.Types.INTEGER);
        }else{
            inserimentoOggetto.setInt(1, idInventario);
        }
        inserimentoOggetto.setInt(2, idSalvataggio);
        inserimentoOggetto.executeUpdate();
        inserimentoOggetto.close();

        //inserimento oggetti inventario
        for(int i=0; i< inventario.size(); i++){
            //inserimento oggetto inventario
            inserimentoOggetto = conn.prepareStatement("INSERT INTO OggettoInventario VALUES (?,?, ?, ?, ?, ?, ?, ?)");
            inserimentoOggetto.setInt(1, idInventario);
            inserimentoOggetto.setString(2, inventario.get(i).getNome());
            //IMPOSTA SE L'OGGETTO È CURATORE
            if(inventario.get(i).getCuratore()){
                inserimentoOggetto.setInt(3, 1);
            }else{
                inserimentoOggetto.setInt(3, 0);
            }
            
            //IMPOSTA SE L'OGGETTO È USABILE
            if(inventario.get(i).getUsabile()){
                inserimentoOggetto.setInt(4, 1);
            }else{
                inserimentoOggetto.setInt(4, 0);
            }
            
            //IMPOSTA SE  L'OGGETTO È EQUIPAGGIABILE
            if(inventario.get(i).getEquipaggiabile()){
                inserimentoOggetto.setInt(5, 1);
            }else{
                inserimentoOggetto.setInt(5, 0);
            }
            
            //imposta se l'oggetto può essere preso
            if(inventario.get(i).isPickable()){
                inserimentoOggetto.setInt(6, 1);
            }else{
                inserimentoOggetto.setInt(6, 0);
            }
            
            //imposta se l'oggetto è premibile
            if(inventario.get(i).isPushable()){
                inserimentoOggetto.setInt(7, 1);
            }else{
                inserimentoOggetto.setInt(7, 0);
            }
            
            //imposta se l'oggetto è stato premuto
            if(inventario.get(i).isPushed()){
                inserimentoOggetto.setInt(8, 1);
            }else{
                inserimentoOggetto.setInt(8, 0);
            }
            
            inserimentoOggetto.executeUpdate();
            //chiusura
            inserimentoOggetto.close();
        }
    }
    
    private void inserimentoStats(Connection conn, int idStats, int idInventario, int idBussola, User utente, String stanzaCorrente, int idSalvataggio) throws SQLException{
        //Inserimento dati utente (vita ecc...)
        PreparedStatement inserimentoDatiUtente;
        inserimentoDatiUtente = conn.prepareStatement("INSERT INTO StatsUtente VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
        //inserimento id
        inserimentoDatiUtente.setInt(1, idStats);
        //inserimento vita
        inserimentoDatiUtente.setInt(2, utente.getVita());
        //inserimento inventario
        if(idInventario==0){
            inserimentoDatiUtente.setNull(3, java.sql.Types.INTEGER);
        }else{
            inserimentoDatiUtente.setInt(3, idInventario);
        }
        
        //Inserimento arma equipaggiata
        if(utente.getArmaEquipaggiata()!= null){
            inserimentoDatiUtente.setString(4, utente.getArmaEquipaggiata().getNome());
            inserimentoDatiUtente.setInt(6, utente.getArmaEquipaggiata().getMunizioni());
        }else{
            inserimentoDatiUtente.setString(4, null);
            inserimentoDatiUtente.setInt(6, 0);
        }

        //Inserimento utente bloccato
        //Se è bloccato, 1
        if(utente.bloccato()){
            inserimentoDatiUtente.setInt(5, 1);
            //sennò 0
        }else{
            inserimentoDatiUtente.setInt(5, 0);
        }
        
        if(idBussola==0){
            inserimentoDatiUtente.setNull(7, java.sql.Types.INTEGER);
        }else{
            inserimentoDatiUtente.setInt(7, idBussola);
        }
        
        inserimentoDatiUtente.setString(8, stanzaCorrente);
        inserimentoDatiUtente.setInt(9, idSalvataggio);
        inserimentoDatiUtente.executeUpdate();
        inserimentoDatiUtente.close();
    }
    
    private void inserimentoSalvataggio(Connection conn, int idStats, int codSalvataggio) throws SQLException{
        //INSERIMENTO DATI DEL SALVATAGGIO
        PreparedStatement inserimentoDatiSalvataggio;
        java.sql.Date sqlGiorno = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        inserimentoDatiSalvataggio = conn.prepareStatement("INSERT INTO Salvataggio VALUES (?, ?, ?, ?)");
        //inserimento cod salvataggio
        inserimentoDatiSalvataggio.setInt(1, codSalvataggio);
        //inserimento email utente
        inserimentoDatiSalvataggio.setString(2, this.giocoCorrente.getUser().getEmail());
        //inserimento id stats utente
        if(idStats == 0){
            inserimentoDatiSalvataggio.setNull(3, java.sql.Types.INTEGER);
        }else{
            inserimentoDatiSalvataggio.setInt(3, idStats);
        }
        //inserimento data creazione salvataggio
        inserimentoDatiSalvataggio.setDate(4, sqlGiorno);
        inserimentoDatiSalvataggio.executeUpdate();
        inserimentoDatiSalvataggio.close();
        
    }
    
    private void inserimentoBussola(Connection conn, int idBussola) throws SQLException{
        PreparedStatement inserimentoBussola ;
        inserimentoBussola = conn.prepareStatement("INSERT INTO Bussola VALUES (?, ?, ?, ?, ?)");
        inserimentoBussola.setInt(1, idBussola);
        inserimentoBussola.setString(2, giocoCorrente.getUser().getBussola().getAvanti());
        inserimentoBussola.setString(3, giocoCorrente.getUser().getBussola().getSinistra());
        inserimentoBussola.setString(4, giocoCorrente.getUser().getBussola().getDestra());
        inserimentoBussola.setString(5, giocoCorrente.getUser().getBussola().getGiu());
        inserimentoBussola.executeUpdate();
        inserimentoBussola.close();
    }
    
    private void inserimentoStanze(int idSalvataggio) throws SQLException{
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://housebreak-db.cafdhyoaqv4t.eu-west-2.rds.amazonaws.com:3306/HouseBreak","admin", "housebreak")) {
            ArrayList<Integer> stanzaNemico = new ArrayList<>();
            
            //Cicla su tutte le stanze del gioco
            for(int i = 0; i< giocoCorrente.getRoom().size(); i++){
                //Aggiunge l'indice della stanza dove è presente il nemico
                if(giocoCorrente.getRoom().get(i).nemico()){
                    stanzaNemico.add(i);
                }
                
                int idInventarioStanza = 0;
                //INSERIMENTO INVENTARIO STANZA
                if(giocoCorrente.getRoom().get(i).getObject().size() >0){
                    idInventarioStanza = (int)(Math.random()*2000);
                    while(codiceEsistente("codInventario", "Inventario", "codInventario", idInventarioStanza)){
                        idInventarioStanza = (int)(Math.random()*2000);
                    }
                    //inserimento inventario
                    inserimentoInventario(conn, idInventarioStanza, this.idSalvataggio ,giocoCorrente.getRoom().get(i).getObject());
                }
                                
                PreparedStatement inserimentoStanza;
                inserimentoStanza = conn.prepareStatement("INSERT INTO Stanza VALUES (?, ?, ?, ?);");
                inserimentoStanza.setString(1, giocoCorrente.getRoom().get(i).getNomeStanza().toString());
                if(idInventarioStanza==0){
                    inserimentoStanza.setNull(2, java.sql.Types.INTEGER);
                }else{
                    inserimentoStanza.setInt(2, idInventarioStanza);
                }
                
                inserimentoStanza.setInt(3, idSalvataggio);
                if(giocoCorrente.getRoom().get(i).bloccata()){
                    inserimentoStanza.setInt(4, 1);
                }else{
                    inserimentoStanza.setInt(4, 0);
                }
                inserimentoStanza.executeUpdate();
                inserimentoStanza.close();
            }
            
            System.out.println("\b\b\b\b\b");
            System.out.print("Inserimento nemici...");
            // INSERIMENTO DATI NEMICI
            for(int k=0; k<stanzaNemico.size(); k++){
                int idInventarioNemico = 0;
                //generazione id stats nemico
                int idStatsNemico = 0;
                
                //Se il nemico ha qualcosa nell'inventario, crea l'inventario
                if(giocoCorrente.getRoom().get(stanzaNemico.get(k)).getNemico().getInvetario().getObjects().size()>0){
                    idInventarioNemico =(int)(Math.random()*2000);
                    while(codiceEsistente("codInventario", "Inventario", "codInventario", idInventarioNemico)){
                        idInventarioNemico = (int)(Math.random()*2000);
                    }

                    inserimentoInventario(conn, idInventarioNemico, this.idSalvataggio, giocoCorrente.getRoom().get(stanzaNemico.get(k)).getNemico().getInvetario().getObjects());
                }
                
                while(codiceEsistente("codStats", "StatsUtente", "codStats", idStatsNemico) && idStatsNemico==0){
                    idStatsNemico= (int)(Math.random()*2000);
                }
                inserimentoStats(conn, idStatsNemico, idInventarioNemico, 0, giocoCorrente.getRoom().get(stanzaNemico.get(k)).getNemico(), giocoCorrente.getRoom().get(stanzaNemico.get(k)).getNomeStanza().toString(), idSalvataggio);
            }
        }
    }
    
    private void inserimentoStatsSalvataggio(int codStats) throws SQLException{
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://housebreak-db.cafdhyoaqv4t.eu-west-2.rds.amazonaws.com:3306/HouseBreak","admin", "housebreak")){
            PreparedStatement updateSalvataggio ;
            updateSalvataggio = conn.prepareStatement("UPDATE Salvataggio SET codStatsUtente = ? WHERE codSalvataggio = ?;");
            updateSalvataggio.setInt(1, codStats);
            updateSalvataggio.setInt(2, this.idSalvataggio);
            updateSalvataggio.executeUpdate();
            updateSalvataggio.close();
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
    private void inserimentoPartita() throws SQLException{        
        System.out.print("Connessione al database...");
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://housebreak-db.cafdhyoaqv4t.eu-west-2.rds.amazonaws.com:3306/HouseBreak","admin", "housebreak")) {
            System.out.print("Ok\n");

            int codSalvataggio= (int) (Math.random()*2000);
            //genera un id inesistente
            while(codiceEsistente("codSalvataggio", "Salvataggio", "codSalvataggio", codSalvataggio)){
                codSalvataggio= (int) (Math.random()*2000);
            }
            this.idSalvataggio = codSalvataggio;
            System.out.println("\b\b\b\b\b");
            inserimentoSalvataggio(conn, 0, this.idSalvataggio);
            

            System.out.println("\b\b\b\b\b");
            System.out.print("Inserimento stanze...");
            //INSERIMENTO STANZE
            inserimentoStanze(this.idSalvataggio);
            
            int idInventario= (int) (Math.random()*2000);
            //genera un id inesistente
            while(codiceEsistente("codInventario", "Inventario", "codInventario", idInventario)){
                idInventario= (int) (Math.random()*2000);
            }
            System.out.println("\b\b\b\b\b");
            System.out.print("Inserimento dati inventario....");
            inserimentoInventario(conn, idInventario, this.idSalvataggio, giocoCorrente.getUser().getInvetario().getObjects());
            
            int idBussola = (int) (Math.random()*2000);
            while(codiceEsistente("codBussola", "Bussola", "codBussola", idBussola)){
                idBussola = (int) (Math.random()*2000);
            }
            inserimentoBussola(conn, idBussola);
            
            int idStats= (int) (Math.random()*2000);
            //genera un id inesistente
            while(codiceEsistente("codStats", "StatsUtente", "codStats", idStats) && idStats==0){
                idStats= (int) (Math.random()*2000);
            }
            System.out.println("\b\b\b\b\b");
            System.out.print("Inserimento dati giocatore...");
            inserimentoStats(conn, idStats, idInventario, idBussola, this.giocoCorrente.getUser(), this.giocoCorrente.getCurrentRoom().getNomeStanza().toString(), this.idSalvataggio);          
            
            System.out.println("\b\b\b\b\b");
            System.out.println("Ritorno al gioco...");

            inserimentoStatsSalvataggio(idStats);
            conn.close();
        }
    }

    private boolean codiceEsistente(String attributoCount, String tabellaInput, String attributoConfronto, int idInput) {
        Boolean risultatoB;
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://housebreak-db.cafdhyoaqv4t.eu-west-2.rds.amazonaws.com:3306/HouseBreak","admin", "housebreak");
            ResultSet risultato;
            try(PreparedStatement inventario = conn.prepareStatement("SELECT Count("+attributoCount+") FROM "+tabellaInput+" WHERE "+attributoConfronto+"="+idInput+";")){
                risultato = inventario.executeQuery();
                if(risultato.next()){
                    risultatoB = risultato.getInt(1)>=1;
                }else{
                    risultatoB = false;
                }

                conn.close();
                inventario.close();
                risultato.close();
            }
            return risultatoB;
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return false;
    }

    private void setNullSalvataggio(int codSalvataggio, Connection conn)throws SQLException{
        try (PreparedStatement updateSalvataggio = conn.prepareStatement("UPDATE Salvataggio SET codStatsUtente = null WHERE codSalvataggio = ?;")) {
            updateSalvataggio.setInt(1, codSalvataggio);
            updateSalvataggio.executeUpdate();
            updateSalvataggio.close();
        }catch(SQLException ex){
            System.err.println(ex);
        }
    }

    private void cancellaSalvataggio(int codSalvataggio, Connection conn)throws SQLException{
        try (PreparedStatement eliminaSalvataggio = conn.prepareStatement("DELETE FROM Salvataggio WHERE codSalvataggio = ?;")) {
            eliminaSalvataggio.setInt(1, codSalvataggio);
            eliminaSalvataggio.executeUpdate();
            eliminaSalvataggio.close();
        }catch(SQLException ex){
            System.err.println(ex);
        }
    }

    private void cancellazioneDati(int codConfronto, String tabellaTarget, String attributoCondizione, Connection conn)throws SQLException{
        try (PreparedStatement cancellazioneStats = conn.prepareStatement("DELETE FROM "+tabellaTarget+" WHERE "+attributoCondizione+" = ?;")) {
            cancellazioneStats.setInt(1, codConfronto);
            cancellazioneStats.executeUpdate();
            cancellazioneStats.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }

    private int idBussolaUtente(int codConfronto, Connection conn)throws SQLException{
        int codBussola=0;
        ResultSet risultatoBussola;
        try (PreparedStatement queryBussola = conn.prepareStatement("SELECT codBussola FROM StatsUtente WHERE codSalvataggio= ?;")) {
            queryBussola.setInt(1, codConfronto);
            risultatoBussola = queryBussola.executeQuery();
            if(risultatoBussola.next()){
                codBussola = risultatoBussola.getInt(1);
            }
            risultatoBussola.close();
            queryBussola.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return codBussola;
    }

    private ArrayList<Integer> idInventariEliminare(int codSalvataggio, Connection conn)throws SQLException{
        ArrayList<Integer> risultatoId = new ArrayList<>();
        ResultSet risultato;
        try (PreparedStatement idInventari = conn.prepareStatement("SELECT codInventario FROM Inventario WHERE codSalvataggio= ?;")) {
            idInventari.setInt(1, codSalvataggio);
            risultato = idInventari.executeQuery();
            
            while(risultato.next()){
                risultatoId.add(risultato.getInt(1));
            }
            idInventari.close();
            risultato.close();
        }catch(SQLException ex){
            System.err.println(ex);
        }
        

        return risultatoId;
    }

    private void eliminazioneInventari(int codSalvataggio, ArrayList<Integer> codiciInventario , Connection conn)throws SQLException{
        //ELIMINAZIONE OGGETTI INVENTARIO
        PreparedStatement eliminazioneOggetti;
        for(Integer numero : codiciInventario){
            eliminazioneOggetti = conn.prepareStatement("DELETE FROM OggettoInventario WHERE codInventario=?;");
            eliminazioneOggetti.setInt(1, numero);
            eliminazioneOggetti.executeUpdate();
            eliminazioneOggetti.close();
        }

        try ( //ELIMINAZIONE INVENTARI
            PreparedStatement eliminazioneInventari = conn.prepareStatement("DELETE FROM Inventario WHERE codSalvataggio=?;")) {
            eliminazioneInventari.setInt(1, codSalvataggio);
            eliminazioneInventari.executeUpdate();
            eliminazioneInventari.close();
        }catch(SQLException ex){
            System.err.println(ex);
        }
    }

    private void eliminazioneStanze(int codSalvataggio, Connection conn)throws SQLException{
        try (PreparedStatement eliminazioneStanze = conn.prepareStatement("DELETE FROM Stanza WHERE codSalvataggio=?;")) {
            eliminazioneStanze.setInt(1, codSalvataggio);
            eliminazioneStanze.executeUpdate();
            eliminazioneStanze.close();
        }catch(SQLException ex){
            System.err.println(ex);
        }
    }

    /**
     * Cancella il vecchio salvataggio per sostituirlo a quello nuovo.
     * @throws SQLException
     */
    public void cancellaVecchioSalvataggio()throws SQLException{
        try(Connection conn =  DriverManager.getConnection("jdbc:mysql://housebreak-db.cafdhyoaqv4t.eu-west-2.rds.amazonaws.com:3306/HouseBreak","admin", "housebreak");){
            System.out.print("\nChiamata degli umpa lumpa per aggiornamento in corso....");
            //SETTA NNULL COD STATS UTENTE IN SALVATAGGIO
            setNullSalvataggio(this.idSalvataggio, conn);

            //ID BUSSOLA DA ELIMINARE
            int idBussolaUtente = idBussolaUtente(this.idSalvataggio, conn);
            

            //ELIMINA STATS UTENTE CON COD SALVATAGGIO
            cancellazioneDati(this.idSalvataggio, "StatsUtente", "codSalvataggio", conn);

            //ELIMINA BUSSOLA CON CODICE UGUALE ALL'UTENTE
            cancellazioneDati(idBussolaUtente, "Bussola", "codBussola", conn);

            //ELIMINA STANZE CON COD SALVATAGGIO
            eliminazioneStanze(this.idSalvataggio, conn);

            //ID INVENTARI DA ELIMINARE
            ArrayList<Integer> idInventari = idInventariEliminare(this.idSalvataggio, conn);

            //ELIMINAZIONE INVENTARI 
            eliminazioneInventari(this.idSalvataggio, idInventari, conn);

            //eliminazione salvataggio
            cancellaSalvataggio(this.idSalvataggio, conn);
            this.idSalvataggio = 0;
            System.out.print("Arrivati!\n\n");
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
}
