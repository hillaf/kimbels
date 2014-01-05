/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

/**
 *
 *
 * Pelilaudan yksi ruutu. Voi olla lähtö- tai maaliruutu tai tavallinen ruutu.
 * Tavalliset ruudut väriltään null. Tietää oman värinsä ja sijaintinsa. Saattaa
 * sisältää nappulan - jos ei, nappula == null.
 *
 *
 * @author hilla
 */
public class Ruutu {

    /**
     * Ruudun sijainti indeksinä väliltä 0-59.
     */
    private int sijainti;

    /**
     * Ruudun sisältämä nappula. Jos ei nappulaa, niin nappula == null;
     */
    private Nappula nappula;

    /**
     * Ruudun väri. Jos neutraali, niin vari == null;
     */
    private VARI vari;

    /**
     * Onko ruutu aktiivinen. Eli onko ruudussa nappula, joka kuuluu pelaajalle,
     * jolla on tällä hetkellä pelivuoro. Oletuksena false.
     */
    private boolean onkoValittava;

    /**
     *
     * @param sijainti ruudun indeksi
     * @param vari ruudun mahdollinen väri, null jos neutraali
     */
    public Ruutu(int sijainti, VARI vari) {
        this.sijainti = sijainti;
        this.vari = vari;
        this.onkoValittava = false;
        this.nappula = null;
    }

    /**
     * Asettaa nappulan ruutuun.
     *
     * @param nappula nappula joka ruutuun asetetaan
     * @return palauttaa true jos asettaminen onnistui, false muuten
     */
    public void asetaNappulaRuutuun(Nappula nappula) {
        this.nappula = nappula;
        nappula.setSijainti(this.sijainti);

    }

    public void poistaNappulaRuudusta() {
        this.nappula = null;
    }

    public Nappula getNappula() {
        return this.nappula;
    }

    public int getSijainti() {
        return this.sijainti;
    }

    public VARI getVari() {
        return this.vari;
    }

    public boolean onkoValittava() {
        return this.onkoValittava;
    }

    /**
     *
     * @param bool true jos asetetaan valittavaksi, false jos poistetaan
     * valittaavuus
     */
    public void setOnkoValittava(boolean bool) {
        this.onkoValittava = bool;
    }

}
