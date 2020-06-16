/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameComponents;

/**
 *
 * @author Moresi Gianmarco
 */
public final class Bussola {
    private String avanti,sinistra,destra,giu;

    public Bussola() {
        this.spostamentoNord();
    }

    public void spostamentoNord(){
        avanti = "nord";
        sinistra = "ovest";
        destra = "est";
        giu = "sud";
    }

    public void spostamentoEst() {
        avanti = "est";
        sinistra = "nord";
        destra = "sud";
        giu = "ovest";
    }

    public void spostamentoSud(){
        avanti="sud";
        sinistra="est";
        destra="ovest";
        giu="nord";
    }

    public void spostamentoOvest(){
        avanti="ovest";
        sinistra="sud";
        destra="nord";
        giu="est";
    }
    
    public Bussola getBussola(){
        return this;
    }

    public String getAvanti(){
        return this.avanti;
    }
    
    public void setAvanti(String avantiInput){
        this.avanti = avantiInput;
    }

    public String getGiu(){
        return this.giu;
    }
    
    public void setGiu(String giuInput){
        this.giu = giuInput;
    }

    public String getDestra(){
        return this.destra;
    }
    
    public void setDestra(String destraInput){
        this.destra = destraInput;
    }

    public String getSinistra(){
        return this.sinistra;
    }
    
    public void setSinistra(String sinistraInput){
        this.sinistra = sinistraInput;
    }
    
    public void spostamentoInput(String direzioneInput){
        if(direzioneInput.contains("nord")){
            this.spostamentoNord();
        }else if(direzioneInput.contains("sud")){
            this.spostamentoSud();
        }else if(direzioneInput.startsWith("o")){
            this.spostamentoOvest();
        }else if(direzioneInput.startsWith("e")){
            this.spostamentoEst();
        }
    }
    
    public String getPosizioneUtente(String direzioneInput){
        if(direzioneInput.contains("nord")){
            return this.getAvanti();
        }else if(direzioneInput.contains("sud")){
            return this.getGiu();
        }else if(direzioneInput.startsWith("e")){
            return this.getDestra();
        }else if(direzioneInput.startsWith("o")){
            return this.getSinistra();
        }
        return null;
    }
}
