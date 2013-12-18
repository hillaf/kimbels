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

    HashMap<Integer, Ruutu> rengas;
    ArrayList<Pelaaja> pelaajat;

    public Pelilauta() {
        this.rengas = new HashMap<Integer, Ruutu>();
        this.pelaajat = new ArrayList<Pelaaja>();
    }

    public void luoRuudut() {
        for (int i = 0; i < 28; i++) {
            this.rengas.put(i, new Ruutu(i));
        }
    }

    public void luoPelaajat(int pelaajienMaara, ArrayList<String> nimet) {
        int i = 0;


        for (VARI vari : VARI.values()) {
            if (i < pelaajienMaara) {
                if (nimet.size() <= i) {
                    Pelaaja pelaaja = new Pelaaja(vari);
                    this.pelaajat.add(pelaaja);
                } else {
                    Pelaaja pelaaja = new Pelaaja(nimet.get(i), vari);
                    this.pelaajat.add(pelaaja);
                }
                i++;
            }
        }

    }

    public HashMap<Integer, Ruutu> getRengas() {
        return this.rengas;
    }

    public ArrayList<Pelaaja> getPelaajat() {
        return this.pelaajat;
    }
}
