/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import sovelluslogiikka.KimbleLogiikka;
import sovelluslogiikka.VARI;

/**
 *
 * Käyttöliittymä/piirtäjä. Huolehtii ruutujen ja nappuloiden piirtämisestä.
 *
 *
 * @author hilla
 */
public class Kayttoliittyma implements Runnable {

    /**
     * frame, sisältää panel ja vuoroteksti
     */
    private JFrame frame;

    /**
     * panel: tähän lisätään ruudut ja noppa
     */
    private JPanel panel;

    /**
     * Lista paneliin lisätyistä PyoreaNappi-olioista (ruudut).
     */
    private HashMap<Integer, PyoreaNappi> nappilista;

    /**
     * Apumuuttuja ruutujen piirtämiseen, x-koordinaatti. Tämän voisi
     * refaktoroida metodien sisälle passailtavaksi.
     */
    private int x;

    /**
     * Apumuuttuja ruutujen piirtämiseen, y-koordinaatti. Tämän voisi
     * refaktoroida myös metodien sisälle passailtavaksi.
     */
    private int y;

    /**
     * Rajapinta sovelluslogiikkaan.
     */
    private final KimbleLogiikka logiikka;

    /**
     * JLabel joka sisältää tämänhetkisen tilanteen: kenen vuoro ja paljonko
     * nopasta on viimeksi heitetty.
     */
    private JLabel vuoroteksti;

    /**
     * Konstruktori saa parametrina rajapinnan sovelluslogiikkaan.
     *
     * @param pelilauta
     */
    public Kayttoliittyma(KimbleLogiikka pelilauta) {
        this.logiikka = pelilauta;
    }

    /**
     * run()-metodi alustaa komponentit ja listan ja asettaa framen speksit.
     * Kutsuu luoKomponentit()-metodia ja asettaa framen näkyville.
     *
     * @see luoKomponentit()
     */
    @Override
    public void run() {
        this.frame = new JFrame("Kimbels 1.0");
        this.panel = new JPanel();
        this.vuoroteksti = new JLabel();
        this.nappilista = new HashMap<Integer, PyoreaNappi>();

        this.frame.setBackground(Color.WHITE);
        this.frame.setLayout(new BorderLayout());
        this.frame.setPreferredSize(new Dimension(1000, 700));
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit();

        this.frame.pack();
        this.frame.setVisible(true);
    }

    /**
     *
     * Luo komponentit peliin. Määrittelee panelin ja vuorotekstin speksit. Luo
     * nopan ja kutsuu metodia piirraRuudut(). Asettelee paneli ja vuoro-
     * tekstin frameen.
     *
     * @see luoNoppa()
     * @see piirraRuudut()
     *
     */
    public void luoKomponentit() {
        this.panel.setBackground(Color.WHITE);
        this.panel.setLayout(null);
        this.vuoroteksti.setBackground(Color.WHITE);
        this.vuoroteksti.setLayout(new BorderLayout());
        this.vuoroteksti.setPreferredSize(new Dimension(280, 200));

        luoNoppa();
        piirraRuudut();

        this.frame.getContentPane().add(this.panel, BorderLayout.CENTER);
        this.frame.getContentPane().add(vuoroteksti, BorderLayout.EAST);
    }

    /**
     * Luo peliin nopan.
     */
    public void luoNoppa() {
        JButton noppa = new PyoreaNappi(0, 0, Color.PINK, 50);
        noppa.setText("!");
        noppa.addActionListener(new NopanKuuntelija(noppa, this.logiikka, this));
        this.panel.add(noppa);
        noppa.setBounds(340, 290, 52, 52);
    }

    /**
     * Huolehtii ruutujen piirtämisen hallinnoinnista. Aloittaa piirtämisen
     * kohdasta x, y. Kutsuu ensin neutraaleille ruuduille metodia
     * piirraOvaali() ja tämän jälkeen maali- ja lähtöruuduille
     * piirraRuudutVarille()-metodia. X:n ja y:n muutokset tapahtuvat näissä
     * metodeissa.
     *
     * @see #piirraVarittomatRuudut(int, int, int)
     * @see piirraRuudutVarille(int xi, int yi, VARI vari, int i)
     */
    public void piirraRuudut() {

        this.vuoroteksti.setText(this.logiikka.kenenVuoro() + ", HEITÄ NOPPAA");
        this.x = 100;
        this.y = 150;

        for (int i = 0; i < 44; i++) {
            piirraVarittomatRuudut(this.x, this.y, i);
        }

        //maaliruudut
        piirraRuudutVarille(Color.RED, 190, 460, true, false, 7);
        piirraRuudutVarille(Color.YELLOW, 510, 460, false, false, 18);
        piirraRuudutVarille(Color.GREEN, 510, 140, false, true, 29);
        piirraRuudutVarille(Color.BLUE, 190, 140, true, true, 40);

        //lähtöruudut
        piirraRuudutVarille(Color.BLUE, 50, 120, true, false, 44);
        piirraRuudutVarille(Color.RED, 50, 480, true, true, 48);
        piirraRuudutVarille(Color.YELLOW, 650, 480, false, true, 52);
        piirraRuudutVarille(Color.GREEN, 650, 120, false, false, 56);

    }

