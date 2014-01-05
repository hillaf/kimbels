/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

import java.awt.Color;

/**
 *
 * Pelilaudan värit. Tietävät oman lähtöruutunsa.
 * 
 * @author hilla
 */
public enum VARI {
    SININEN(0, Color.BLUE), PUNAINEN(11, Color.RED), KELTAINEN(22, Color.YELLOW), VIHREA(33, Color.GREEN);
    private int lahtoruutu;
    private Color color;

        private VARI(int lahtoruutu, Color color) {
                this.lahtoruutu = lahtoruutu;
                this.color = color;
        }
        
        public int getLahtoruutu(){
            return this.lahtoruutu;
        }
        
        public Color getColor(){
            return this.color;
        }

        

}
