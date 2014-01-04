/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

import java.awt.Color;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import static sovelluslogiikka.VARI.*;

/**
 *
 * Pelilauta huolehtii esim. kaikesta. Toimii rajapintana käyttöliittymälle ja
 * huolehtii pelilaudan tapahtumista.
 *
 *
 * @author hilla
 */
public class Pelilauta implements KimbleLogiikka {
    
    /**
     * Pelilaudan neutraalit ruudut ja maaliruudut. Avaimena integer väliltä 0-43, joka on 
     * ruudun indeksi. Tietyt indeksit tiettyjen värien maaliruutuja:
     *
     * 7-10 SININEN
     * 18-21 PUNAINEN
     * 29-32 KELTAINEN
     * 40-43 VIHREA
     */
    private HashMap<Integer, Ruutu> rengas;
    
    /**
     * Lista pelaajista. 
     * 0 = SININEN
     * 1 = PUNAINEN
     * 2 = KELTAINEN
     * 3 = VIHREA
     * 
     */
    private ArrayList<Pelaaja> pelaajat;
    /**
    * Lähtöruudut. Avaimena integer, joka on ruudun indeksi.
    * 
    * 44-47 SININEN
    * 48-51 PUNAINEN
    * 52-55 KELTAINEN
    * 56-59 VIHREA
    * 
    */
    private HashMap<Integer, Ruutu> lahtoruudut;
    
    /**
    * Noppa. Arpoo satunnaisia lukuja 1-6.
    */
    private Noppa noppa;
    
    /**
     * Lista pelaajien mahdollisista väreistä. 
     * 
     * 0 = SININEN
     * 1 = PUNAINEN
     * 2 = KELTAINEN
     * 3 = VIHREA
     * 
     */
    private ArrayList<VARI> varit;
    
    /**
     * Lähtöruudut. Avaimena väri, joka viittaa listaan lähtöruutujen 
     * indekseistä.
     */
    private HashMap<VARI, ArrayList<Integer>> aloitusruudut;
    
    /**
     * Väri, jonka vuoro tällä hetkellä on.
     */
    private VARI kenenVuoro;

    /**
     * Konstruktorissa alustetaan muuttujat ja asetetaan aloitusvuoro 
     * siniselle. Lisätään myös värit listaan.
     */
    
    public Pelilauta() {
        this.rengas = new HashMap<Integer, Ruutu>();
        this.lahtoruudut = new HashMap<Integer, Ruutu>();
        this.pelaajat = new ArrayList<Pelaaja>();
        this.noppa = new Noppa();
        this.varit = new ArrayList<VARI>();
        this.aloitusruudut = new HashMap<VARI, ArrayList<Integer>>();
        this.kenenVuoro = SININEN;

        varit.add(VARI.SININEN);
        varit.add(VARI.PUNAINEN);
        varit.add(VARI.KELTAINEN);
        varit.add(VARI.VIHREA);

    }
    
    /**
     * Huolehtii ruutujen luomisen hallinnoinnista. Luo neutraalit ruudut ja 
     * maaliruudut ja kutsuu metodia luoLahtoruudut().
     * 
     * @see luoLahtoruudut()
     * 
     */

    @Override
    public void luoRuudut() {

        for (int i = 0; i < 44; i++) {
            this.rengas.put(i, new Ruutu(i, null));
        }

        for (int i = 7; i < 11; i++) {
            this.rengas.put(i, new Ruutu(i, VARI.PUNAINEN));
        }
        for (int i = 18; i < 22; i++) {
            this.rengas.put(i, new Ruutu(i, VARI.KELTAINEN));
        }
        for (int i = 29; i < 33; i++) {
            this.rengas.put(i, new Ruutu(i, VARI.VIHREA));
        }
        for (int i = 40; i < 44; i++) {
            this.rengas.put(i, new Ruutu(i, VARI.SININEN));
        }

        luoLahtoruudut();

    }

    /**
     * 
     * Luo lähtöruudut parametrina annetulle värille.
     * 
     * @param alkuindeksi värin ensimmäinen lähtöruutu
     * @param vari väri jolle lähtöruudut luodaan
     */
    
    public void luoLahtoruudutVarille(int alkuindeksi, VARI vari) {
        ArrayList<Integer> lista = new ArrayList<Integer>();

        for (int i = alkuindeksi; i < alkuindeksi + 4; i++) {
            this.lahtoruudut.put(i, new Ruutu(i, vari));
            lista.add(i);
        }

        this.aloitusruudut.put(vari, lista);
    }

    /**
     * Hallinnoi lähtöruutujen luontia.
     * 
     * @see LuoLahtoruudutVarille()
     */
    
