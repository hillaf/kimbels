/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

import java.util.ArrayList;
import java.util.HashMap;
import static sovelluslogiikka.VARI.*;

/**
 *
 * Pelilauta huolehtii esim. kaikesta. Toimii rajapintana k�ytt�liittym�lle ja
 * huolehtii pelilaudan tapahtumista.
 *
 *
 * @author hilla
 */
public class Pelilauta implements KimbleLogiikka {

    /**
     * Pelilaudan neutraalit ruudut ja maaliruudut. Avaimena integer v�lilt�
     * 0-43, joka on ruudun indeksi. Tietyt indeksit tiettyjen v�rien
     * maaliruutuja:
     *
     * 7-10 SININEN 18-21 PUNAINEN 29-32 KELTAINEN 40-43 VIHREA
     */
    private final HashMap<Integer, Ruutu> rengas;

    /**
     * Lista pelaajista. 0 = SININEN 1 = PUNAINEN 2 = KELTAINEN 3 = VIHREA
     *
     */
    private final ArrayList<Pelaaja> pelaajat;
    /**
     * L�ht�ruudut. Avaimena integer, joka on ruudun indeksi.
     *
     * 44-47 SININEN 48-51 PUNAINEN 52-55 KELTAINEN 56-59 VIHREA
     *
     */
    private final HashMap<Integer, Ruutu> lahtoruudutIndekseittain;

    /**
     * L�ht�ruudut. Avaimena v�ri, joka viittaa listaan l�ht�ruutujen
     * indekseist�.
     */
    private final HashMap<VARI, ArrayList<Integer>> lahtoruudunIndeksitVareittain;

    /**
     * Noppa. Arpoo satunnaisia lukuja 1-6.
     */
    private final Noppa noppa;

    /**
     * Lista pelaajien mahdollisista v�reist�.
     *
     * 0 = SININEN 1 = PUNAINEN 2 = KELTAINEN 3 = VIHREA
     *
     */
    private final HashMap<Integer, VARI> varit;

    /**
     * V�ri, jonka vuoro t�ll� hetkell� on.
     */
    private VARI kenenVuoro;

    /**
     * Viimeksi heitetty nopan silm�luku;
     */
    private int silmalukuNyt;

    /**
     * Kierroksella p�ivitett�v�t ruudut.
     */
    private final HashMap<Integer, VARI> paivitettavatRuudut;

    /**
     * Konstruktorissa alustetaan muuttujat ja asetetaan aloitusvuoro siniselle.
     * Lis�t��n my�s v�rit listaan.
     */
    public Pelilauta() {
        this.rengas = new HashMap<Integer, Ruutu>();
        this.lahtoruudutIndekseittain = new HashMap<Integer, Ruutu>();
        this.pelaajat = new ArrayList<Pelaaja>();
        this.noppa = new Noppa();
        this.varit = new HashMap<Integer, VARI>();
        this.lahtoruudunIndeksitVareittain = new HashMap<VARI, ArrayList<Integer>>();
        this.kenenVuoro = VARI.SININEN;
        this.paivitettavatRuudut = new HashMap<Integer, VARI>();

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
     * Hallinnoi l�ht�ruutujen luontia.
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
     * Luo l�ht�ruudut parametrina annetulle v�rille.
     *
     * @param alkuindeksi v�rin ensimm�inen l�ht�ruutu
     * @param vari v�ri jolle l�ht�ruudut luodaan
     */
    public void luoLahtoruudutVarille(int alkuindeksi, VARI vari) {
        ArrayList<Integer> lista = new ArrayList<Integer>();

        for (int i = alkuindeksi; i < alkuindeksi + 4; i++) {
            this.lahtoruudutIndekseittain.put(i, new Ruutu(i, vari));
            lista.add(i);
        }

        this.lahtoruudunIndeksitVareittain.put(vari, lista);
    }

    /**
     *
     * Luo peliin pelaajat. Pelaajia luodaan 0-4 riippuen parametrista.
     * Pelaajien luonti alkaa v�rill� SININEN. Kutsuu jokaiselle pelaajalle
     * metodia luoNappulat()
     *
     * @see luoNappulat(Pelaaja pelaaja)
     * @param pelaajienMaara montako pelaajaa luodaan
     */
    @Override
    public void luoPelaajat(int pelaajienMaara) {

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
            Nappula nappula = new Nappula(pelaaja);
            nappulat.add(nappula);
        }

        asetaNappulatAloitusruutuihin(nappulat);
        pelaaja.setNappulat(nappulat);
    }

