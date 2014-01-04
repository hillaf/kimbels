/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

import java.util.Random;

/**
 *
 * 
 * Noppa. Arpoo satunnaisen luvun väliltä 1-6.
 * 
 * @author hilla
 */
public class Noppa {
    
    private Random arpoja;
    
    public Noppa(){
        this.arpoja = new Random();
    }
    
    /**
     * Arpoo luvun väliltä 1-6.
     * 
     * @return palauttaa arvotun luvun int-tyyppisenä muuttujana
     */
    
    public int heitaNoppaa(){
        
        int arvottuLuku = arpoja.nextInt(6);
        arvottuLuku = arvottuLuku+1;
        
        return arvottuLuku;
    }
    
}
