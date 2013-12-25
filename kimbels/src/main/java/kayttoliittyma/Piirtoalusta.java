/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author hilla
 */
public class Piirtoalusta extends JPanel {

    private int x;
    private int y;

    public Piirtoalusta() {
        super.setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        this.x = 0;
        this.y = 120;

        for (int i = 0; i < 44; i++) {
            piirraOvaali(this.x, this.y, i, graphics);
        }
        
        
        piirraMaaliruudutVarille(Color.RED, 120, 480, graphics, true, false);
        piirraMaaliruudutVarille(Color.YELLOW, 480, 480, graphics, false, false);
        piirraMaaliruudutVarille(Color.GREEN, 480, 120, graphics, false, true);
        piirraMaaliruudutVarille(Color.BLUE, 120, 120, graphics, true, true);
        
    }
    
    

    public void piirraOvaali(int x, int y, int i, Graphics graphics) {

        graphics.setColor(valitseVari(i));

        if ((i < 7 && i > -1) || (i > 10 && i < 18) || (i > 21 && i < 29) || (i > 32 && i < 40)) {
            graphics.fillOval(x, y, 50, 50);
            asetaX(i);
            asetaY(i);
        }

    }

    public void asetaY(int i) {
        if (i < 5 && i > -1) {
            this.y += 70;
        } else if (i > 15 && i < 18) {
            this.y -= 60;
            this.x += 60;
        } else if (i > 21 && i < 27) {
            this.y -= 70;
        } else if (i > 37 && i < 40) {
            this.y += 60;
            this.x -= 60;
        }
    }

    public void asetaX(int i) {
        if (i < 7 && i > 4) {
            this.x += 60;
            this.y += 60;
        } else if (i > 10 && i < 16) {
            this.x += 70;
        } else if (i > 26 && i < 29) {
            this.x -= 60;
            this.y -= 60;
        } else if (i > 32 && i < 38) {
            this.x -= 70;
        }



    }
 
        
        public void piirraMaaliruudutVarille(Color vari, int xr, int yr, Graphics graphics, boolean xKasvaa, boolean yKasvaa){
            graphics.setColor(vari);
            
            for (int i = 0; i < 4; i++) {
                graphics.fillOval(xr, yr, 50, 50);
                if (xKasvaa){
                    xr += 40;
                } else {
                    xr -= 40;
                }
                if (yKasvaa){
                    yr += 40;
                } else {
                    yr -= 40;
                }
            }
        }
        
    

    public Color valitseVari(int i) {

        if (i == 6){
            System.out.println("RED: " + this.x + "   " + this.y);
            return Color.RED;
        }
        if (i == 17){
            System.out.println("YELLOW: " + this.x + "   " + this.y);
            return Color.YELLOW;
        }
        if (i == 28){
            System.out.println("GREEN: " + this.x + "   " + this.y);
            return Color.GREEN;
        }
        
        if (i == 39){
            System.out.println("BLUE: " + this.x + "   " + this.y);
            return Color.BLUE;
        }
        
        return Color.LIGHT_GRAY;
    }
}
