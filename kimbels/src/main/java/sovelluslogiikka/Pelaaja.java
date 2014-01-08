/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

import java.util.ArrayList;

/**
 *
 * Pelaaja. Tietää nimensä, värinsä ja nappulansa.
 * 
 * @author hilla
 */
public class Pelaaja {
    
    private final VARI vari;
    private ArrayList<Nappula> nappulat;
    
    public Pelaaja(VARI vari){
        this.vari = vari;
        this.nappulat = new ArrayList<Nappula>();
    }
   
    public ArrayList<Nappula> getNappulat(){
        return this.nappulat;
    }
    
    public void setNappulat(ArrayList<Nappula> nappulat){
        this.nappulat = nappulat;
    }
    
    
    public VARI getVari(){
        return this.vari;
    }
    
    
}
