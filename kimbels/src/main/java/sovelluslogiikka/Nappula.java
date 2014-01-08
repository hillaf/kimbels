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
    
    private final Pelaaja pelaaja;
    private int sijainti;
    
    /**
     * Jos nappula on ohittanut nollan, tiedetään muualla huolehtia ettei se astu yli
     * maailmankartalta ja aiheuta ydintuhoa.
     */
    private boolean onkoOhittanutNollan;
    private final VARI vari;
    
    public Nappula(Pelaaja pelaaja){
        this.pelaaja = pelaaja;
        this.sijainti = pelaaja.getVari().getLahtoruutu();
        this.onkoOhittanutNollan = false;
        this.vari = pelaaja.getVari();
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
    
    public VARI getVari(){
        return this.vari;
    }
    
    public boolean onkoOhittanutNollan(){
        return this.onkoOhittanutNollan;
    }
    
    public void setOhittiNollan(boolean bool){
        this.onkoOhittanutNollan = bool;
    }
}
