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
public class VARITest {

    public VARITest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void sinisenLahtoruutuOn39() {
        assertTrue(VARI.SININEN.getLahtoruutu() == 39);
    }

    @Test
    public void punaisenLahtoruutuOnKuusi() {
        assertTrue(VARI.PUNAINEN.getLahtoruutu() == 6);
    }

    @Test
    public void keltaisenLahtoruutuOn17() {
        assertTrue(VARI.KELTAINEN.getLahtoruutu() == 17);
    }

    @Test
    public void vihreanLahtoruutuOn28s() {
        assertTrue(VARI.VIHREA.getLahtoruutu() == 28);
    }

    @Test
    public void neutraalinLahtoruutuOnMiinusyks() {
        assertTrue(VARI.NEUTRAALI.getLahtoruutu() == -1);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
