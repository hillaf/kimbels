/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import sovelluslogiikka.KimbleLogiikka;

/**
 *
 * @author hilla
 */
public class NopanKuuntelija implements ActionListener {

    private JButton noppa;
    private KimbleLogiikka logiikka;
    
    public NopanKuuntelija(JButton noppa, KimbleLogiikka logiikka){
        this.noppa = noppa;
        this.logiikka = logiikka;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        int silmaluku = this.logiikka.heitaNoppaa();
        this.noppa.setText(String.valueOf(silmaluku));
    }
    
}
