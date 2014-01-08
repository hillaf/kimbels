/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * Nappien (eli ruutujen) tapahtumankuuntelija.
 * 
 * @author hilla
 */
public class KlikkausKuuntelija implements ActionListener {

    /**
     * Nappi jota kuunnellaan.
     */
    
    private final PyoreaNappi nappi;
    
    public KlikkausKuuntelija(PyoreaNappi nappi){
        this.nappi = nappi;
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