    /**
     *
     * Asettaa parametrina annetut nappulat parametrina annetun v�rin
     * aloitusruutuihin.
     *
     * @param nappulat mitk� nappulat asetetaan
     */
    public void asetaNappulatAloitusruutuihin(ArrayList<Nappula> nappulat) {
        int i = 0;

        for (Integer indeksi : this.lahtoruudunIndeksitVareittain.get(nappulat.get(i).getVari())) {
            this.lahtoruudutIndekseittain.get(indeksi).asetaNappulaRuutuun(nappulat.get(i));
            i++;
        }
    }

    /**
     *
     * Hallinnoi nappulan siirt�mist�. Jos aloitusruudussa, passaa
     * sisarmetodille siirraNappulaaAloitusruudussa joka huolehtii k�yt�nn�ss�
     * samoista asioista paitsi aloitusruuduille. Melkosta copypashaa!
     *
     * @param nappula
     * @param askeleita
     * @return p�ivitett�v�t ruudut
     */
    @Override
    public HashMap<Integer, VARI> siirraNappulaa(Nappula nappula, int askeleita) {

        int tutkittavaSijainti = nappula.getSijainti() + askeleita;
        this.paivitettavatRuudut.put(nappula.getSijainti(), null);

        //jos ollaan aloitusruudussa
        if (ollaanAloitusruudussa(nappula.getSijainti())) {
            return siirraNappulaaAloitusruudussa(nappula, askeleita);
        }

        // jos meinaa k�vell� vastustajan maaliin, k�vell��n ohi
        if (valissaOnMuidenMaaliruutuja(nappula.getSijainti(), nappula.getVari(), askeleita)) {
            tutkittavaSijainti += 4;
        }

        // jos ruudussa oma nappula tai pelilauta loppuu ei liikuta
        if (ruutuunVoiLiikkua(nappula, tutkittavaSijainti, nappula.getVari())) {
            tutkittavaSijainti = tutkiIndeksiHyppays(nappula, tutkittavaSijainti);
            siirraNappulaRuutuun(nappula, tutkittavaSijainti);
            this.paivitettavatRuudut.put(tutkittavaSijainti, nappula.getVari());
        } else {
            this.paivitettavatRuudut.put(nappula.getSijainti(), nappula.getVari());
        }

        return this.paivitettavatRuudut;
    }

    /**
     * Hurjan nasevasti indeksoidun Kimble-laudan ongelmana on se, ett�
     * nappuloiden tulisi p��st� kivasti kiert�m��n lautaa ymp�ri (ei kuitenkaan
     * omista maaleistaan eteenp�in jne.). T�m� metodi tarkistaa indeksien rajat
     * ja palauttaa uuden indeksin jos tarvetta.
     *
     * @param nappula
     * @param alkuperainenIndeksi
     * @return
     */
    public int tutkiIndeksiHyppays(Nappula nappula, int alkuperainenIndeksi) {

        if (alkuperainenIndeksi >= this.rengas.size()) {
            nappula.setOhittiNollan(true);
            return (alkuperainenIndeksi - rengas.size());
        } else {
            return alkuperainenIndeksi;
        }

    }

    /**
     * Huolehtii nappulan siirtämisestä aloitusruudusta.
     * @param nappula nappula jota siirretään
     * @param askelia nopan silmäluku, siirretään aloitusruudusta vain jos == 6
     * @return lista päivitettävistä ruuduista
     */
    
    public HashMap<Integer, VARI> siirraNappulaaAloitusruudussa(Nappula nappula, int askelia) {

        if (askelia == 6) {
            if (!onkoRuudussaOmaNappula(nappula.getVari().getLahtoruutu(), nappula.getVari())) {
                this.paivitettavatRuudut.put(siirraAloitusruudusta(nappula), nappula.getVari());
                return this.paivitettavatRuudut;
            }
        }

        this.paivitettavatRuudut.put(nappula.getSijainti(), nappula.getVari());
        return this.paivitettavatRuudut;
    }

