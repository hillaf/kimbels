/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import sovelluslogiikka.KimbleLogiikka;
import sovelluslogiikka.Pelilauta;
import sovelluslogiikka.VARI;

/**
 *
 * Käyttöliittymä/piirtäjä. Huolehtii grafiikasta eli ruutujen ja nappuloiden piirtämisestä.
 * 
 * 
 * @author hilla
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private JPanel panel;
    private int x;
    private int y;
    private KimbleLogiikka logiikka;

    public Kayttoliittyma(KimbleLogiikka pelilauta) {
        this.logiikka = pelilauta;
    }

    @Override
    public void run() {
        this.frame = new JFrame("Kimbels 1.0");
        this.panel = new JPanel();
        this.frame.setLayout(new BorderLayout());


        this.frame.setPreferredSize(new Dimension(1000, 700));
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.panel.setLayout(null);

        luoKomponentit();

        this.frame.pack();
        this.frame.setVisible(true);
    }

    public void luoKomponentit() {
        this.panel.setBackground(Color.WHITE);

        luoNoppa();
        piirraRuudut();


        this.frame.getContentPane().add(this.panel);
    }

    public void luoNoppa() {
        JButton noppa = new PyoreaNappi(0, 0, Color.PINK, 50);
        noppa.setText("!");
        noppa.addActionListener(new NopanKuuntelija(noppa, this.logiikka));
        this.panel.add(noppa);
        noppa.setBounds(340, 290, 50, 50);
    }

    public void piirraRuudut() {


        this.x = 100;
        this.y = 150;

        for (int i = 0; i < 44; i++) {
            piirraOvaali(this.x, this.y, i);
        }

        //maaliruudut
        piirraRuudutVarille(Color.RED, 190, 460, true, false, 7);
        piirraRuudutVarille(Color.YELLOW, 510, 460, false, false, 18);
        piirraRuudutVarille(Color.GREEN, 510, 140, false, true, 29);
        piirraRuudutVarille(Color.BLUE, 190, 140, true, true, 40);

        //lähtöruudut
        piirraRuudutVarille(Color.RED, 50, 480, true, true, 44);
        piirraRuudutVarille(Color.YELLOW, 650, 480, false, true, 48);
        piirraRuudutVarille(Color.GREEN, 650, 120, false, false, 52);
        piirraRuudutVarille(Color.BLUE, 50, 120, true, false, 56);

    }



    public void piirraOvaali(int xi, int yi, int i) {

        Color vari = valitseVari(i);

        if ((i < 7 && i > -1) || (i > 10 && i < 18) || (i > 21 && i < 29) || (i > 32 && i < 40)) {
            luoRuutu(xi, yi, vari, i);
            asetaX(i);
            asetaY(i);
        }

    }

    public void luoRuutu(int xi, int yi, Color vari, int i) {

        if (logiikka.onkoRuudussaNappula(i) == true) {
            PyoreaNappi nappi = new PyoreaNappi(0, 0, vari);
            nappi.asetaKlikattavaksi();
            nappi.addActionListener(new KlikkausKuuntelija(nappi));
            this.panel.add(nappi);
            nappi.setBounds(xi, yi, 40, 40);
        } else {
            PyoreaNappi nappi = new PyoreaNappi(0, 0, vari);
            nappi.addActionListener(new KlikkausKuuntelija(nappi));
            this.panel.add(nappi);
            nappi.setBounds(xi, yi, 40, 40);
        }

    }

    public void asetaY(int i) {
        if (i < 5 && i > -1) {
            this.y += 60;
        } else if (i > 15 && i < 18) {
            this.y -= 50;
            this.x += 50;
        } else if (i > 21 && i < 27) {
            this.y -= 60;
        } else if (i > 37 && i < 40) {
            this.y += 50;
            this.x -= 50;
        }
    }

    public void asetaX(int i) {
        if (i < 7 && i > 4) {
            this.x += 50;
            this.y += 50;
        } else if (i > 10 && i < 16) {
            this.x += 60;
        } else if (i > 26 && i < 29) {
            this.x -= 50;
            this.y -= 50;
        } else if (i > 32 && i < 38) {
            this.x -= 60;
        }



    }

    public void piirraRuudutVarille(Color vari, int xr, int yr, boolean xKasvaa, boolean yKasvaa, int indeksi) {


        for (int i = indeksi; i < indeksi+4; i++) {
            luoRuutu(xr, yr, vari, indeksi);
            if (xKasvaa) {
                xr += 40;
            } else {
                xr -= 40;
            }
            if (yKasvaa) {
                yr += 40;
            } else {
                yr -= 40;
            }
        }
    }

    public Color valitseVari(int i) {


//
//        if (i == 11) {
//            System.out.println("RED: x: " + this.x + " y: " + this.y);
//            return Color.RED;
//        }
//        if (i == 22) {
//            System.out.println("YELLOW: x: " + this.x + " y: " + this.y);
//            return Color.YELLOW;
//        }
//        if (i == 33) {
//            System.out.println("GREEN: x: " + this.x + " y: " + this.y);
//            return Color.GREEN;
//        }
//
//        if (i == 44) {
//            System.out.println("BLUE: x: " + this.x + " y: " + this.y);
//            return Color.BLUE;
//        }

        return Color.LIGHT_GRAY;


    }
}
