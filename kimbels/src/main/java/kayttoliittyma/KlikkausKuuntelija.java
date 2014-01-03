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
    
    public KlikkausKuuntelija(PyoreaNappi nappi){
        this.nappi = nappi;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (this.nappi.onkoKlikattava()){
            this.nappi.asetaKlikattavaksi();
        }
    }
    
}