    /**
     *
     * Luo harmaan ruudun annettuun sijaintiin x, y. i on ruudun indeksi, joka
     * annetaan parametrina luoRuutu()-metodille.
     *
     * @param xi sijainti x
     * @param yi sijainti y
     * @param i ruudun indeksi
     * @see luoRuutu(int xi, int yi, Color vari, int i)
     */
    public void piirraVarittomatRuudut(int xi, int yi, int i) {

        
        Color vari = Color.LIGHT_GRAY;

        if (onkoRuutuVariton(i)) {
            luoRuutu(xi, yi, vari, i);
            asetaX(i);
            asetaY(i);
        }
    }

    /**
     * Tarkistaa onko ruutu väritön.
     *
     * @param indeksi tutkittavan ruudun indeksi
     * @return true jos väritön, false jos sininen/punainen/jne.
     */

    public boolean onkoRuutuVariton(int indeksi) {
        return this.logiikka.getRuutu(indeksi).getVari() == null;
    }

    /**
     *
     * Luo annetun värisen PyoreaNappi-olion annettuun sijaintiin x, y. i on
     * ruudun indeksi. Selvittää logiikalta onko ruudussa nappulaa ja onko sitä
     * mahdollista siirtää (onko kyseessä vuorossaolevan pelaajan nappula). VOIS
     * TEHDÄ: tee tästä oma metodinsa sovelluslogiikkaan. Luo myös
     * tapahtumankuuntelijan. Toisaalta tätä settiä kutsutaan vain kerran pelin
     * alussa..
     *
     *
     * @param xi sijanti x
     * @param yi sijainti y
     * @param vari ruudun väri
     * @param i ruudun indeksi
     */
    public void luoRuutu(int xi, int yi, Color vari, int i) {

        PyoreaNappi nappi = new PyoreaNappi(0, 0, vari, this.logiikka.getRuutu(i), this.logiikka, Kayttoliittyma.this);
        nappi.maarittele();
        this.nappilista.put(i, nappi);

        this.logiikka.getRuutu(i).setOnkoValittava(false);

        nappi.addActionListener(new KlikkausKuuntelija(nappi));
        this.panel.add(nappi);
        nappi.setBounds(xi, yi, 40, 40);

    }

    /**
     * Huonosti nimetty metodi, joka laskee ruudun indeksin perusteella, mihin
     * kohtaan seuraava ruutu piirretään. En uskalla koskea tähän enää.
     *
     * @param i ruudun indeksi
     */
    public void asetaY(int i) {
        if (i < 5 && i > -1) {
            this.y += 60;
        } else if (i > 15 && i < 18) {
            this.y -= 50;
            this.x += 50;
        } else if (i > 21 && i < 27) {
            this.y -= 60;
        } else if (i > 37 && i < 40) {
            this.y += 50;
            this.x -= 50;
        }
    }

    /**
     * Myös huonosti nimetty metodi, joka laskee ruudun indeksin perusteella,
     * mihin kohtaan seuraava ruutu piirretään. En uskalla koskea tähän enää,
     * tää on jotain woodoota.
     *
     * @param i ruudun indeksi
     */
    public void asetaX(int i) {
        if (i < 7 && i > 4) {
            this.x += 50;
            this.y += 50;
        } else if (i > 10 && i < 16) {
            this.x += 60;
        } else if (i > 26 && i < 29) {
            this.x -= 50;
            this.y -= 50;
        } else if (i > 32 && i < 38) {
            this.x -= 60;
        }

    }

    /**
     * Piirtää neljä annetun väristä ruutua lähtien kohdasta x, y. Piirtää uuden
     * ruudun annettujen boolean-muuttujien mukaan.
     *
     * @param vari ruudun väri
     * @param xr ensimmäinen x-koordinaatti
     * @param yr ensimmäinen y-koordinaatti
     * @param xKasvaa kasvaako x - jos false, x pienenee
     * @param yKasvaa kasvaako y - jos false, y pienenee
     * @param indeksi ruudun indeksi
     */
    public void piirraRuudutVarille(Color vari, int xr, int yr, boolean xKasvaa, boolean yKasvaa, int indeksi) {

        for (int i = indeksi; i < indeksi + 4; i++) {
            luoRuutu(xr, yr, vari, i);
            if (xKasvaa) {
                xr += 40;
            } else {
                xr -= 40;
            }
            if (yKasvaa) {
                yr += 40;
            } else {
                yr -= 40;
            }

        }
    }

    /**
     * Päivittää vuorotekstin.
     */
    public void paivitaVuoroteksti() {

        if (this.logiikka.onkoVoittanutPelin(this.logiikka.kenenVuoro())) {
            this.vuoroteksti.setText(this.logiikka.kenenVuoro() + " VOITTI PELIN!");
        } else {

            if (this.logiikka.onkoHeittovuoro()) {
                this.vuoroteksti.setText(this.logiikka.kenenVuoro() + ", HEITÄ NOPPAA");
            } else {
                this.vuoroteksti.setText(this.logiikka.kenenVuoro() + ", HEITIT " + this.logiikka.silmalukuNyt() + ", SIIRRÄ NAPPULAASI");
            }

        }

    }

    public JPanel getPelilauta() {
        return this.panel;
    }

    public HashMap<Integer, PyoreaNappi> getNappilista() {
        return this.nappilista;
    }

}
