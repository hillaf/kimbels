/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import aanet.Dice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import sovelluslogiikka.KimbleLogiikka;

/**
 *
 * Nopan tapahtumakuuntelija.
 *
 *
 * @author hilla
 */
public class NopanKuuntelija implements ActionListener {

    /**
     * Noppa jota klikataan.
     */
    private final JButton noppa;
    
    /**
     * Rajapinta sovelluslogiikkaan.
     */
    private final KimbleLogiikka logiikka;
    
    /**
     * Käyttöliittymä, jolle kutsutaan paivitaVuoroteksti()-metodia.
     */
    private final Kayttoliittyma kayttoliittyma;
    
    /**
     * Kaunis noppasoundi. ;___;
     */
    private final Dice dice;

    public NopanKuuntelija(JButton noppa, KimbleLogiikka logiikka, Kayttoliittyma liittyma) {
        this.noppa = noppa;
        this.logiikka = logiikka;
        this.kayttoliittyma = liittyma;
        this.dice = new Dice();
        
    }

    /**
     * Klikattaessa heittää nopasta uuden silmäluvun ja kutsuu logiikan
     * setSiirtoVuoro()-metodia.
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {

        if (this.logiikka.onkoHeittovuoro()) {
            this.dice.play();
            int silmaluku = this.logiikka.heitaNoppaa();
            this.noppa.setText(String.valueOf(silmaluku));
            this.logiikka.setSiirtoVuoro(this.logiikka.kenenVuoro(), silmaluku);
            this.logiikka.siirrytaanSiirtymisvuoroon();
            this.kayttoliittyma.paivitaVuoroteksti();
        }

    }

}
