/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author hilla
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private JPanel panel;
    private int x;
    private int y;
    
    public Kayttoliittyma(){
        
    }
    
    @Override
    public void run() {
        this.frame = new JFrame("Kimbels 1.0");
        this.panel = new JPanel();
        this.frame.setLayout(new BorderLayout());
        
        
        this.frame.setPreferredSize(new Dimension(1000, 700));
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.panel.setLayout(null);
        
        luoKomponentit();
        
        this.frame.pack();
        this.frame.setVisible(true);
    }
    
    public void luoKomponentit(){
        this.panel.setBackground(Color.WHITE);
        JButton button = new PyoreaNappi(0, 0, Color.BLACK);
        this.panel.add(button);
        button.setBounds(0, 0, 50, 50);
        
        piirra();
        this.frame.getContentPane().add(this.panel);
    }
    
        public void piirra() {

        
        this.x = 0;
        this.y = 120;

        for (int i = 0; i < 44; i++) {
            piirraOvaali(this.x, this.y, i);
        }
        
        
        piirraMaaliruudutVarille(Color.RED, 120, 480, true, false);
        piirraMaaliruudutVarille(Color.YELLOW, 480, 480, false, false);
        piirraMaaliruudutVarille(Color.GREEN, 480, 120, false, true);
        piirraMaaliruudutVarille(Color.BLUE, 120, 120, true, true);
        
    }
    
    

    public void piirraOvaali(int x, int y, int i) {

        Color vari = valitseVari(i);

        if ((i < 7 && i > -1) || (i > 10 && i < 18) || (i > 21 && i < 29) || (i > 32 && i < 40)) {
            JButton nappi = new PyoreaNappi(x, y, vari);
            this.panel.add(nappi);
            nappi.setBounds(x, y, 50, 50);
            System.out.println("i: " + i + " x: " + nappi.getX() + " y: " + nappi.getY());
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
 
        
        public void piirraMaaliruudutVarille(Color vari, int xr, int yr, boolean xKasvaa, boolean yKasvaa){
            
            
            for (int i = 0; i < 4; i++) {
                PyoreaNappi nappi = new PyoreaNappi(xr, yr, vari);
                this.panel.add(nappi);
                nappi.setBounds(xr, yr, 50, 50);
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
