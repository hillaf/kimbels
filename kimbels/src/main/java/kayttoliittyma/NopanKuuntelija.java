/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import sovelluslogiikka.KimbleLogiikka;

/**
 *
 * Nopan tapahtumakuuntelija.
 * 
 * 
 * @author hilla
 */
public class NopanKuuntelija implements ActionListener {

    private JButton noppa;
    private KimbleLogiikka logiikka;
    private JLabel ruutu;
    private Kayttoliittyma kayttoliittyma;
    
    public NopanKuuntelija(JButton noppa, KimbleLogiikka logiikka, JLabel ruutu, Kayttoliittyma liittyma){
        this.noppa = noppa;
        this.logiikka = logiikka;
        this.ruutu = ruutu;
        this.kayttoliittyma = liittyma;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        int silmaluku = this.logiikka.heitaNoppaa();
        this.noppa.setText(String.valueOf(silmaluku));
        this.logiikka.seuraavanVuoro();
        this.ruutu.setText("VUOROSSA: " + this.logiikka.kenenVuoro());
        this.kayttoliittyma.piirraRuudut();
        
    }
    
}
