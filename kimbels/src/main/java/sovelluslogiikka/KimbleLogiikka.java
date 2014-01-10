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
    
/**
 * Luo ruudut pelilaudalle.
 */
    void luoRuudut();
    
    /**
     * Luo pelaajat peliin.
     * @param pelaajienMaara pelaajien määrä 
     */
    void luoPelaajat(int pelaajienMaara);
    
    /**
     * Siirtää nappulaa annetun luvun verran.
     * @param nappula mitä nappulaa siirretään
     * @param askeleita minkä verran
     * @return päivitettävät ruudut
     */
    HashMap<Integer, VARI> siirraNappulaa(Nappula nappula, int askeleita);
    
    /**
     * Heittää noppaa.
     * @return uusi silmäluku 
     */
    int heitaNoppaa();
    
    /**
     * Palauttaa vuorossaolevan värin.
     * @return väri jonka vuoro on
     */
    VARI kenenVuoro();
    
    /**
     * Siirtää vuoron seuraavalle pelaajalle.
     */
    void seuraavanVuoro();
    
    /**
     * Palauttaa annetussa indeksissä olevan ruudun.
     * @param i indeksi
     * @return ruutu
     */
    Ruutu getRuutu(int i);
    
    /**
     * Palauttaa tämänhetkisen silmäluvun.
     * @return silmäluku
     */
    int silmalukuNyt();
    
    /**
     * Asettaa annetut parametrit vuorossaolijaksi ja silmäluvuksi.
     * @param vari vuorossaoleva väri
     * @param silmaluku nopalla heitetty silmäluku
     */
    void setVuorossaolijaJaSilmaluku(VARI vari, int silmaluku);
    
    /**
     * Tutkii onko annettu pelaaja voittanut pelin.
     * @param vari minkä värinen pelaaja
     * @return true jos on voittanut, false jos ei
     */
    boolean onkoVoittanutPelin(VARI vari);
    
    /**
     * Onko tällä hetkellä heittovuoro (eli ei siirtymisvuoro)
     * @return true jos on heittovuoro, false jos siirtymisvuoro
     */
    boolean onkoHeittovuoro();
    
    /**
     * Siirtää pelin siirtymisvuoroon.
     */
    void setSiirtymisvuoro();
            
    
}
