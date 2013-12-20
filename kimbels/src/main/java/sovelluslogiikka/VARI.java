/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

/**
 *
 * @author hilla
 */
public enum VARI {
    SININEN(0), PUNAINEN(7), KELTAINEN(14), VIHREA(21), NEUTRAALI(-1);
    private int lahtoruutu;

        private VARI(int lahtoruutu) {
                this.lahtoruutu = lahtoruutu;
        }
        
        public int getLahtoruutu(){
            return this.lahtoruutu;
        }

}
