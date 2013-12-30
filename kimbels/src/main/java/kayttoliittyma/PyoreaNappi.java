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
    private Shape shape;

    public PyoreaNappi(int x, int y, Color vari) {
        this.x = x;
        this.y = y;
        this.vari = vari;

        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        
        graphics.setColor(this.vari);
        graphics.fillOval(this.x, this.y, 50, 50);
        super.paintComponent(graphics);

    }

    @Override
    protected void paintBorder(Graphics graphics) {
        graphics.setColor(this.vari);
        graphics.drawOval(this.x, this.y, 50, 50);
    }
    
    

    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }
}
