/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

/**
 *
 * Pelilaudan värit. Tietävät oman lähtöruutunsa.
 * 
 * @author hilla
 */
public enum VARI {
    SININEN(39), PUNAINEN(6), KELTAINEN(17), VIHREA(28), NEUTRAALI(-1);
    private int lahtoruutu;

        private VARI(int lahtoruutu) {
                this.lahtoruutu = lahtoruutu;
        }
        
        public int getLahtoruutu(){
            return this.lahtoruutu;
        }

}
