/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author hilla
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    
    public Kayttoliittyma(){
        
    }
    
    @Override
    public void run() {
        this.frame = new JFrame("Kimbels 1.0");
        this.frame.setPreferredSize(new Dimension(1000, 700));
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        luoKomponentit(this.frame.getContentPane());
        
        this.frame.pack();
        this.frame.setVisible(true);
    }
    
    public void luoKomponentit(Container container){
        JPanel panel = new JPanel(new GridLayout());
        panel.add(new Piirtoalusta());
        
        container.add(panel);
    }
    
}
