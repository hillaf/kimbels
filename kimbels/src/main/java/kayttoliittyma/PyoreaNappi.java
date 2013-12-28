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
 * @author hilla
 */
public class PyoreaNappi extends JButton {

    private int x;
    private int y;
    private Color vari;

    public PyoreaNappi(int x, int y, Color vari) {
        this.x = x;
        this.y = y;
        this.vari = vari;
        Dimension koko = new Dimension();
        koko.setSize(5, 5);
        setPreferredSize(koko);

        setContentAreaFilled(false);
    }

    protected void paintComponent(Graphics graphics) {
        
        graphics.setColor(this.vari);
        graphics.fillOval(this.x, this.y, getSize().width, getSize().height);
        super.paintComponent(graphics);

    }

    protected void paintBorder(Graphics graphics) {
        graphics.setColor(this.vari);
        graphics.drawOval(this.x, this.y, getSize().width, getSize().height);
    }
    
    
    Shape shape;

    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }
}
