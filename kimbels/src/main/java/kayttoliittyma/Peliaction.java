/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import sovelluslogiikka.Pelilauta;

/**
 *
 * Hallinnoi alun kyselyjä ja peliä.
 *
 * @author hilla
 */
public class Peliaction implements Runnable {

    private final Kayttoliittyma kayttoliittyma;
    private final Alkukyselyt kyselyt;
    private final Pelilauta pelilauta;

    /**
     * Peliaction alustaa kaikki muut villit luokat.
     */
    public Peliaction() {
        this.pelilauta = new Pelilauta();
        this.kayttoliittyma = new Kayttoliittyma(this.pelilauta);
        this.kyselyt = new Alkukyselyt(this);
    }

    @Override
    public void run() {
        this.kyselyt.run();

    }

    /**
     * Kutsutaan kun Aloituskuuntelija vastaanottaa klikkauksen. Peli alkaa!
     * @param maara pelaajien maara int-muuttujana
     */

    public void setPelaajienMaara(int maara) {
        this.pelilauta.luoRuudut();
        this.pelilauta.luoPelaajat(maara);
        this.kayttoliittyma.run();
    }

}
