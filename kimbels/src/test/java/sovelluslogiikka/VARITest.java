/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

import java.awt.Color;
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
    
    @Test
    public void vihreanColorOnGreen() {
        assertTrue(VARI.VIHREA.getColor().equals(Color.GREEN));
    }
    @Test
    public void sinisenColorOnBlue() {
        assertTrue(VARI.SININEN.getColor().equals(Color.BLUE));
    }
    @Test
    public void punaisenColorOnRed() {
        assertTrue(VARI.PUNAINEN.getColor().equals(Color.RED));
    }
    @Test
    public void keltaisenColorOnYellow() {
        assertTrue(VARI.KELTAINEN.getColor().equals(Color.YELLOW));
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
