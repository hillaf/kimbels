/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JRadioButton;

/**
 * Alun pelaajamääräkyselyjen pelataan-napin kuuntelija.
 * 
 * @author hilla
 */
public class Aloituskuuntelija implements ActionListener {
/**
 * Pelin pelaajien määrä.
 */
    private int pelaajienMaara;
    
    /**
     * Pelaajia 1.
     */
    private final JRadioButton a;
    
    /**
     * Pelaajia 2.
     */
    private final JRadioButton b;
    
    /**
     * Pelaajia 3.
     */
    private final JRadioButton c;
    
    /**
     * Pelaajia 4.
     */
    private final JRadioButton d;
    
    /**
     * Pelaajien määrä annetaan actionille parametrina.
     */
    private final Peliaction action;
    
    /**
     * Painettu nappula disabloidaan mahdollisen ydintuhon välttämiseksi.
     */
    private final JButton button;

    
    public Aloituskuuntelija(JRadioButton a, JRadioButton b, JRadioButton c, JRadioButton d, Peliaction action, JButton button) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.pelaajienMaara = 0;
        this.action = action;
        this.button = button;
    }

    /**
     * Asettaa pelaajien määrän valitun RadioButtonin perusteella.
     * @param ae klikkaus
     */
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (this.a.isSelected()) {
            this.pelaajienMaara = 1;
        }

        if (this.b.isSelected()) {
            this.pelaajienMaara = 2;
        }

        if (this.c.isSelected()) {
            this.pelaajienMaara = 3;
        }

        if (this.d.isSelected()) {
            this.pelaajienMaara = 4;
        }
        
        this.action.setPelaajienMaara(pelaajienMaara);
        this.button.removeActionListener(this);
    }
   
}
