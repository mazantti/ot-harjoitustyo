package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 0.10",kortti.toString());
    }
    
    @Test
    public void lataaminenKasvatttaaSaldoaOikein() {
        kortti.lataaRahaa(90);
        assertEquals("saldo: 1.00", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeOikein() {
        kortti.otaRahaa(5);
        assertEquals("saldo: 0.05", kortti.toString());
    }
    
    @Test 
    public void saldoEiVaheneJosEiRahaa() {
        kortti.otaRahaa(11);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    
    @Test
    public void palauttaaTrueJosRahaa() {
        assertEquals("true",""+kortti.otaRahaa(5));
    }
    
    @Test
    public void palauttaaFalseJosEiRahaa() {
        assertEquals("false",""+kortti.otaRahaa(11));
    }
    
    
}