    /**
     * Ollaanko aloitusruudussa.
     * 
     * @param indeksi tutkittava ruutu
     * @return true jos indeksi on aloitusruutu, false jos ei
     */
    
    public boolean ollaanAloitusruudussa(int indeksi) {
        return indeksi >= rengas.size();
    }
    
    /**
     * Tutkii voiko ruutuun liikkua.
     * @param nappula liikutettava nappula
     * @param indeksi ruutu johon ollaan liikkumassa
     * @param nappulanVari liikutettavan nappulan väri
     * @return true jos voidaan liikkua, false jos ei
     */

    public boolean ruutuunVoiLiikkua(Nappula nappula, int indeksi, VARI nappulanVari) {
        return !meneekoYliLaudalta(nappula, indeksi, nappulanVari) && !onkoRuudussaOmaNappula(indeksi, nappulanVari);

    }
    
    /**
     * Tutkitaan onko nappula jo maalissa eikä voi liikkua yli laudalta.
     * @param nappula liikutettava nappula
     * @param indeksi tutkittavan ruudun indeksi
     * @param nappulanVari nappulan väri
     * @return true jos menee yli laudalta, false jos ei
     */

    public boolean meneekoYliLaudalta(Nappula nappula, int indeksi, VARI nappulanVari) {

        if (nappulanVari.equals(VARI.SININEN)) {
            if (indeksi >= rengas.size()) {
                return true;
            }
        } else if (indeksi > nappulanVari.getLahtoruutu() - 1 && nappula.onkoOhittanutNollan()) {
            return true;
        }

        return false;

    }
    
    /**
     * Tutkii onko ruudussa pelaajan oma toinen nappula
     * @param indeksi tutkittava indeksi
     * @param nappulanVari pelaajan väri
     * @return true jos ruudussa on oma nappula, false jos ei
     */

    public boolean onkoRuudussaOmaNappula(int indeksi, VARI nappulanVari) {

        if (onkoRuudussaNappula(indeksi)) {
            return (minkaVarinenNappula(indeksi).equals(nappulanVari));
        } else {
            return false;
        }
    }
    
    /**
     * Tutkii joutuuko nappula "hyppäämään" toisen pelaajan maaliruutujen yli
     * eli ohittamaan ne pelilaudalla. Täyttä indeksipaskaa!
     * 
     * @param sijainti nappulan sijainti
     * @param nappulanVari nappulan väri
     * @param askeleita montako askelta liikutaan
     * @return true jos liikutaan toisen maalin ohi, false jos ei
     */

    public boolean valissaOnMuidenMaaliruutuja(int sijainti, VARI nappulanVari, int askeleita) {

        for (int i = sijainti + 1; i <= sijainti + askeleita; i++) {
            if (i >= this.rengas.size()) {
                return false;
            }
            if (this.rengas.get(i).getVari() != null && nappulanVari != this.rengas.get(i).getVari()) {
                return true;
            }
        }

        return false;
    }
    
    /**
     * Palauttaa annetun indeksin värin.
     * @param tutkittavaIndeksi tutkittavan ruudun indeksi
     * @return ruudun väri, null jos väritön
     */

    public VARI tutkiSijainninVari(int tutkittavaIndeksi) {

        if (getRuutu(tutkittavaIndeksi) != null) {
            return getRuutu(tutkittavaIndeksi).getVari();
        } else {
            return null;
        }
    }
    
    /**
     * Siirtää nappulan kotipesästä pelaajan aloitusruutuun.
     * @param nappula siirrettävä nappula
     * @return indeksi, johon nappula juuri siirrettiin
     */

    public int siirraAloitusruudusta(Nappula nappula) {

        int siirrettavaIndeksi = nappula.getPelaaja().getVari().getLahtoruutu();
        siirraNappulaRuutuun(nappula, siirrettavaIndeksi);
        return siirrettavaIndeksi;

    }

