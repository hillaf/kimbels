/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

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
public class RuutuTest {

    private Ruutu ruutu;
    private Pelaaja pelaaja;

    public RuutuTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.ruutu = new Ruutu(2, VARI.NEUTRAALI);
        this.pelaaja = new Pelaaja("Mikko", VARI.KELTAINEN);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void ruudunSijaintiOikein() {
        assertTrue(this.ruutu.getSijainti() == 2);
    }

    @Test
    public void nappulanAsettaminenRuutuunOnnistuu() {
        Nappula nappula = new Nappula(this.pelaaja, 1);
        this.ruutu.asetaNappulaRuutuun(nappula);

        assertTrue(this.ruutu.getNappula().equals(nappula));
    }

    @Test
    public void nappulanAsettaminenRuutuunOnnistuuReturnsTrue() {
        Nappula nappula = new Nappula(this.pelaaja, 1);
        assertTrue(this.ruutu.asetaNappulaRuutuun(nappula));
    }

    @Test
    public void nappulaaEiVoiAsettaaJosRuudussaToinen() {
        Nappula nappula = new Nappula(this.pelaaja, 1);
        this.ruutu.asetaNappulaRuutuun(nappula);

        Nappula nappula2 = new Nappula(this.pelaaja, 1);
        this.ruutu.asetaNappulaRuutuun(nappula2);
        assertTrue(this.ruutu.getNappula().equals(nappula));
    }

    @Test
    public void nappulaaEiVoiAsettaaJosRuudussaToinenReturnsFalse() {
        Nappula nappula = new Nappula(this.pelaaja, 1);
        this.ruutu.asetaNappulaRuutuun(nappula);

        Nappula nappula2 = new Nappula(this.pelaaja, 1);
        assertTrue(this.ruutu.asetaNappulaRuutuun(nappula2) == false);
    }

    @Test
    public void kunNappulaSiirretaanRuutuunNappulanSijaintiPaivittyy() {
        Nappula nappula = new Nappula(this.pelaaja, 1);
        this.ruutu.asetaNappulaRuutuun(nappula);
        assertTrue(nappula.getSijainti() == this.ruutu.getSijainti());
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
