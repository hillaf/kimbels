/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.util.ArrayList;
import sovelluslogiikka.Pelilauta;

/**
 *
 * Hallinnoi alun kyselyjä ja peliä.
 * 
 * @author hilla
 */
public class Peliaction implements Runnable {
    
    private Kayttoliittyma kayttoliittyma;
    private Alkukyselyt kyselyt;
    private Pelilauta pelilauta;
    
    public Peliaction(){
        this.pelilauta = new Pelilauta();
        this.kayttoliittyma = new Kayttoliittyma(this.pelilauta);
        this.kyselyt = new Alkukyselyt(this);
    }

    /**
     * Kutsutaan kun Aloituskuuntelija vastaanottaa klikkauksen.
     */
    
    @Override
    public void run() {
        this.kyselyt.run();
        
    }
    
    public void setPelaajienMaara(int maara){
        this.pelilauta.luoRuudut();
        this.pelilauta.luoPelaajat(maara, new ArrayList<String>());
        this.kayttoliittyma.run();
        
    }
    
    
    
}
