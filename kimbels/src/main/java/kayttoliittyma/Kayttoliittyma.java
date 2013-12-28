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
    
    public Kayttoliittyma(){
        
    }
    
    @Override
    public void run() {
        this.frame = new Piirtoalusta();
        this.frame.setPreferredSize(new Dimension(1000, 700));
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        luoKomponentit(this.frame.getContentPane());
        
        this.frame.pack();
        this.frame.setVisible(true);
    }
    
    public void luoKomponentit(Container container){
        
        
    }
    
    
}
