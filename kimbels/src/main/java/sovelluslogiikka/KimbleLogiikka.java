/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

import java.util.ArrayList;

/**
 *
 * Sovelluslogiikan rajapinta käyttöliittymälle.
 * 
 * 
 * @author hilla
 */
public interface KimbleLogiikka {
    

    void luoRuudut();
    void luoPelaajat(int pelaajienMaara, ArrayList<String> nimilista);
    
    boolean siirraNappulaa(Nappula nappula, int askeleita);
    void siirraLahtoruutuun(Nappula nappula);
    VARI minkaVarinenNappula(int i);
    boolean onkoRuudussaNappula(int i);
    int heitaNoppaa();
    VARI kenenVuoro();
    void seuraavanVuoro();
    Ruutu getRuutu(int i);
    
}
