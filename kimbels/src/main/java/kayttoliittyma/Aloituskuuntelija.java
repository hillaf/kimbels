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

    public Aloituskuuntelija(JRadioButton a, JRadioButton b, JRadioButton c, JRadioButton d, Peliaction action) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.pelaajienMaara = 0;
        this.action = action;
    }

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
        
        this.action.setPelaajienMaara(this.pelaajienMaara);
        
    }
   
}