    /**
     * Asettaa parametrina annetun pelaajan ja silmäluvun laudalle.
     * @param vuorossa vuorossaoleva väri
     * @param silmaluku nopan uusi silmäluku
     */
    @Override
    public void setVuorossaolijaJaSilmaluku(VARI vuorossa, int silmaluku) {
        this.kenenVuoro = vuorossa;
        this.silmalukuNyt = silmaluku;
    }

    /**
     * Metodi palauttaa ruudussa olevan nappulan v�rin. Metodi saa parametrina
     * ruudun indeksin.
     *
     * @param i parametrina ruudun indeksi
     *
     * @return VARI mink� v�rinen nappula ruudussa - palauttaa null mik�li
     * indeksi� ei l�ydy
     */
        public VARI minkaVarinenNappula(int i) {

        if (this.rengas.containsKey(i)) {
            if (onkoRuudussaNappula(i)) {
                return this.rengas.get(i).getNappula().getPelaaja().getVari();
            }
        }

        if (this.lahtoruudutIndekseittain.containsKey(i)) {
            if (onkoRuudussaNappula(i)) {
                return this.lahtoruudutIndekseittain.get(i).getNappula().getPelaaja().getVari();
            }
        }

        return null;
    }

    /**
     *
     *
     * @param nappula
     * @param indeksi
     * @return p�ivitett�v� indeksi
     */
    public int siirraNappulaRuutuun(Nappula nappula, int indeksi) {

        Ruutu sijoitettava = this.rengas.get(indeksi);

        if (!onkoRuudussaNappula(indeksi)) {
            poistaNappulaRuudusta(nappula.getSijainti());
        } else if (!minkaVarinenNappula(indeksi).equals(nappula.getPelaaja().getVari())) {
            siirraLahtoruutuun(this.rengas.get(indeksi).getNappula());
            poistaNappulaRuudusta(nappula.getSijainti());
        }

        sijoitettava.asetaNappulaRuutuun(nappula);
        return indeksi;

    }

    /**
     *
     * Poistaa nappulan ruudusta, joka liittyy parametrina annettuun indeksiin.
     * Mik�li indeksi� ei l�ydy, ei poisteta mit��n.
     *
     * @param indeksi mist� indeksist� poistetaan nappula
     */
    public void poistaNappulaRuudusta(int indeksi) {
        if (this.rengas.containsKey(indeksi)) {
            this.rengas.get(indeksi).poistaNappulaRuudusta();
        }

        if (this.lahtoruudutIndekseittain.containsKey(indeksi)) {
            this.lahtoruudutIndekseittain.get(indeksi).poistaNappulaRuudusta();
        }
    }

    /**
     *
     * Palauttaa boolean-arvon, joka kertoo onko parametrina annetussa
     * indeksiss� nappulaa. Palauttaa false, mik�li indeksi� ei l�ydy.
     *
     * @param i indeksi johon liittyv�� ruutua tutkitaan
     * @return true mik�li indeksin ruudusta l�ytyy nappula, muuten false
     */
        public boolean onkoRuudussaNappula(int i) {

        if (this.rengas.containsKey(i)) {
            if (this.rengas.get(i).getNappula() != null) {
                return true;
            }
        }

        if (this.lahtoruudutIndekseittain.containsKey(i)) {
            if (this.lahtoruudutIndekseittain.get(i).getNappula() != null) {
                return true;
            }
        }

        return false;

    }

    /**
     *
     * Siirt�� vuoron seuraavalle pelaajalle. Kutsuu metodia
     * setNappulatValittaviksi kahdesti, edellisen vuoron vuorossaolleelle
     * pelaajalle ja uuden vuoron pelaajalle. Huomioi pelaajien m��r�n.
     *
     * @see setNappulatValittaviksi(VARI vari, boolean bool)
     *
     */
    @Override
    public void seuraavanVuoro() {

        setNappulatValittaviksi(this.kenenVuoro, false);

        if (this.onkoVoittanutPelin(this.kenenVuoro) == false) {
            if (this.silmalukuNyt != 6) {
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
            }

            this.noppa.onkoKlikattava(true);
        }

    }
    
