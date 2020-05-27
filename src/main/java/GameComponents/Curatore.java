package GameComponents;

/**
 * Oggetti che permettono di curare il giocatore
 * Es. Bende, cibo, bevande.
 * Utilizzano la stessa struttura degli oggetti
 * @author Moresi Gianmarco
 */
public class Curatore extends GameObject{
    private int puntiVita;
    
    public Curatore(String lineaFile){
        puntiVita = 0;
        acquisizoneInputFile(lineaFile);
    }
    
    public void setPuntiVita(int puntiInput){
        this.puntiVita = puntiInput;
    }
    
    public int getPuntiVita(){
        return this.puntiVita;
    }
}
