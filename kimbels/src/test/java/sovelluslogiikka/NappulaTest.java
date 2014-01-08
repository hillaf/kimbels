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
import static sovelluslogiikka.VARI.*;

/**
 *
 * @author hilla
 */
public class NappulaTest {
    
    Nappula nappula;
    Ruutu ruutu;
    
    public NappulaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.nappula = new Nappula(new Pelaaja(KELTAINEN));
        this.ruutu = new Ruutu(2, null);
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void nappulanPelaajaOikein(){
        assertTrue(this.nappula.getPelaaja().getVari().equals(KELTAINEN));
    }
    
    @Test
    public void nappulanSijaintiOikein(){
        assertTrue(this.nappula.getSijainti() == 22);
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}


