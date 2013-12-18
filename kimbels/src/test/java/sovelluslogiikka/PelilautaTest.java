/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hilla
 */
public class PelilautaTest {

    Pelilauta pelilauta;
//    ArrayList<Pelaaja> pelaajat;
//    HashMap<Integer, Ruutu> rengas;

    public PelilautaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.pelilauta = new Pelilauta();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void pelilautaLuo28Ruutua() {
        this.pelilauta.luoRuudut();
        assertTrue(this.pelilauta.getRengas().size() == 28);
    }

    @Test
    public void pelilautaLuoOikeinKaksiPelaajaa() {
        ArrayList<String> nimet = new ArrayList<String>();
        nimet.add("Heikki");
        nimet.add("Pekka");
        this.pelilauta.luoPelaajat(2, nimet);
        assertTrue(this.pelilauta.getPelaajat().size() == 2);
    }

    @Test
    public void pelilautaEiLuoPelaajiaJosNolla() {
        ArrayList<String> nimet = new ArrayList<String>();
        this.pelilauta.luoPelaajat(0, nimet);
        assertTrue(this.pelilauta.getPelaajat().isEmpty());
    }

    @Test
    public void pelilautaGeneroiNimenJosPelaajallaEiNimea() {
        ArrayList<String> nimet = new ArrayList<String>();
        nimet.add("Pekko");
        this.pelilauta.luoPelaajat(2, nimet);
        assertTrue(this.pelilauta.getPelaajat().size() == 2);
    }

    @Test
    public void pelilautaGeneroiNimeksiVarinJosPelaajallaEiNimea() {
        ArrayList<String> nimet = new ArrayList<String>();
        this.pelilauta.luoPelaajat(1, nimet);
        assertTrue(this.pelilauta.getPelaajat().get(0).getNimi().equals("SININEN"));
    }

    @Test
    public void pelilautaEiLuoYliNeljaaPelaajaa() {
        ArrayList<String> nimet = new ArrayList<String>();
        nimet.add("Heikki");
        nimet.add("Pekka");
        nimet.add("Jonne");
        nimet.add("Make");
        nimet.add("Hildemar");

        this.pelilauta.luoPelaajat(5, nimet);
        assertTrue(this.pelilauta.getPelaajat().size() == 4);
    }

    @Test
    public void jokaistaVariaVainYksi() {
        ArrayList<String> nimet = new ArrayList<String>();
        nimet.add("Heikki");
        nimet.add("Pekka");
        nimet.add("Jonne");
        nimet.add("Make");
        this.pelilauta.luoPelaajat(4, nimet);

        HashSet<VARI> loydetytVarit = new HashSet<VARI>();

        for (Pelaaja pelaaja : this.pelilauta.getPelaajat()) {
            loydetytVarit.add(pelaaja.getVari());
        }

        assertTrue(loydetytVarit.size() == 4);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
