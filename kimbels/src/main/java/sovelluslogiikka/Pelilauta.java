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

/**
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

    public Pelilauta() {
        this.rengas = new HashMap<Integer, Ruutu>();
        this.lahtoruudut = new HashMap<Integer, Ruutu>();
        this.pelaajat = new ArrayList<Pelaaja>();
        this.noppa = new Noppa();
        this.varit = new ArrayList<VARI>();
        this.aloitusruudut = new HashMap<VARI, ArrayList<Integer>>();

        varit.add(VARI.SININEN);
        varit.add(VARI.PUNAINEN);
        varit.add(VARI.KELTAINEN);
        varit.add(VARI.VIHREA);
        
    }

    @Override
    public void luoRuudut() {

        for (int i = 0; i < 44; i++) {
            this.rengas.put(i, new Ruutu(i, VARI.NEUTRAALI));
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

    public void luoLahtoruudut() {

        int k = 0;
        int j = 0;
        ArrayList<Integer> lista = new ArrayList<Integer>();
        
        for (int i = 44; i < 60; i++) {
            if (k == 4) {
                this.aloitusruudut.put(varit.get(j), lista);
                k = 0;
                j++;
            }
            this.lahtoruudut.put(i, new Ruutu(i, varit.get(j)));
            lista.add(i);
            k++;
        }
        this.aloitusruudut.put(varit.get(j), lista);
    }

    @Override
    public void luoPelaajat(int pelaajienMaara, ArrayList<String> nimet) {
        int i = 0;

        for (VARI vari : VARI.values()) {

            if (!vari.equals(VARI.NEUTRAALI) && i < pelaajienMaara) {
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

    // lähtöruudut kesken!
 
    public void luoNappulat(Pelaaja pelaaja) {

        ArrayList<Nappula> nappulat = new ArrayList<Nappula>();

        for (int i = 0; i < 4; i++) {
            Nappula nappula = new Nappula(pelaaja, pelaaja.getVari().getLahtoruutu());
            nappulat.add(nappula);
        }

        asetaNappulatAloitusruutuihin(nappulat, pelaaja.getVari());
        pelaaja.setNappulat(nappulat);
    }
    
    public void asetaNappulatAloitusruutuihin(ArrayList<Nappula> nappulat, VARI vari){
        
        for (Nappula nappula : nappulat) {
            
            for (Integer indeksi : this.aloitusruudut.get(vari)) {
                this.lahtoruudut.get(indeksi).asetaNappulaRuutuun(nappula);
            }
        }
    }

    @Override
    public boolean siirraNappulaa(Nappula nappula, int askeleita) {

        VARI verrokkivari = nappula.getPelaaja().getVari();
        int sijaintiNyt = nappula.getSijainti();


        int tutkittavaSijainti = sijaintiNyt + askeleita;

        if (tutkittavaSijainti > 43) {
            return false;
        } else {

            Ruutu tutkittavaRuutu = this.rengas.get(tutkittavaSijainti);

            if (verrokkivari.equals(tutkittavaRuutu.getVari()) || tutkittavaRuutu.getVari().equals(VARI.NEUTRAALI)) {
                return tutkittavaRuutu.asetaNappulaRuutuun(nappula);
            }
        }

        return false;

    }
    
    @Override
    public VARI minkaVarinenNappula(int i){
        
        if (this.rengas.containsKey(i)){
            if (onkoRuudussaNappula(i)){
                return this.rengas.get(i).getNappula().getPelaaja().getVari();
            }
        }
        
        if (this.lahtoruudut.containsKey(i)){
            if (onkoRuudussaNappula(i)){
                return this.lahtoruudut.get(i).getNappula().getPelaaja().getVari();
            }
        }
        
        return VARI.NEUTRAALI;
    }
    
    @Override
    public boolean onkoRuudussaNappula(int i){
        
        if (this.rengas.containsKey(i)){
            if (this.rengas.get(i).getNappula() != null){
                return true;
            }
        }
        
        if (this.lahtoruudut.containsKey(i)){
            if (this.lahtoruudut.get(i).getNappula() != null){
                return true;
            }
        }
        
        return false;
        
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
        
    }
    
    public HashMap<VARI, ArrayList<Integer>> getAloitusruudut(){
        return this.aloitusruudut;
    }
    
    @Override
    public int heitaNoppaa(){
        return this.noppa.heitaNoppaa();
    }
}
