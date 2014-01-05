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
        assertTrue(VARI.SININEN.getLahtoruutu() == 0);
    }

    @Test
    public void punaisenLahtoruutuOnKuusi() {
        assertTrue(VARI.PUNAINEN.getLahtoruutu() == 11);
    }

    @Test
    public void keltaisenLahtoruutuOn17() {
        assertTrue(VARI.KELTAINEN.getLahtoruutu() == 22);
    }

    @Test
    public void vihreanLahtoruutuOn28s() {
        assertTrue(VARI.VIHREA.getLahtoruutu() == 33);
    }


    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
