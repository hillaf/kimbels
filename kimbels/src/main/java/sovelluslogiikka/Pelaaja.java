/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

import java.util.ArrayList;

/**
 *
 * @author hilla
 */
public class Pelaaja {
    
    private String nimi;
    private VARI vari;
    private ArrayList<Nappula> nappulat;
    
    public Pelaaja(String nimi, VARI vari){
        this.nimi = nimi;
        this.vari = vari;
        this.nappulat = new ArrayList<Nappula>();
    }
    
    public Pelaaja(VARI vari){
        this.vari = vari;
        this.nimi = vari.toString();
    }
    
    public ArrayList<Nappula> getNappulat(){
        return this.nappulat;
    }
    
    public void setNappulat(ArrayList<Nappula> nappulat){
        this.nappulat = nappulat;
    }
    
    public String getNimi(){
        return this.nimi;
    }
    
    public VARI getVari(){
        return this.vari;
    }
    
    
}
