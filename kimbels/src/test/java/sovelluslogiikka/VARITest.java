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
    public void sinisenLahtoruutuOnNolla() {
        assertTrue(VARI.SININEN.getLahtoruutu() == 0);
    }

    @Test
    public void punaisenLahtoruutuOnSeitseman() {
        assertTrue(VARI.PUNAINEN.getLahtoruutu() == 7);
    }

    @Test
    public void keltaisenLahtoruutuOnNeljatoista() {
        assertTrue(VARI.KELTAINEN.getLahtoruutu() == 14);
    }

    @Test
    public void vihreanLahtoruutuOnKakskytyks() {
        assertTrue(VARI.VIHREA.getLahtoruutu() == 21);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
