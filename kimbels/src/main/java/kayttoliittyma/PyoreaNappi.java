/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import javax.swing.JButton;
import sovelluslogiikka.KimbleLogiikka;
import sovelluslogiikka.Ruutu;
import sovelluslogiikka.VARI;

/**
 *
 *
 * Pyöreä nappi.
 *
 * @author hilla
 */
public class PyoreaNappi extends JButton {

    /**
     * Sijainti x:n suhteen.
     */
    private final int x;

    /**
     * Sijainti y:n suhteen.
     */
    private final int y;

    /**
     * Ruudun väri. BLUE/RED/YELLOW/GREEN jos ruudussa nappula.
     */
    private Color vari;

    /**
     * Ruudun reunan väri. BLUE/RED/YELLOW/GREEN jos jonkin värin lähtö- tai
     * maaliruutu. LIGHT_GREY jos tyhjä neutraali.
     */
    private Color borderVari;

    /**
     * Napin muoto.
     */
    private Shape shape;

    /**
     * Napin koko (size = leveys = korkeus).
     */
    private final int size;

    /**
     * Sovelluslogiikan ruutu johon nappi liittyy.
     */
    private Ruutu ruutu;

    /**
     * Sovelluslogiikan rajapinta.
     */
    private KimbleLogiikka logiikka;

    /**
     * Käyttöliittymä jonka metodeja nappi kutsuu klikattaessa.
     */
    private Kayttoliittyma kayttoliittyma;


    /**
     * Alustetaan muuttujat.
     *
     * @param x sijainti x
     * @param y sijainti y
     * @param vari napin väri
     * @param ruutu ruutu, johon nappi liittyy
     * @param logiikka sovelluslogiikan rajapinta
     * @param kayttoliittyma käyttöliittymä
     */
    public PyoreaNappi(int x, int y, Color vari, Ruutu ruutu, KimbleLogiikka logiikka, Kayttoliittyma kayttoliittyma) {
        this.x = x;
        this.y = y;
        this.vari = vari;
        this.borderVari = vari;
        this.size = 35;
        this.ruutu = ruutu;
        this.logiikka = logiikka;
        this.kayttoliittyma = kayttoliittyma;

        setContentAreaFilled(false);
    }

    /**
     * Käytetään nopan luontiin. Noppa ei tunne sovelluslogiikkaa tai
     * käyttöliittymää.
     *
     * @param x
     * @param y
     * @param vari
     * @param size
     */
    public PyoreaNappi(int x, int y, Color vari, int size) {
        this.x = x;
        this.y = y;
        this.vari = vari;
        this.borderVari = this.vari.darker();
        this.size = size;

        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics graphics) {

        graphics.setColor(this.vari);
        graphics.fillOval(this.x, this.y, this.size, this.size);
        super.paintComponent(graphics);

    }

    @Override
    protected void paintBorder(Graphics graphics) {
        graphics.setColor(this.borderVari);
        graphics.drawOval(this.x, this.y, this.size, this.size);
    }

    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }

    /**
     * Selvittää ruudulta voiko nappia klikata.
     *
     * @return true jos voi klikata, false jos ei
     */
    public boolean onkoKlikattava() {
        return this.ruutu.onkoValittava();
    }

    /**
     *
     * Kutsutaan, kun nappia klikataan. Kutsuu siirraNappulaa logiikasta ja
     * päivittää palautetussa listassa olleet napit oikean värisiksi.
     */
    public void klikattu() {

        HashMap<Integer, VARI> paivitettavat = this.logiikka.siirraNappulaa(this.ruutu.getNappula(), this.logiikka.silmalukuNyt());

        for (Integer i : paivitettavat.keySet()) {

            if (paivitettavat.get(i) == null) {
                this.kayttoliittyma.getNappilista().get(i).piirraTyhjaRuutu(Color.LIGHT_GRAY);
            } else {
                this.kayttoliittyma.getNappilista().get(i).piirraNappularuutu(paivitettavat.get(i).getColor());
            }

        }

        this.logiikka.seuraavanVuoro();
        kayttoliittyma.paivitaVuoroteksti();

    }
    
    /**
     * Päivitetään lautaa luodessa nappien väritys.
     */

    public void maarittele() {

        if (this.ruutu.getNappula() != null) {
            piirraNappularuutu(vari);
        } else {
            if (this.ruutu.getVari() != null) {
                this.borderVari = this.ruutu.getVari().getColor();
            } else {
                this.borderVari = Color.LIGHT_GRAY;
            }
            this.vari = Color.LIGHT_GRAY;
        }
    }
/**
 * Päivitetään peliä pelatessa ruutu tyhjäksi.
 * @param vari haluttu väri
 */
    
    public void piirraTyhjaRuutu(Color vari) {
        this.vari = vari;
        if(this.ruutu.getVari() != null) {
            this.borderVari = this.ruutu.getVari().getColor();
        } else {
            this.borderVari = this.vari;
        }
        
        this.repaint();
    }
    
    /**
     * Päivitetään peliä pelatessa ruutu nappulalliseksi.
     * @param vari 
     */

    public void piirraNappularuutu(Color vari) {
        this.borderVari = Color.DARK_GRAY;
        this.vari = this.ruutu.getNappula().getPelaaja().getVari().getColor();
        this.repaint();
    }
}
