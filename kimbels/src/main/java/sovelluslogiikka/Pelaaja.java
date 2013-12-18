/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

/**
 *
 * @author hilla
 */
public class Pelaaja {
    
    private String nimi;
    private VARI vari;
    
    public Pelaaja(String nimi, VARI vari){
        this.nimi = nimi;
        this.vari = vari;
    }
    
    public Pelaaja(VARI vari){
        this.vari = vari;
        this.nimi = vari.toString();
    }
    
    public String getNimi(){
        return this.nimi;
    }
    
    public VARI getVari(){
        return this.vari;
    }
}