    public void luoLahtoruudut() {

        luoLahtoruudutVarille(44, VARI.SININEN);
        luoLahtoruudutVarille(48, VARI.PUNAINEN);
        luoLahtoruudutVarille(52, VARI.KELTAINEN);
        luoLahtoruudutVarille(56, VARI.VIHREA);

    }

    
    /**
     * 
     * Luo peliin pelaajat. Pelaajia luodaan 0-4 riippuen parametrista. 
     * Pelaajien luonti alkaa värillä SININEN.
     * Kutsuu jokaiselle pelaajalle metodia luoNappulat()
     * 
     * @see luoNappulat()
     * @param pelaajienMaara montako pelaajaa luodaan
     * @param nimet pelaajien nimet ArrayListinä - toiminnallisuus kesken!
     */
    
    @Override
    public void luoPelaajat(int pelaajienMaara, ArrayList<String> nimet) {
        int i = 0;

        for (VARI vari : VARI.values()) {

            if (i < pelaajienMaara) {
                if (nimet.size() <= i) {
                    Pelaaja pelaaja = new Pelaaja(vari);
                    this.pelaajat.add(pelaaja);
                    luoNappulat(pelaaja);
                } else {
                    Pelaaja pelaaja = new Pelaaja(nimet.get(i), vari);
                    this.pelaajat.add(pelaaja);
                    luoNappulat(pelaaja);
                }
                i++;
            }

        }

    }
    
    /**
     * 
     * Luo 4 nappulaa parametrina annetulle pelaajalle. Kutsuu metodia 
     * asetaNappulatAloitusruutuihin ja asettaa nappulat pelaajalle.
     * 
     * @see asetaNappulatAloitusruutuihin()
     * 
     * @param pelaaja pelaaja jolle nappulat luodaan
     */

    public void luoNappulat(Pelaaja pelaaja) {

        ArrayList<Nappula> nappulat = new ArrayList<Nappula>();

        for (int i = 0; i < 4; i++) {
            Nappula nappula = new Nappula(pelaaja, pelaaja.getVari().getLahtoruutu());
            nappulat.add(nappula);
        }

        asetaNappulatAloitusruutuihin(nappulat, pelaaja.getVari());
        pelaaja.setNappulat(nappulat);
    }
    
    /**
     * 
     * Asettaa parametrina annetut nappulat parametrina annetun värin 
     * aloitusruutuihin.
     * 
     * @param nappulat mitkä nappulat asetetaan
     * @param vari minkä värin aloitusruutuihin
     */

    public void asetaNappulatAloitusruutuihin(ArrayList<Nappula> nappulat, VARI vari) {
        int i = 0;

        for (Integer indeksi : this.aloitusruudut.get(vari)) {
            this.lahtoruudut.get(indeksi).asetaNappulaRuutuun(nappulat.get(i));
            i++;
        }
    }

    // tää on superkesken ja aika olennainen!
    @Override
    public boolean siirraNappulaa(Nappula nappula, int askeleita) {

        VARI verrokkivari = nappula.getPelaaja().getVari();
        int sijaintiNyt = nappula.getSijainti();

        int tutkittavaSijainti = sijaintiNyt + askeleita;

        if (tutkittavaSijainti > 43) {
            return false;
        } else {

            Ruutu tutkittavaRuutu = this.rengas.get(tutkittavaSijainti);

            if (verrokkivari.equals(tutkittavaRuutu.getVari()) || tutkittavaRuutu.getVari() == null) {
                return true;
            }
        }

        return false;

    }

    /**
     * Metodi palauttaa ruudussa olevan nappulan värin. Metodi saa parametrina
     * ruudun indeksin.
     *
     * @param i parametrina ruudun indeksi
     *
     * @return VARI minkä värinen nappula ruudussa - palauttaa null mikäli 
     * indeksiä ei löydy
     */
    @Override
    public VARI minkaVarinenNappula(int i) {

        if (this.rengas.containsKey(i)) {
            if (onkoRuudussaNappula(i)) {
                return this.rengas.get(i).getNappula().getPelaaja().getVari();
            }
        }

        if (this.lahtoruudut.containsKey(i)) {
            if (onkoRuudussaNappula(i)) {
                return this.lahtoruudut.get(i).getNappula().getPelaaja().getVari();
            }
        }

        return null;
    }

    public void siirraNappulaRuutuun(Nappula nappula, int indeksi) {

        if (!onkoRuudussaNappula(indeksi)) {
            Ruutu sijoitettava = this.rengas.get(indeksi);
            poistaNappulaRuudusta(nappula.getSijainti());
            sijoitettava.asetaNappulaRuutuun(nappula);
        } else {
            if (!minkaVarinenNappula(indeksi).equals(nappula.getPelaaja().getVari())) {
                siirraLahtoruutuun(this.rengas.get(indeksi).getNappula());
                Ruutu sijoitettava = this.rengas.get(indeksi);
                poistaNappulaRuudusta(nappula.getSijainti());
                sijoitettava.asetaNappulaRuutuun(nappula);
            }
        }

    }

    /**
     * 
     * Poistaa nappulan ruudusta, joka liittyy parametrina annettuun indeksiin. 
     * Mikäli indeksiä ei löydy, ei poisteta mitään.
     * 
     * @param indeksi mistä indeksistä poistetaan nappula
     */
    
