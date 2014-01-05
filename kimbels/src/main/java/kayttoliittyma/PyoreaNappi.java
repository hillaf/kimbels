/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import javax.swing.JButton;
import sovelluslogiikka.KimbleLogiikka;
import sovelluslogiikka.Nappula;
import sovelluslogiikka.Ruutu;

/**
 *
 *
 * Pyöreä nappi.
 *
 * @author hilla
 */
public class PyoreaNappi extends JButton {

    private int x;
    private int y;
    private Color vari;
    private Color borderVari;
    private Shape shape;
    private int size;
    private Ruutu ruutu;
    private KimbleLogiikka logiikka;
    private Kayttoliittyma kayttoliittyma;
    private int indeksi;

    public PyoreaNappi(int x, int y, Color vari, Ruutu ruutu, KimbleLogiikka logiikka, Kayttoliittyma kayttoliittyma, int indeksi) {
        this.x = x;
        this.y = y;
        this.vari = vari;
        this.borderVari = vari;
        this.size = 35;
        this.ruutu = ruutu;
        this.logiikka = logiikka;
        this.kayttoliittyma = kayttoliittyma;
        this.indeksi = indeksi;

        setContentAreaFilled(false);
    }

    public PyoreaNappi(int x, int y, Color vari, int size) {
        this.x = x;
        this.y = y;
        this.vari = vari;
        this.borderVari = vari;
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

    public boolean onkoKlikattava() {
        return this.ruutu.onkoValittava();
    }

    public void klikattu() {

        
        int kohde = this.logiikka.siirraNappulaa(this.ruutu.getNappula(), this.logiikka.silmalukuNyt());

        if (kohde != -1) {
            kayttoliittyma.getNappilista().get(kohde).maalaaVarilla(this.vari);
            this.vari = Color.LIGHT_GRAY;
            kayttoliittyma.getNappilista().get(kohde).repaint();
        } else {
            this.vari = this.ruutu.getNappula().getPelaaja().getVari().getColor();
        }

        kayttoliittyma.paivitaVuoroteksti();

    }

    public void maarittele() {

        if (this.ruutu.getNappula() != null) {
            this.borderVari = this.ruutu.getNappula().getPelaaja().getVari().getColor();
            this.vari = this.ruutu.getNappula().getPelaaja().getVari().getColor();
        } else {
            if (this.ruutu.getVari() != null) {
                this.borderVari = this.ruutu.getVari().getColor();
            } else {
                this.borderVari = Color.LIGHT_GRAY;
            }
            this.vari = Color.LIGHT_GRAY;
        }
    }

    public void maalaaVarilla(Color vari) {
        this.vari = vari;
    }

}
