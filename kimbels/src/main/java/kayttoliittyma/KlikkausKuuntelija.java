/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * Nappien (eli ruutujen) tapahtumankuuntelija.
 * 
 * @author hilla
 */
public class KlikkausKuuntelija implements ActionListener {

    private PyoreaNappi nappi;
    private Kayttoliittyma liittyma;
    
    public KlikkausKuuntelija(PyoreaNappi nappi, Kayttoliittyma liittyma){
        this.nappi = nappi;
        this.liittyma = liittyma;
    }
    
    /**
     * Kutsuu napin klikattu()-metodia kun klikataan.
     * @param ae 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (this.nappi.onkoKlikattava()){
            this.nappi.klikattu();
        }
    }
    
}
