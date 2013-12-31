/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

import java.util.Random;

/**
 *
 * @author hilla
 */
public class Noppa {
    
    private Random arpoja;
    
    public Noppa(){
        this.arpoja = new Random();
    }
    
    public int heitaNoppaa(){
        
        int arvottuLuku = arpoja.nextInt(6);
        arvottuLuku = arvottuLuku+1;
        
        return arvottuLuku;
    }
    
}
