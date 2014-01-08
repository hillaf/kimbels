/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

/**
 *
 * Pelin alussa oleva kysely: montako pelaajaa pelaa.
 * 
 * @author hilla
 */
public class Alkukyselyt implements Runnable {
    /**
     * Frame.
     */
    private JFrame frame;
    
    /**
     * Panel, johon nappulat lisätään.
     */
    private JPanel panel;
    
    /**
     * Peliaction, annetaan parametrina tapahtumankuuntelijalle.
     */
    private final Peliaction action;
    
    public Alkukyselyt(Peliaction action){
        this.action = action;
    }

    /**
     * Alustetaan frame ja panel, luodaan komponentit.
     */
    
    @Override
    public void run() {
        this.frame = new JFrame("Kimbels 1.0");
        this.panel = new JPanel();
        
        this.frame.setLayout(new BorderLayout());
        
        this.frame.setPreferredSize(new Dimension(300, 200));
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        
        luoKomponentit();

        this.frame.pack();
        this.frame.setVisible(true);
    }
    
  /**
   * Luodaan kysymysteksti, vaihtoehdot ja pelaa-nappula.
   */
    
    
    public void luoKomponentit(){
        
        JLabel kysymys = new JLabel("Montako pelaajaa?");
        this.frame.getContentPane().add(kysymys, BorderLayout.NORTH);
        
        ButtonGroup group = new ButtonGroup();
        JRadioButton button1 = new JRadioButton("1");
        JRadioButton button2 = new JRadioButton("2");
        JRadioButton button3 = new JRadioButton("3");
        JRadioButton button4 = new JRadioButton("4");
        
        group.add(button1);
        group.add(button2);
        group.add(button3);
        group.add(button4);
        
        this.panel.add(button1);
        this.panel.add(button2);
        this.panel.add(button3);
        this.panel.add(button4);
        
        this.frame.getContentPane().add(panel, BorderLayout.CENTER);
        
        JButton pelataan = new JButton("Pelataan!");
        pelataan.addActionListener(new Aloituskuuntelija(button1, button2, button3, button4, this.action, pelataan));
        this.frame.getContentPane().add(pelataan, BorderLayout.SOUTH);
        
    }
}