    /**
     * Palauttaa annetun värin maaliruudun, jonka indeksi on pienin.
     * @param vari annettu väri
     * @return pelaajan ensimmäinen maaliruutu
     */

    public int getEnsimmainenMaaliruutu(VARI vari) {

        if (vari.equals(SININEN)) {
            return this.rengas.size() - 4;
        } else {
            return vari.getLahtoruutu() - 4;
        }
    }

    
    /**
     * Tutkii onko annettu pelaaja voittanut pelin.
     * @param vari pelaaja jota tutkitaan
     * @return true jos pelaaja on voittanut, false jos ei
     */
    @Override
    public boolean onkoVoittanutPelin(VARI vari) {

        int maaliAlkaa = getEnsimmainenMaaliruutu(vari);
        for (int i = maaliAlkaa; i < maaliAlkaa + 4; i++) {
            if (onkoRuudussaNappula(i) == false) {
                return false;
            }

        }
        return true;
    }

    /**
     * 
     * Vähän taas pitkäksi venähtänyt metodi joka siirtää syödyn nappulan
     * takaisin aloitusruutuun. 
     * 
     * @param nappula palautettava nappula
     * @return true jos onnistui, false jos ei
     */
        public boolean siirraLahtoruutuun(Nappula nappula) {

        nappula.setOhittiNollan(false);
        int sijaintiEnnen = nappula.getSijainti();
        Ruutu ennen = getRuutu(sijaintiEnnen);
        int ekaAloitusruutu = getEkaAloitusruutu(nappula.getPelaaja().getVari());

        if (ekaAloitusruutu != -1) {
            for (int i = 0; i < 4; i++) {
                Ruutu uusi = this.lahtoruudutIndekseittain.get(ekaAloitusruutu + i);
                if (uusi.getNappula() == null) {
                    uusi.asetaNappulaRuutuun(nappula);
                    ennen.poistaNappulaRuudusta();
                    this.paivitettavatRuudut.put(uusi.getSijainti(), uusi.getVari());
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * Palauttaa true jos on heittovuoro (eli ei siirtovuoro).
     *
     * @return
     */
    @Override
    public boolean onkoHeittovuoro() {
        return this.noppa.onkoKlikattava();
    }
    
    /**
     * Asettaa nopan ei-klikattavaksi ja vuorossaolevan pelaajan nappulat
     * klitattaviksi.
     */

    @Override
    public void setSiirtymisvuoro() {
        this.noppa.onkoKlikattava(false);
        setNappulatValittaviksi(this.kenenVuoro, true);
    }

    /**
     *
     * Asettaa annetun v�rin nappulat valittaviksi tai ei-valittaviksi
     * parametrin mukaan.
     *
     * @param vari mink� v�rin nappulat asetetaan
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
        return this.lahtoruudutIndekseittain;
    }

    /**
     * Palauttaa annetun v�rin pelaajan.
     *
     * @param vari mink� v�rin pelaaja
     * @return Pelaaja jonka v�ri on parametrina annettu v�ri. Palauttaa null
     * jos t�llaista pelaajaa ei l�ydy.
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
        return this.lahtoruudunIndeksitVareittain;
    }

    /**
     *
     * @return nopan silm�luku v�lilt� 1-6
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
     * Palauttaa parametrina annetun indeksin ruudun. K�yt�nn�ss� etsii sen joko
     * tavallisten ruutujen tai l�ht�ruutujen joukosta.
     *
     * @param indeksi
     * @return
     */
    @Override
    public Ruutu getRuutu(int indeksi) {
        if (this.rengas.containsKey(indeksi)) {
            return this.rengas.get(indeksi);
        }

        if (this.lahtoruudutIndekseittain.containsKey(indeksi)) {
            return this.lahtoruudutIndekseittain.get(indeksi);
        }

        return null;
    }

    @Override
    public int silmalukuNyt() {
        return this.silmalukuNyt;
    }

    /**
     * Palauttaa annetun aloitusruudun, jonka indeksi on pienin.
     * @param vari
     * @return aloitusruudun indeksi
     */
    public int getEkaAloitusruutu(VARI vari) {
        return this.lahtoruudunIndeksitVareittain.get(vari).get(0);
    }

}
