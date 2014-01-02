/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

/**
 *
 * @author hilla
 */
public class Ruutu {
 
    private int sijainti;
    private Nappula nappula;
    private VARI vari;
    

    public Ruutu(int sijainti, VARI vari){
        this.sijainti = sijainti;
        this.vari = vari;
    }
    
    
    public boolean asetaNappulaRuutuun(Nappula nappula){
        
        if (this.nappula == null){
            this.nappula = nappula;
            nappula.setSijainti(this.sijainti);
            return true;
        } else {
            return false;
        }
        
    }
    
    public void poistaNappulaRuudusta(){
        this.nappula = null;
    }
    
    public Nappula getNappula(){
        return this.nappula;
    }
    
    public int getSijainti(){
        return this.sijainti;
    }
    
    public VARI getVari(){
        return this.vari;
    }
}
