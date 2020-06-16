/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;


/**
 *
 * @author burritos
 */
public class Salvataggio {
    HouseBreak giocoCorrente;
    
    public Salvataggio(HouseBreak gioco){
        this.giocoCorrente = gioco;
    }
    
    private int idSalvataggio = 0;
    private int idGameComponents = 0;
    private int idPlayerStats = 0;

    public int getIdSalvataggio(){
        return this.idSalvataggio;
    }
    
    public void setIdSalvataggio(int id){
        this.idSalvataggio = id;
    }
    
    public int getIdGameComponents(){
        return this.idGameComponents;
    }
    
    public void setIdGameComponents(int id){
        this.idGameComponents = id;
    }
    
    public int getIdPlayerStats(){
        return this.idPlayerStats;
    }
    
    public void setIdPlayerStats(int id){
        this.idPlayerStats = id;
    }
    
    public Boolean primoSalvataggio(){
        return this.idSalvataggio==0;
    }
    
    public void salvaPartita() throws SQLException{
        if(primoSalvataggio()){
            inserimentoPartita();
        }else{

        }
    }
    
    private void inserimentoInventario(Connection conn, int idInventario) throws SQLException{
        PreparedStatement inserimentoOggetto;
        //inserimento codice inventario
        inserimentoOggetto = conn.prepareStatement("INSERT INTO Inventario VALUES (?)");
        inserimentoOggetto.setInt(1, idInventario);
        inserimentoOggetto.executeUpdate();

        //inserimento oggetti inventario
        for(int i=0; i< this.giocoCorrente.getUser().getInvetario().getObjects().size(); i++){
            //inserimento oggetto inventario
            inserimentoOggetto = conn.prepareStatement("INSERT INTO OggettoInventario VALUES (?,?, ?, ?, ?, ?, ?, ?)");
            inserimentoOggetto.setInt(1, idInventario);
            inserimentoOggetto.setString(2, this.giocoCorrente.getUser().getInvetario().getObjects().get(i).getNome());
            //IMPOSTA SE L'OGGETTO È CURATORE
            if(this.giocoCorrente.getUser().getInvetario().getObjects().get(i).getCuratore()){
                inserimentoOggetto.setInt(3, 1);
            }else{
                inserimentoOggetto.setInt(3, 0);
            }
            
            //IMPOSTA SE L'OGGETTO È USABILE
            if(this.giocoCorrente.getUser().getInvetario().getObjects().get(i).getUsabile()){
                inserimentoOggetto.setInt(4, 1);
            }else{
                inserimentoOggetto.setInt(4, 0);
            }
            
            //IMPOSTA SE  L'OGGETTO È EQUIPAGGIABILE
            if(this.giocoCorrente.getUser().getInvetario().getObjects().get(i).getEquipaggiabile()){
                inserimentoOggetto.setInt(5, 1);
            }else{
                inserimentoOggetto.setInt(5, 0);
            }
            
            //imposta se l'oggetto può essere preso
            if(this.giocoCorrente.getUser().getInvetario().getObjects().get(i).isPickable()){
                inserimentoOggetto.setInt(6, 1);
            }else{
                inserimentoOggetto.setInt(6, 0);
            }
            
            //imposta se l'oggetto è premibile
            if(this.giocoCorrente.getUser().getInvetario().getObjects().get(i).isPushable()){
                inserimentoOggetto.setInt(7, 1);
            }else{
                inserimentoOggetto.setInt(7, 0);
            }
            
            //imposta se l'oggetto è stato premuto
            if(this.giocoCorrente.getUser().getInvetario().getObjects().get(i).isPushed()){
                inserimentoOggetto.setInt(8, 1);
            }else{
                inserimentoOggetto.setInt(8, 0);
            }
            
            inserimentoOggetto.executeUpdate();
            //chiusura
            inserimentoOggetto.close();
        }
    }
    
    private void inserimentoStats(Connection conn, int idStats, int idInventario, int idBussola) throws SQLException{
        //Inserimento dati utente (vita ecc...)
        PreparedStatement inserimentoDatiUtente;
        inserimentoDatiUtente = conn.prepareStatement("INSERT INTO StatsUtente VALUES (?, ?, ?, ?, ?, ?, ?);");
        //inserimento id
        inserimentoDatiUtente.setInt(1, idStats);
        //inserimento vita
        inserimentoDatiUtente.setInt(2, this.giocoCorrente.getUser().getVita());
        //inserimento inventario
        inserimentoDatiUtente.setInt(3, idInventario);
        //Inserimento arma equipaggiata
        if(this.giocoCorrente.getUser().getArmaEquipaggiata()!= null){
            inserimentoDatiUtente.setString(4, this.giocoCorrente.getUser().getArmaEquipaggiata().getNome());
            inserimentoDatiUtente.setInt(6, this.giocoCorrente.getUser().getArmaEquipaggiata().getMunizioni());
        }else{
            inserimentoDatiUtente.setString(4, null);
            inserimentoDatiUtente.setInt(6, 0);
        }

        //Inserimento utente bloccato
        //Se è bloccato, 1
        if(this.giocoCorrente.getUser().bloccato()){
            inserimentoDatiUtente.setInt(5, 1);
            //sennò 0
        }else{
            inserimentoDatiUtente.setInt(5, 0);
        }
        
        inserimentoDatiUtente.setInt(7, idBussola);
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
        inserimentoDatiSalvataggio.setInt(3, idStats);
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
    
    private void inserimentoPartita() throws SQLException{        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://sql2.freesqldatabase.com:3306/sql2347978","sql2347978", "fE6%xP5%")) {
            int idInventario= (int) (Math.random()*2000);
            //genera un id inesistente
            while(codiceEsistente("codInventario", "Inventario", "codInventario", idInventario)){
                idInventario= (int) (Math.random()*2000);
            }
            inserimentoInventario(conn, idInventario);
            
            int idBussola = (int) (Math.random()*2000);
            while(codiceEsistente("codBussola", "Bussola", "codBussola", idBussola)){
                idBussola = (int) (Math.random()*2000);
            }
            inserimentoBussola(conn, idBussola);
            
            int idStats= (int) (Math.random()*2000);
            //genera un id inesistente
            while(codiceEsistente("codStats", "StatsUtente", "codStats", idStats)){
                idStats= (int) (Math.random()*2000);
            }
            inserimentoStats(conn, idStats, idInventario, idBussola);
            
            int codSalvataggio= (int) (Math.random()*2000);
            //genera un id inesistente
            while(codiceEsistente("codSalvataggio", "Salvataggio", "codSalvataggio", codSalvataggio)){
                codSalvataggio= (int) (Math.random()*2000);
            }
            inserimentoSalvataggio(conn, idStats, codSalvataggio);
        }
    }

    private boolean codiceEsistente(String attributoCount, String tabellaInput, String attributoConfronto, int idInput) {
        Boolean risultatoB;
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://sql2.freesqldatabase.com:3306/sql2347978","sql2347978", "fE6%xP5%");
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
    
}
