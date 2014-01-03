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

    private HashMap<Integer, Ruutu> rengas;
    private ArrayList<Pelaaja> pelaajat;
    private HashMap<Integer, Ruutu> lahtoruudut;
    private Noppa noppa;
    private ArrayList<VARI> varit;
    private HashMap<VARI, ArrayList<Integer>> aloitusruudut;
    private VARI kenenVuoro;

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

    public void luoLahtoruudutVarille(int alkuindeksi, VARI vari) {
        ArrayList<Integer> lista = new ArrayList<Integer>();

        for (int i = alkuindeksi; i < alkuindeksi + 4; i++) {
            this.lahtoruudut.put(i, new Ruutu(i, vari));
            lista.add(i);
        }

        this.aloitusruudut.put(vari, lista);
    }

    public void luoLahtoruudut() {

        luoLahtoruudutVarille(44, VARI.SININEN);
        luoLahtoruudutVarille(48, VARI.PUNAINEN);
        luoLahtoruudutVarille(52, VARI.KELTAINEN);
        luoLahtoruudutVarille(56, VARI.VIHREA);

    }

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

    public void luoNappulat(Pelaaja pelaaja) {

        ArrayList<Nappula> nappulat = new ArrayList<Nappula>();

        for (int i = 0; i < 4; i++) {
            Nappula nappula = new Nappula(pelaaja, pelaaja.getVari().getLahtoruutu());
            nappulat.add(nappula);
        }

        asetaNappulatAloitusruutuihin(nappulat, pelaaja.getVari());
        pelaaja.setNappulat(nappulat);
    }

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
     * @return VARI minkä värinen nappula ruudussa
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

    public void poistaNappulaRuudusta(int indeksi) {
        if (this.rengas.containsKey(indeksi)) {
            this.rengas.get(indeksi).poistaNappulaRuudusta();
        }

        if (this.lahtoruudut.containsKey(indeksi)) {
            this.lahtoruudut.get(indeksi).poistaNappulaRuudusta();
        }
    }

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
    
    public void setNappulatValittaviksi(VARI vari, boolean bool){
        
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
    
    public Pelaaja getPelaaja(VARI vari){
        
        for (Pelaaja pelaaja : pelaajat) {
            if (pelaaja.getVari().equals(vari)){
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
