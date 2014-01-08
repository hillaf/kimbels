/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

import java.util.HashMap;
import java.util.HashSet;
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
public class PelilautaTest {

    Pelilauta pelilauta;

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
        assertTrue(this.pelilauta.getRengas().get(11).getVari() == null);
        assertTrue(this.pelilauta.getRengas().get(0).getVari() == null);
        assertTrue(this.pelilauta.getRengas().get(17).getVari() == null);
        assertTrue(this.pelilauta.getRengas().get(22).getVari() == null);
        assertTrue(this.pelilauta.getRengas().get(33).getVari() == null);
    }

    @Test
    public void pelilautaLuoOikeinKaksiPelaajaa() {
        this.pelilauta.luoPelaajat(2);
        assertTrue(this.pelilauta.getPelaajat().size() == 2);
    }

    @Test
    public void pelilautaEiLuoPelaajiaJosNolla() {
        this.pelilauta.luoPelaajat(0);
        assertTrue(this.pelilauta.getPelaajat().isEmpty());
    }

    @Test
    public void pelilautaGeneroiNimenJosPelaajallaEiNimea() {
        this.pelilauta.luoPelaajat(2);
        assertTrue(this.pelilauta.getPelaajat().size() == 2);
    }

    @Test
    public void pelilautaGeneroiNimeksiVarinJosPelaajallaEiNimea() {
        this.pelilauta.luoPelaajat(1);
        assertTrue(this.pelilauta.getPelaajat().get(0).getVari().equals(SININEN));
    }

    @Test
    public void pelilautaEiLuoYliNeljaaPelaajaa() {
        this.pelilauta.luoPelaajat(5);
        assertTrue(this.pelilauta.getPelaajat().size() == 4);
    }

    @Test
    public void jokaistaVariaVainYksi() {
        this.pelilauta.luoPelaajat(4);

        HashSet<VARI> loydetytVarit = new HashSet<VARI>();

        for (Pelaaja pelaaja : this.pelilauta.getPelaajat()) {
            loydetytVarit.add(pelaaja.getVari());
        }

        assertTrue(loydetytVarit.size() == 4);
    }

    @Test
    public void luoNappulatKunLuodaanPelaajatNimilla() {
        this.pelilauta.luoPelaajat(4);
        assertTrue(!this.pelilauta.getPelaajat().get(0).getNappulat().isEmpty());
        assertTrue(!this.pelilauta.getPelaajat().get(3).getNappulat().isEmpty());
    }

    @Test
    public void luoNappulatKunLuodaanPelaajatIlmanNimea() {
        this.pelilauta.luoPelaajat(4);

        assertTrue(!this.pelilauta.getPelaajat().get(0).getNappulat().isEmpty());
        assertTrue(!this.pelilauta.getPelaajat().get(3).getNappulat().isEmpty());
    }

    @Test
    public void luoNappuloita() {
        Pelaaja pelaaja = new Pelaaja(KELTAINEN);
        this.pelilauta.luoNappulat(pelaaja);

        assertTrue(pelaaja.getNappulat() != null);
    }

    @Test
    public void luoTasanNeljaNappulaa() {
        Pelaaja pelaaja = new Pelaaja(KELTAINEN);
        this.pelilauta.luoNappulat(pelaaja);

        assertTrue(pelaaja.getNappulat().size() == 4);

    }

    @Test
    public void luoNappulatOikeallePelaajalle() {
        Pelaaja pelaaja = new Pelaaja(KELTAINEN);
        this.pelilauta.luoNappulat(pelaaja);

        assertTrue(pelaaja.getNappulat().get(0).getPelaaja().equals(pelaaja));

    }

    @Test
    public void luoNappulatAloitusruutuun() {
        Pelaaja pelaaja = new Pelaaja(KELTAINEN);
        this.pelilauta.luoNappulat(pelaaja);

        assertTrue(this.pelilauta.getAloitusruudut().get(VARI.KELTAINEN).contains(pelaaja.getNappulat().get(0).getSijainti()));
    }

    @Test
    public void luoNappulatEriAloitusruutuihin() {
        Pelaaja pelaaja = new Pelaaja(KELTAINEN);
        this.pelilauta.luoNappulat(pelaaja);

        assertTrue(pelaaja.getNappulat().get(0).getSijainti() != pelaaja.getNappulat().get(1).getSijainti());
    }

    @Test
    public void eiSiirraNappulaaUlosLaudalta() {
        Nappula nappula = new Nappula(new Pelaaja(VARI.KELTAINEN));
        this.pelilauta.luoRuudut();
        this.pelilauta.siirraNappulaRuutuun(nappula, 38);
        this.pelilauta.siirraNappulaa(nappula, 6);
        assertTrue(nappula.getSijainti() == 4);
    }

    @Test
    public void siirtaaNappulaaNeutraalillaAlueella() {
        Nappula nappula = new Nappula(new Pelaaja(VARI.PUNAINEN));
        this.pelilauta.luoRuudut();
        this.pelilauta.siirraNappulaRuutuun(nappula, 2);
        this.pelilauta.siirraNappulaa(nappula, 2);
        assertTrue(nappula.getSijainti() == 4);
        assertTrue(this.pelilauta.onkoRuudussaNappula(4) == true);
    }

    @Test
    public void eiSiirraNappulaaToisenMaaliin() {
        Nappula nappula = new Nappula(new Pelaaja(VARI.SININEN));
        this.pelilauta.luoRuudut();
        this.pelilauta.siirraNappulaa(nappula, 6);
        assertTrue(this.pelilauta.onkoRuudussaNappula(8) == false);
    }

    @Test
    public void siirtaaNappulanOmaanMaaliinJaPaivittaa() {
        Nappula nappula = new Nappula(new Pelaaja(VARI.PUNAINEN));
        this.pelilauta.luoRuudut();
        assertTrue(this.pelilauta.siirraNappulaa(nappula, 6).containsKey(17));
    }

    @Test
    public void siirtaaNappulaaIndeksinReunalla() {
        Nappula nappula = new Nappula(new Pelaaja(VARI.SININEN));
        this.pelilauta.siirraNappulaRuutuun(nappula, 40);
        this.pelilauta.luoRuudut();
        this.pelilauta.siirraNappulaa(nappula, 3);
        assertTrue(nappula.getSijainti() == 43);
        assertTrue(this.pelilauta.onkoRuudussaNappula(43));
    }
    
    @Test
    public void siirraNappulaAloitusruudussaToimii() {
        this.pelilauta.luoRuudut();
        this.pelilauta.luoPelaajat(2);
        HashMap<Integer, VARI> tutkittava = this.pelilauta.siirraNappulaaAloitusruudussa(this.pelilauta.getPelaaja(SININEN).getNappulat().get(0), 6);
        assertTrue(this.pelilauta.getPelaaja(SININEN).getNappulat().get(0).getSijainti() == 0);
        assertTrue(this.pelilauta.onkoRuudussaNappula(0));
        assertTrue(tutkittava.containsKey(0));
    }
    
    @Test
    public void siirraNappulaAloitusruudussaEiSiirraJosEiKutosta() {
        this.pelilauta.luoRuudut();
        this.pelilauta.luoPelaajat(2);
        HashMap<Integer, VARI> tutkittava = this.pelilauta.siirraNappulaa(this.pelilauta.getPelaaja(SININEN).getNappulat().get(0), 5);
        assertTrue(this.pelilauta.getPelaaja(SININEN).getNappulat().get(0).getSijainti() == 44);
        assertTrue(this.pelilauta.onkoRuudussaNappula(0) == false);
        assertTrue(tutkittava.containsKey(44));
    }
    
    @Test
    public void siirraNappulaAloitusruudustaEiSiirraJosEiKutosta() {
        this.pelilauta.luoRuudut();
        this.pelilauta.luoPelaajat(2);
        this.pelilauta.siirraNappulaa(this.pelilauta.getPelaaja(SININEN).getNappulat().get(0), 5);
        assertTrue(this.pelilauta.getPelaaja(SININEN).getNappulat().get(0).getSijainti() == 44);
        assertTrue(this.pelilauta.onkoRuudussaNappula(0) == false);
    }
    
    @Test
    public void siirraNappulaAloitusruudustaEiSiirraJosTiellaOma() {
        this.pelilauta.luoRuudut();
        this.pelilauta.luoPelaajat(1);
        this.pelilauta.siirraNappulaRuutuun(this.pelilauta.getPelaaja(SININEN).getNappulat().get(0), 0);
        this.pelilauta.siirraNappulaa(this.pelilauta.getPelaaja(SININEN).getNappulat().get(1), 6);
        assertTrue(this.pelilauta.getPelaaja(SININEN).getNappulat().get(1).getSijainti() == 45);
        assertTrue(this.pelilauta.getPelaaja(SININEN).getNappulat().get(0).getSijainti() == 0);
    }

    @Test
    public void noppaToimii() {

        for (int i = 0; i < 10; i++) {
            int luku = this.pelilauta.heitaNoppaa();
            assertTrue(luku > 0 && luku < 7);
        }
    }
    
    @Test
    public void alussaHeittovuoro(){
        assertTrue(this.pelilauta.onkoHeittovuoro());
    }

    @Test
    public void palauttaaOikeanVarisenNappulan() {
        Nappula nappula = new Nappula(new Pelaaja(PUNAINEN));
        this.pelilauta.siirraNappulaRuutuun(nappula, 5);
        assertTrue(this.pelilauta.minkaVarinenNappula(5).equals(VARI.PUNAINEN));

    }

    @Test
    public void siirtaaNappulaaTyhjaanRuutuun() {
        this.pelilauta.luoPelaajat(2);
        this.pelilauta.siirraNappulaRuutuun(this.pelilauta.getPelaajat().get(0).getNappulat().get(0), 28);

        assertTrue(this.pelilauta.onkoRuudussaNappula(28) == true);
    }

    @Test
    public void siirtaaNappulanToisenPaalle() {
        this.pelilauta.luoPelaajat(2);
        this.pelilauta.siirraNappulaRuutuun(this.pelilauta.getPelaajat().get(0).getNappulat().get(0), 28);
        this.pelilauta.siirraNappulaRuutuun(this.pelilauta.getPelaajat().get(1).getNappulat().get(0), 28);

        assertTrue(this.pelilauta.minkaVarinenNappula(28) != null);
    }
    


    @Test
    public void toisenNappulaSyodaan() {
        this.pelilauta.luoPelaajat(2);
        this.pelilauta.siirraNappulaRuutuun(this.pelilauta.getPelaajat().get(0).getNappulat().get(0), 28);
        this.pelilauta.siirraNappulaRuutuun(this.pelilauta.getPelaajat().get(1).getNappulat().get(0), 28);
        int matinAloitusruutu = this.pelilauta.getAloitusruudut().get(this.pelilauta.getPelaajat().get(0).getVari()).get(0);

        assertTrue(this.pelilauta.getPelaajat().get(0).getNappulat().get(0).getSijainti() == matinAloitusruutu);
    }
    
        @Test
    public void toisenNappulaSyodaanAloitusruudussa() {
        this.pelilauta.luoPelaajat(2);
        this.pelilauta.siirraNappulaRuutuun(this.pelilauta.getPelaajat().get(0).getNappulat().get(0), 28);
        this.pelilauta.siirraNappulaRuutuun(this.pelilauta.getPelaajat().get(1).getNappulat().get(0), 28);
        int matinAloitusruutu = this.pelilauta.getAloitusruudut().get(this.pelilauta.getPelaajat().get(0).getVari()).get(0);

        assertTrue(this.pelilauta.getPelaajat().get(0).getNappulat().get(0).getSijainti() == matinAloitusruutu);
    }
    
        @Test
    public void siirtyyAloitusruudusta() {
        this.pelilauta.luoPelaajat(2);
        this.pelilauta.siirraNappulaRuutuun(this.pelilauta.getPelaajat().get(0).getNappulat().get(0), 28);
        this.pelilauta.siirraNappulaRuutuun(this.pelilauta.getPelaajat().get(1).getNappulat().get(0), 28);
        int matinAloitusruutu = this.pelilauta.getAloitusruudut().get(this.pelilauta.getPelaajat().get(0).getVari()).get(0);

        assertTrue(this.pelilauta.getPelaajat().get(0).getNappulat().get(0).getSijainti() == matinAloitusruutu);
    }
    
    @Test
    public void meneekoHenkkohtBoundOikeinJosOhittanutNollan() {
        this.pelilauta.luoPelaajat(2);
        this.pelilauta.siirraNappulaRuutuun(this.pelilauta.getPelaajat().get(1).getNappulat().get(0), 6);
        this.pelilauta.getRuutu(6).getNappula().setOhittiNollan(true);
        this.pelilauta.siirraNappulaa(this.pelilauta.getPelaajat().get(1).getNappulat().get(0), 6);
        assertTrue(this.pelilauta.getPelaajat().get(1).getNappulat().get(0).getSijainti() == 6);
    }

    @Test
    public void palauttaaAlussaOikeanVuoron() {
        this.pelilauta.luoPelaajat(3);
        assertTrue(this.pelilauta.kenenVuoro().equals(VARI.SININEN));
    }

    @Test
    public void palauttaaKuudenVuoronJalkeenOikeanVuoron() {
        this.pelilauta.luoPelaajat(4);

        for (int i = 0; i < 6; i++) {
            this.pelilauta.seuraavanVuoro();
        }
        assertTrue(this.pelilauta.kenenVuoro().equals(VARI.KELTAINEN));
    }

    @Test
    public void getRuutuToimiiRenkaalle() {
        assertTrue(this.pelilauta.getRuutu(14).equals(this.pelilauta.getRengas().get(14)));
    }
    
    @Test
    public void getRuutuPalauttaaNullJosEiIndeksia() {
        assertTrue(this.pelilauta.getRuutu(65) == null);
    }

    @Test
    public void getRuutuToimiiLahtoruuduille() {
        assertTrue(this.pelilauta.getRuutu(55).equals(this.pelilauta.getLahtoruudut().get(55)));
    }

    @Test
    public void getPelaajaToimii() {
        this.pelilauta.luoPelaajat(3);
        assertTrue(this.pelilauta.getPelaaja(VARI.SININEN).equals(this.pelilauta.getPelaajat().get(0)));
    }
    
        @Test
    public void getPelaajaPalauttaaNullJosEiPelaajia() {
        assertTrue(this.pelilauta.getPelaaja(VARI.SININEN) == null);
    }

    @Test
    public void setNappulatValittaviksiTrueToimii() {
        this.pelilauta.luoPelaajat(2);
        this.pelilauta.setNappulatValittaviksi(VARI.PUNAINEN, true);

        assertTrue(this.pelilauta.getRuutu(48).onkoValittava() == true);
    }

    @Test
    public void setNappulatValittaviksiFalseToimii() {
        this.pelilauta.luoPelaajat(2);
        this.pelilauta.setNappulatValittaviksi(VARI.SININEN, false);

        assertTrue(this.pelilauta.getRuutu(45).onkoValittava() == false);
    }

    @Test
    public void onkoRuudussaNappulaRengasTrueToimii() {
        this.pelilauta.luoPelaajat(2);
        this.pelilauta.siirraNappulaRuutuun(this.pelilauta.getPelaajat().get(0).getNappulat().get(0), 15);

        assertTrue(this.pelilauta.onkoRuudussaNappula(15));
    }
    
    @Test
    public void eiSiirraNappulaaOmanPaalle() {
        this.pelilauta.luoPelaajat(1);
        this.pelilauta.siirraNappulaRuutuun(this.pelilauta.getPelaajat().get(0).getNappulat().get(0), 15);
        this.pelilauta.siirraNappulaRuutuun(this.pelilauta.getPelaajat().get(0).getNappulat().get(1), 13);
        this.pelilauta.siirraNappulaa(this.pelilauta.getPelaajat().get(0).getNappulat().get(1), 2);
        assertTrue(this.pelilauta.getPelaajat().get(0).getNappulat().get(1).getSijainti() == 13);
        assertTrue(this.pelilauta.getPelaajat().get(0).getNappulat().get(0).getSijainti() == 15);
    }

    @Test
    public void onkoRuudussaNappulaLahtoruutuTrueToimii() {
        this.pelilauta.luoPelaajat(2);

        assertTrue(this.pelilauta.onkoRuudussaNappula(44));
    }

    @Test
    public void onkoRuudussaNappulaRengasFalseToimii() {
        this.pelilauta.luoPelaajat(2);

        assertTrue(this.pelilauta.onkoRuudussaNappula(15) == false);
    }

    @Test
    public void onkoRuudussaNappulaLahtoruutuFalseToimii() {
        this.pelilauta.luoPelaajat(2);
        this.pelilauta.poistaNappulaRuudusta(44);

        assertTrue(this.pelilauta.onkoRuudussaNappula(44) == false);
    }

    @Test
    public void minkaVarinenNappulaRuudussaAloitusruutuNull() {
        this.pelilauta.luoPelaajat(2);
        this.pelilauta.poistaNappulaRuudusta(44);

        assertTrue(this.pelilauta.minkaVarinenNappula(44) == null);
    }

    @Test
    public void minkaVarinenNappulaRuudussaAloitusruutuSininen() {
        this.pelilauta.luoPelaajat(2);

        assertTrue(this.pelilauta.minkaVarinenNappula(44) == VARI.SININEN);
    }

    @Test
    public void minkaVarinenNappulaRuudussaRengas() {
        this.pelilauta.luoPelaajat(2);
        this.pelilauta.siirraNappulaRuutuun(this.pelilauta.getPelaajat().get(0).getNappulat().get(0), 15);
        assertTrue(this.pelilauta.minkaVarinenNappula(15) == VARI.SININEN);
    }
    
    @Test
    public void minkaVarinenNappulaIndeksiaEiOle() {
        this.pelilauta.luoPelaajat(2);
        this.pelilauta.siirraNappulaRuutuun(this.pelilauta.getPelaajat().get(0).getNappulat().get(0), 15);
        assertTrue(this.pelilauta.minkaVarinenNappula(76) == null);
    }
    
      @Test
    public void minkaVarinenNappulaRuudussaEiNappulaa() {
        this.pelilauta.luoPelaajat(2);
        assertTrue(this.pelilauta.minkaVarinenNappula(15) == null);
    }

    @Test
    public void seuraavanVuoroAsettaaNappulatValittaviksiFalse() {
        this.pelilauta.luoPelaajat(2);
        this.pelilauta.seuraavanVuoro();
        assertTrue(this.pelilauta.getRuutu(44).onkoValittava() == false);
    }

 

    @Test
    public void seuraavanVuoroKierrosOikein() {
        this.pelilauta.luoPelaajat(2);
        for (int i = 0; i < 4; i++) {
            this.pelilauta.seuraavanVuoro();
        }
        assertTrue(this.pelilauta.kenenVuoro().equals(VARI.SININEN));
    }
    
    @Test
    public void seuraavanVuoroVihreastaSiniseen() {
        this.pelilauta.luoPelaajat(4);
        this.pelilauta.setSiirtoVuoro(VARI.VIHREA, 6);
        this.pelilauta.seuraavanVuoro();
        assertTrue(this.pelilauta.kenenVuoro().equals(VARI.SININEN));
    }
    
    @Test
    public void setSiirtovuoroToimiiVari(){
        this.pelilauta.luoPelaajat(2);
        this.pelilauta.setSiirtoVuoro(VARI.SININEN, 6);
        assertTrue(this.pelilauta.kenenVuoro().equals(VARI.SININEN));
    }
    
    @Test
    public void setSiirtovuoroToimiiNoppa(){
        this.pelilauta.luoPelaajat(2);
        this.pelilauta.setSiirtoVuoro(VARI.SININEN, 6);
        assertTrue(this.pelilauta.silmalukuNyt() == 6);
    }
    
    @Test
    public void siirraAloitusruudustaReturnOikein(){
        this.pelilauta.luoPelaajat(2);
        assertTrue(this.pelilauta.siirraAloitusruudusta(this.pelilauta.getPelaaja(VARI.SININEN).getNappulat().get(0)) == 0);
    }
    
    @Test
    public void siirraAloitusruudustaSiirtaaNappulan(){
        this.pelilauta.luoPelaajat(2);
        this.pelilauta.siirraAloitusruudusta(this.pelilauta.getPelaaja(VARI.SININEN).getNappulat().get(0));
        assertTrue(this.pelilauta.getPelaaja(VARI.SININEN).getNappulat().get(0).getSijainti() == 0);
    }

    
    @Test
    public void getEkaAloitusRuutuOikein(){
        assertTrue(this.pelilauta.getEkaAloitusruutu(SININEN) == 44);
        assertTrue(this.pelilauta.getEkaAloitusruutu(PUNAINEN) == 48);
        assertTrue(this.pelilauta.getEkaAloitusruutu(KELTAINEN) == 52);
        assertTrue(this.pelilauta.getEkaAloitusruutu(VIHREA) == 56);
    }
    
    @Test
    public void getEkaMaaliRuutuOikein(){
        assertTrue(this.pelilauta.getEnsimmainenMaaliruutu(SININEN) == 40);
        assertTrue(this.pelilauta.getEnsimmainenMaaliruutu(PUNAINEN) == 7);
        assertTrue(this.pelilauta.getEnsimmainenMaaliruutu(KELTAINEN) == 18);
        assertTrue(this.pelilauta.getEnsimmainenMaaliruutu(VIHREA) == 29);
    }
    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