    public void poistaNappulaRuudusta(int indeksi) {
        if (this.rengas.containsKey(indeksi)) {
            this.rengas.get(indeksi).poistaNappulaRuudusta();
        }

        if (this.lahtoruudut.containsKey(indeksi)) {
            this.lahtoruudut.get(indeksi).poistaNappulaRuudusta();
        }
    }

    /**
     * 
     * Palauttaa boolean-arvon, joka kertoo onko parametrina annetussa 
     * indeksissä nappulaa.
     * Palauttaa false, mikäli indeksiä ei löydy.
     * 
     * @param i indeksi johon liittyvää ruutua tutkitaan
     * @return true mikäli indeksin ruudusta löytyy nappula, muuten false
     */
    
    @Override
    public boolean onkoRuudussaNappula(int i) {

        if (this.rengas.containsKey(i)) {
            if (this.rengas.get(i).getNappula() != null) {
                return true;
            }
        }

        if (this.lahtoruudut.containsKey(i)) {
            if (this.lahtoruudut.get(i).getNappula() != null) {
                return true;
            }
        }

        return false;

    }

    /**
     * 
     * Siirtää vuoron seuraavalle pelaajalle. Kutsuu metodia 
     * setNappulatValittaviksi kahdesti, edellisen vuoron vuorossaolleelle 
     * pelaajalle ja uuden vuoron pelaajalle. Huomioi pelaajien määrän.
     * 
     * @see setNappulatValittaviksi(VARI, boolean)
     * 
     */
    
    @Override
    public void seuraavanVuoro() {

        setNappulatValittaviksi(this.kenenVuoro, false);
        int variIndeksi = 0;

        for (int i = 0; i < this.varit.size(); i++) {
            if (this.varit.get(i).equals(this.kenenVuoro)) {
                variIndeksi = i;
            }
        }

        if (pelaajat.size() <= variIndeksi + 1) {
            this.kenenVuoro = SININEN;
        } else {
            this.kenenVuoro = this.varit.get(variIndeksi + 1);
        }

        setNappulatValittaviksi(this.kenenVuoro, true);

    }

    @Override
    public void siirraLahtoruutuun(Nappula nappula) {

        boolean jatkuu = true;
        int sijaintiEnnen = nappula.getSijainti();

        for (Integer integer : this.aloitusruudut.get(nappula.getPelaaja().getVari())) {
            if (jatkuu == true && this.lahtoruudut.get(integer).asetaNappulaRuutuun(nappula) == true) {
                this.rengas.get(sijaintiEnnen).poistaNappulaRuudusta();
                jatkuu = false;
            }
        }

    }
    
    /**
     * 
     * Asettaa annetun värin nappulat valittaviksi tai ei-valittaviksi parametrin mukaan.
     * 
     * @param vari minkä värin nappulat asetetaan
     * @param bool true asettaa valittaviksi, false ei-valittaviksi
     */

    public void setNappulatValittaviksi(VARI vari, boolean bool) {

        Pelaaja pelaaja = getPelaaja(vari);

        for (Nappula nappula : pelaaja.getNappulat()) {
            getRuutu(nappula.getSijainti()).setOnkoValittava(bool);
        }

    }

    public HashMap<Integer, Ruutu> getRengas() {
        return this.rengas;
    }

    public ArrayList<Pelaaja> getPelaajat() {
        return this.pelaajat;
    }

    public HashMap<Integer, Ruutu> getLahtoruudut() {
        return this.lahtoruudut;
    }

    /**
     * Palauttaa annetun värin pelaajan.
     * 
     * @param vari minkä värin pelaaja
     * @return Pelaaja jonka väri on parametrina annettu väri. Palauttaa null
     * jos tällaista pelaajaa ei löydy.
     */
    
    public Pelaaja getPelaaja(VARI vari) {

        for (Pelaaja pelaaja : pelaajat) {
            if (pelaaja.getVari().equals(vari)) {
                return pelaaja;
            }
        }
        return null;
    }

    public HashMap<VARI, ArrayList<Integer>> getAloitusruudut() {
        return this.aloitusruudut;
    }

    @Override
    public int heitaNoppaa() {
        return this.noppa.heitaNoppaa();
    }

    @Override
    public VARI kenenVuoro() {
        return this.kenenVuoro;
    }
    
    /**
     * 
     * Palauttaa parametrina annetun indeksin ruudun. Käytännössä etsii sen
     * joko tavallisten ruutujen tai lähtöruutujen joukosta.
     * 
     * @param indeksi
     * @return 
     */

    public Ruutu getRuutu(int indeksi) {
        if (this.rengas.containsKey(indeksi)) {
            return this.rengas.get(indeksi);
        }

        if (this.lahtoruudut.containsKey(indeksi)) {
            return this.lahtoruudut.get(indeksi);
        }

        return null;
    }

}
