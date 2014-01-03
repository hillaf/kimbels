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
    private boolean sisaltaaNappulan;
    int size;

    public PyoreaNappi(int x, int y, Color vari) {
        this.x = x;
        this.y = y;
        this.vari = vari;
        this.borderVari = vari;
        this.sisaltaaNappulan = false;
        this.size = 35;

        setContentAreaFilled(false);
    }

    public PyoreaNappi(int x, int y, Color vari, int size) {
        this.x = x;
        this.y = y;
        this.vari = vari;
        this.borderVari = vari;
        this.sisaltaaNappulan = false;
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
        return this.sisaltaaNappulan;
    }
    
    public void piirraNappula(){
        this.borderVari = this.vari;
        this.vari = Color.DARK_GRAY;
    }
    
    public void asetaKlikattavaksi(){
        this.sisaltaaNappulan = true;
        this.borderVari = Color.BLACK;
    }

  
}
