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
    
    public Ruutu(int sijainti){
        this.sijainti = sijainti;
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
    
    public Nappula getNappula(){
        return this.nappula;
    }
    
    public int getSijainti(){
        return this.sijainti;
    }
}
