/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

/**
 *
 * 
 * Nappula. Tietää kenelle pelaajalle kuuluu sekä oman sijaintinsa.
 * 
 * @author hilla
 */
public class Nappula {
    
    private Pelaaja pelaaja;
    private int sijainti;
    
    public Nappula(Pelaaja pelaaja, int sijainti){
        this.pelaaja = pelaaja;
        this.sijainti = sijainti;
    }
    
    
    public Pelaaja getPelaaja(){
        return this.pelaaja;
    }
    
    public void setSijainti(int sijainti){
        this.sijainti = sijainti;
    }
    
    public int getSijainti(){
        return this.sijainti;
    }
}
