/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

import java.util.HashMap;

/**
 *
 * Sovelluslogiikan rajapinta käyttöliittymälle. TODO: karsi turhat ja terävöitä!
 * Tällä hetkellä vähän sillisalaatti.
 * 
 * 
 * @author hilla
 */
public interface KimbleLogiikka {
    

    void luoRuudut();
    void luoPelaajat(int pelaajienMaara);
    
    HashMap<Integer, VARI> siirraNappulaa(Nappula nappula, int askeleita);
    boolean siirraLahtoruutuun(Nappula nappula);
    VARI minkaVarinenNappula(int i);
    boolean onkoRuudussaNappula(int i);
    int heitaNoppaa();
    VARI kenenVuoro();
    void seuraavanVuoro();
    Ruutu getRuutu(int i);
    int silmalukuNyt();
    void setSiirtoVuoro(VARI vari, int silmaluku);
    boolean onkoVoittanutPelin(VARI vari);
    boolean onkoHeittovuoro();
    void siirrytaanSiirtymisvuoroon();
            
    
}
