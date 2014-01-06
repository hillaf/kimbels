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

    private int pelaajienMaara;
    private JRadioButton a;
    private JRadioButton b;
    private JRadioButton c;
    private JRadioButton d;
    private Peliaction action;
    private JButton button;

    
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
