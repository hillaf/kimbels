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
        this.pelilauta.luoRuudut();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void pelilautaLuo44Ruutua() {
        this.pelilauta.luoRuudut();
        assertTrue(this.pelilauta.getRengas().size() == 44);
    }

    @Test
    public void pelilautaLuo16Lahtoruutua() {
        this.pelilauta.luoRuudut();
        assertTrue(this.pelilauta.getLahtoruudut().size() == 16);
    }

    @Test
    public void luoLahtoruudutToimii() {
        this.pelilauta.luoLahtoruudut();
        assertTrue(this.pelilauta.getLahtoruudut().size() == 16);
    }

    @Test
    public void pelilautaLuoOikeinLahtoruutujenVarit() {
        this.pelilauta.luoRuudut();
        assertTrue(this.pelilauta.getLahtoruudut().get(45).getVari().equals(VARI.SININEN));
        assertTrue(this.pelilauta.getLahtoruudut().get(49).getVari().equals(VARI.PUNAINEN));
        assertTrue(this.pelilauta.getLahtoruudut().get(53).getVari().equals(VARI.KELTAINEN));
        assertTrue(this.pelilauta.getLahtoruudut().get(56).getVari().equals(VARI.VIHREA));
    }

    @Test
    public void pelilautaLuoOikeinMaaliruutujenVarit() {
        this.pelilauta.luoRuudut();
        assertTrue(this.pelilauta.getRengas().get(40).getVari().equals(VARI.SININEN));
        assertTrue(this.pelilauta.getRengas().get(7).getVari().equals(VARI.PUNAINEN));
        assertTrue(this.pelilauta.getRengas().get(18).getVari().equals(VARI.KELTAINEN));
        assertTrue(this.pelilauta.getRengas().get(32).getVari().equals(VARI.VIHREA));
    }

    @Test
    public void pelilautaEiLuoVarillisiaRengasruutuja() {
        this.pelilauta.luoRuudut();
        assertTrue(this.pelilauta.getRengas().get(11).getVari().equals(VARI.NEUTRAALI));
        assertTrue(this.pelilauta.getRengas().get(0).getVari().equals(VARI.NEUTRAALI));
        assertTrue(this.pelilauta.getRengas().get(17).getVari().equals(VARI.NEUTRAALI));
        assertTrue(this.pelilauta.getRengas().get(22).getVari().equals(VARI.NEUTRAALI));
        assertTrue(this.pelilauta.getRengas().get(33).getVari().equals(VARI.NEUTRAALI));
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

    @Test
    public void luoNappulatKunLuodaanPelaajatNimilla() {

        ArrayList<String> nimet = new ArrayList<String>();
        nimet.add("Heikki");
        nimet.add("Pekka");
        nimet.add("Jonne");
        nimet.add("Make");
        this.pelilauta.luoPelaajat(4, nimet);

        assertTrue(!this.pelilauta.getPelaajat().get(0).getNappulat().isEmpty());
        assertTrue(!this.pelilauta.getPelaajat().get(3).getNappulat().isEmpty());
    }

    @Test
    public void luoNappulatKunLuodaanPelaajatIlmanNimea() {

        ArrayList<String> nimet = new ArrayList<String>();
        this.pelilauta.luoPelaajat(4, nimet);

        assertTrue(!this.pelilauta.getPelaajat().get(0).getNappulat().isEmpty());
        assertTrue(!this.pelilauta.getPelaajat().get(3).getNappulat().isEmpty());
    }

    @Test
    public void luoNappuloita() {
        Pelaaja pelaaja = new Pelaaja("Matti", VARI.KELTAINEN);
        this.pelilauta.luoNappulat(pelaaja);

        assertTrue(pelaaja.getNappulat() != null);
    }

    @Test
    public void luoTasanNeljaNappulaa() {
        Pelaaja pelaaja = new Pelaaja("Matti", VARI.KELTAINEN);
        this.pelilauta.luoNappulat(pelaaja);

        assertTrue(pelaaja.getNappulat().size() == 4);

    }

    @Test
    public void luoNappulatOikeallePelaajalle() {
        Pelaaja pelaaja = new Pelaaja("Matti", VARI.KELTAINEN);
        this.pelilauta.luoNappulat(pelaaja);

        assertTrue(pelaaja.getNappulat().get(0).getPelaaja().equals(pelaaja));

    }

    @Test
    public void luoNappulatAloitusruutuun() {
        Pelaaja pelaaja = new Pelaaja("Matti", VARI.KELTAINEN);
        this.pelilauta.luoNappulat(pelaaja);

        assertTrue(this.pelilauta.getAloitusruudut().get(VARI.KELTAINEN).contains(pelaaja.getNappulat().get(0).getSijainti()));
    }

    @Test
    public void luoNappulatEriAloitusruutuihin() {
        Pelaaja pelaaja = new Pelaaja("Matti", VARI.KELTAINEN);
        this.pelilauta.luoNappulat(pelaaja);

        assertTrue(pelaaja.getNappulat().get(0).getSijainti() != pelaaja.getNappulat().get(1).getSijainti());
    }

    @Test
    public void eiSiirraNappulaaUlosLaudalta() {
        Nappula nappula = new Nappula(new Pelaaja("Matti", VARI.KELTAINEN), 58);
        this.pelilauta.luoRuudut();
        assertTrue(this.pelilauta.siirraNappulaa(nappula, 6) == false);
    }

    @Test
    public void siirtaaNappulaaNeutraalillaAlueella() {
        Nappula nappula = new Nappula(new Pelaaja("Matti", VARI.PUNAINEN), 2);
        this.pelilauta.luoRuudut();
        assertTrue(this.pelilauta.siirraNappulaa(nappula, 2) == true);
    }

    @Test
    public void eiSiirraNappulaaToisenMaaliin() {
        Nappula nappula = new Nappula(new Pelaaja("Matti", VARI.SININEN), 2);
        this.pelilauta.luoRuudut();
        assertTrue(this.pelilauta.siirraNappulaa(nappula, 6) == false);
    }

    @Test
    public void siirtaaNappulanOmaanMaaliin() {
        Nappula nappula = new Nappula(new Pelaaja("Matti", VARI.PUNAINEN), 2);
        this.pelilauta.luoRuudut();
        assertTrue(this.pelilauta.siirraNappulaa(nappula, 6) == true);
    }

    @Test
    public void siirtaaNappulaaIndeksinReunalla() {
        Nappula nappula = new Nappula(new Pelaaja("Matti", VARI.SININEN), 40);
        this.pelilauta.luoRuudut();
        assertTrue(this.pelilauta.siirraNappulaa(nappula, 3) == true);
    }

    @Test
    public void noppaToimii() {
        int luku = this.pelilauta.heitaNoppaa();
        assertTrue(luku > 0 && luku < 7);
        luku = this.pelilauta.heitaNoppaa();
        assertTrue(luku > 0 && luku < 7);
        luku = this.pelilauta.heitaNoppaa();
        assertTrue(luku > 0 && luku < 7);
        luku = this.pelilauta.heitaNoppaa();
        assertTrue(luku > 0 && luku < 7);
        luku = this.pelilauta.heitaNoppaa();
        assertTrue(luku > 0 && luku < 7);
        luku = this.pelilauta.heitaNoppaa();
        assertTrue(luku > 0 && luku < 7);
        luku = this.pelilauta.heitaNoppaa();
        assertTrue(luku > 0 && luku < 7);


    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
