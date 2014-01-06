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
     * Pelilaudan neutraalit ruudut ja maaliruudut. Avaimena integer väliltä
     * 0-43, joka on ruudun indeksi. Tietyt indeksit tiettyjen värien
     * maaliruutuja:
     *
     * 7-10 SININEN 18-21 PUNAINEN 29-32 KELTAINEN 40-43 VIHREA
     */
    private HashMap<Integer, Ruutu> rengas;

    /**
     * Lista pelaajista. 0 = SININEN 1 = PUNAINEN 2 = KELTAINEN 3 = VIHREA
     *
     */
    private ArrayList<Pelaaja> pelaajat;
    /**
     * Lähtöruudut. Avaimena integer, joka on ruudun indeksi.
     *
     * 44-47 SININEN 48-51 PUNAINEN 52-55 KELTAINEN 56-59 VIHREA
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
     * 0 = SININEN 1 = PUNAINEN 2 = KELTAINEN 3 = VIHREA
     *
     */
    private HashMap<Integer, VARI> varit;

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
     * Viimeksi heitetty nopan silmäluku;
     */
    private int silmalukuNyt;

    /**
     * Konstruktorissa alustetaan muuttujat ja asetetaan aloitusvuoro siniselle.
     * Lisätään myös värit listaan.
     */
    public Pelilauta() {
        this.rengas = new HashMap<Integer, Ruutu>();
        this.lahtoruudut = new HashMap<Integer, Ruutu>();
        this.pelaajat = new ArrayList<Pelaaja>();
        this.noppa = new Noppa();
        this.varit = new HashMap<Integer, VARI>();
        this.aloitusruudut = new HashMap<VARI, ArrayList<Integer>>();
        this.kenenVuoro = VARI.SININEN;

        varit.put(0, VARI.SININEN);
        varit.put(1, VARI.PUNAINEN);
        varit.put(2, VARI.KELTAINEN);
        varit.put(3, VARI.VIHREA);

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
     * @see LuoLahtoruudutVarille(int i, VARI vari)
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
     * Pelaajien luonti alkaa värillä SININEN. Kutsuu jokaiselle pelaajalle
     * metodia luoNappulat()
     *
     * @see luoNappulat(Pelaaja pelaaja)
     * @param pelaajienMaara montako pelaajaa luodaan
     * @param nimet pelaajien nimet ArrayListinä - toiminnallisuus kesken!
     */
    @Override
    public void luoPelaajat(int pelaajienMaara, ArrayList<String> nimet) {

        if (pelaajienMaara > 4) {
            pelaajienMaara = 4;
        }

        for (int j = 0; j < pelaajienMaara; j++) {
            Pelaaja pelaaja = new Pelaaja(this.varit.get(j));
            this.pelaajat.add(pelaaja);
            luoNappulat(pelaaja);
        }
    }

    /**
     *
     * Luo 4 nappulaa parametrina annetulle pelaajalle. Kutsuu metodia
     * asetaNappulatAloitusruutuihin ja asettaa nappulat pelaajalle.
     *
     * @see asetaNappulatAloitusruutuihin(ArrayList<Nappula> nappulat, VARI
     * vari)
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
    public int siirraNappulaa(Nappula nappula, int askeleita) {

        
        VARI verrokkivari = nappula.getPelaaja().getVari();
        int sijaintiNyt = nappula.getSijainti();
        int tutkittavaSijainti = sijaintiNyt + askeleita;

        // jos ollaan aloitusruudussa
        if (sijaintiNyt > 43 && this.silmalukuNyt == 6) {
            return siirraAloitusruudusta(nappula);
        } else {
            // jos yritetään liikkua ulos pelilaudalta - TODO: kaikille omat rajat!
            if (tutkittavaSijainti > 43) {
                seuraavanVuoro();
                return -1;
            } else {
                
                Ruutu tutkittavaRuutu = this.rengas.get(tutkittavaSijainti);
                // jos yritetään liikkua tyhjään ruutuun tai omaan maaliin
                if (tutkittavaRuutu.getVari() == null || verrokkivari.equals(tutkittavaRuutu.getVari())) {
                    return siirraNappulaRuutuun(nappula, tutkittavaSijainti);
                    // jos ollaan törmäämässä toisen maaliin TODO: fiksaa loikkausbugi!
                } else if (tutkittavaSijainti + 4 < 44) {
                    tutkittavaSijainti += 4;
                    return siirraNappulaRuutuun(nappula, tutkittavaSijainti);
                }
            }
        }

        seuraavanVuoro();
        return -1;

    }

    public int siirraAloitusruudusta(Nappula nappula) {

        int siirrettavaIndeksi = nappula.getPelaaja().getVari().getLahtoruutu();
        siirraNappulaRuutuun(nappula, siirrettavaIndeksi);
        return siirrettavaIndeksi;

    }

    @Override
    public void setSiirtoVuoro(VARI vuorossa, int silmaluku) {
        this.kenenVuoro = vuorossa;
        this.silmalukuNyt = silmaluku;
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

    /**
     * KESKEN! Ongelmana se, mität palautetaan tilanteessa jossa nappula syödään
     * - toinen nappi jää tällöin päivittymättä. Time to refactor!
     * 
     * @param nappula
     * @param indeksi
     * @return päivitettävä indeksi
     */
    
    public int siirraNappulaRuutuun(Nappula nappula, int indeksi) {

        if (!onkoRuudussaNappula(indeksi)) {
            Ruutu sijoitettava = this.rengas.get(indeksi);
            poistaNappulaRuudusta(nappula.getSijainti());
            sijoitettava.asetaNappulaRuutuun(nappula);
            return indeksi;
        } else if (!minkaVarinenNappula(indeksi).equals(nappula.getPelaaja().getVari())) {
                Ruutu sijoitettava = this.rengas.get(indeksi);
                poistaNappulaRuudusta(nappula.getSijainti());
                sijoitettava.asetaNappulaRuutuun(nappula);
                return siirraLahtoruutuun(this.rengas.get(indeksi).getNappula());
        }
        
        return -1;
        
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
     * indeksissä nappulaa. Palauttaa false, mikäli indeksiä ei löydy.
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
     * @see setNappulatValittaviksi(VARI vari, boolean bool)
     *
     */
    @Override
    public void seuraavanVuoro() {

        setNappulatValittaviksi(this.kenenVuoro, false);
        int max = this.pelaajat.size() - 1;
        int i = 0;

        if (this.kenenVuoro.equals(VARI.SININEN)) {
            this.kenenVuoro = VARI.PUNAINEN;
            i = 1;
        } else if (this.kenenVuoro.equals(VARI.PUNAINEN)) {
            this.kenenVuoro = VARI.KELTAINEN;
            i = 2;
        } else if (this.kenenVuoro.equals(VARI.KELTAINEN)) {
            this.kenenVuoro = VARI.VIHREA;
            i = 3;
        } else if (this.kenenVuoro.equals(VARI.VIHREA)) {
            this.kenenVuoro = VARI.SININEN;
            i = 0;
        }

        if (i > max) {
            this.kenenVuoro = VARI.SININEN;
        }

        System.out.println(this.kenenVuoro);
        setNappulatValittaviksi(this.kenenVuoro, true);

    }

    // ei valmis
    @Override
    public int siirraLahtoruutuun(Nappula nappula) {

        int sijaintiEnnen = nappula.getSijainti();
        Ruutu ennen = getRuutu(sijaintiEnnen);
        int ekaAloitusruutu = getEkaAloitusruutu(nappula.getPelaaja().getVari());

        if (ekaAloitusruutu != -1) {
            for (int i = 0; i < 4; i++) {
                Ruutu uusi = this.lahtoruudut.get(ekaAloitusruutu+i);
                if (uusi.getNappula() == null){
                    uusi.asetaNappulaRuutuun(nappula);
                    ennen.poistaNappulaRuudusta();
                    return uusi.getSijainti();
                }
            }
        }
        return -1;
        

    }

    /**
     *
     * Asettaa annetun värin nappulat valittaviksi tai ei-valittaviksi
     * parametrin mukaan.
     *
     * @param vari minkä värin nappulat asetetaan
     * @param bool true asettaa valittaviksi, false ei-valittaviksi
     */
    public void setNappulatValittaviksi(VARI vari, boolean bool) {

        Pelaaja pelaaja = getPelaaja(vari);
        System.out.println("bool: " + pelaaja.getVari() + " true/false: " + bool);

        for (Nappula nappula : pelaaja.getNappulat()) {
            getRuutu(nappula.getSijainti()).setOnkoValittava(bool);
            System.out.println("nappula ruudussa : " + nappula.getSijainti() + " check : " + getRuutu(nappula.getSijainti()).getSijainti() + " bool :" + bool);
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

    /**
     *
     * @return aloitusruudut
     */
    public HashMap<VARI, ArrayList<Integer>> getAloitusruudut() {
        return this.aloitusruudut;
    }

    /**
     *
     * @return nopan silmäluku väliltä 1-6
     */
    @Override
    public int heitaNoppaa() {
        return this.noppa.heitaNoppaa();
    }

    /**
     *
     * @return
     */
    @Override
    public VARI kenenVuoro() {
        return this.kenenVuoro;
    }

    /**
     *
     * Palauttaa parametrina annetun indeksin ruudun. Käytännössä etsii sen joko
     * tavallisten ruutujen tai lähtöruutujen joukosta.
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

    public int silmalukuNyt() {
        return this.silmalukuNyt;
    }

    public int getEkaAloitusruutu(VARI vari) {

        if (vari.equals(SININEN)) {
            return 44;
        } else if (vari.equals(PUNAINEN)) {
            return 48;
        } else if (vari.equals(KELTAINEN)) {
            return 52;
        } else if (vari.equals(VIHREA)) {
            return 56;
        }

        return -1;
    }

}
