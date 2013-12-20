/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author hilla
 */
public class Pelilauta {

    private HashMap<Integer, Ruutu> rengas;
    private ArrayList<Pelaaja> pelaajat;
    private HashMap<Integer, Ruutu> lahtoruudut;
    private Noppa noppa;
    

    public Pelilauta() {
        this.rengas = new HashMap<Integer, Ruutu>();
        this.lahtoruudut = new HashMap<Integer, Ruutu>();
        this.pelaajat = new ArrayList<Pelaaja>();
        this.noppa = new Noppa();
    }

    public void luoRuudut() {
        for (int i = 0; i < 44; i++) {
            this.rengas.put(i, new Ruutu(i));
        }

        for (int i = 44; i < 60; i++) {
            this.lahtoruudut.put(i, new Ruutu(i));
        }

    }

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

    // lähtöruudut kesken!
    public void luoNappulat(Pelaaja pelaaja) {

        ArrayList<Nappula> nappulat = new ArrayList<Nappula>();

        for (int i = 0; i < 4; i++) {
            Nappula nappula = new Nappula(pelaaja, pelaaja.getVari().getLahtoruutu());
            nappulat.add(nappula);
        }

        pelaaja.setNappulat(nappulat);
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
}
