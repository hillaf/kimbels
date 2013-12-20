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
public class NoppaTest {

    Noppa noppa;

    public NoppaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.noppa = new Noppa();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void noppaAntaaPositiivisenLuvun() {
        int testattavaLuku = noppa.heitaNoppaa();
        assertTrue(testattavaLuku > 0);
        testattavaLuku = noppa.heitaNoppaa();
        assertTrue(testattavaLuku > 0);
        testattavaLuku = noppa.heitaNoppaa();
        assertTrue(testattavaLuku > 0);
        testattavaLuku = noppa.heitaNoppaa();
        assertTrue(testattavaLuku > 0);
        testattavaLuku = noppa.heitaNoppaa();
        assertTrue(testattavaLuku > 0);
        testattavaLuku = noppa.heitaNoppaa();
        assertTrue(testattavaLuku > 0);
        
        
    }

    @Test
    public void noppaAntaaLuvunPienempiKuinSeitseman() {
        int testattavaLuku = noppa.heitaNoppaa();
        assertTrue(testattavaLuku < 7);
        testattavaLuku = noppa.heitaNoppaa();
        assertTrue(testattavaLuku < 7);
        testattavaLuku = noppa.heitaNoppaa();
        assertTrue(testattavaLuku < 7);
        testattavaLuku = noppa.heitaNoppaa();
        assertTrue(testattavaLuku < 7);
        testattavaLuku = noppa.heitaNoppaa();
        assertTrue(testattavaLuku < 7);
        testattavaLuku = noppa.heitaNoppaa();
        assertTrue(testattavaLuku < 7);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
