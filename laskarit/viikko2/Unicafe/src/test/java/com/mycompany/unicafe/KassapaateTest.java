/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mazantti
 */
public class KassapaateTest {
    
    Kassapaate kassa;
    
    @Before
    public void setup() {
        kassa = new Kassapaate();
    }
    
    @Test
    public void luotuKassaOlemassa() {
        assertTrue(kassa!=null);      
    }
    
    @Test
    public void oikeaMaaraAlkurahaa() {
        assertTrue(kassa.kassassaRahaa()==100000);
    }
    
    @Test
    public void eiMyytyjaLounaitaAlussa() {
        assertTrue(Math.abs(kassa.edullisiaLounaitaMyyty())+Math.abs(kassa.maukkaitaLounaitaMyyty())==0);
    }
    
    @Test 
    public void syoEdullisestiToimiiKunMaksuRiittava() {
        kassa.syoEdullisesti(240);
        assertTrue(kassa.edullisiaLounaitaMyyty()==1 && kassa.kassassaRahaa() == 100000+240);
    }
    
    @Test 
    public void syoEdullisestiVaihtorahaOikea() {
        assertTrue(kassa.syoEdullisesti(300) == 60);
    }
    
    @Test 
    public void syoMaukkaastiToimiiKunMaksuRiittava() {
        kassa.syoMaukkaasti(400);
        assertTrue(kassa.maukkaitaLounaitaMyyty()==1 && kassa.kassassaRahaa() == 100000+400);
    }
    
    @Test 
    public void syoMaukkaastiVaihtorahaOikea() {
        assertTrue(kassa.syoMaukkaasti(500) == 100);
    }
    
    @Test 
    public void syoEdullisestiToimiiJosMaksuEiRiita() {
        kassa.syoEdullisesti(200);
        assertTrue(kassa.edullisiaLounaitaMyyty()==0 && kassa.kassassaRahaa() == 100000);
    }
    
    @Test
    public void syoEdullisestiEiVaihtorahaaJosMaksuEiRiita() {
        assertTrue(kassa.syoEdullisesti(200) == 200);
    }
    
    @Test 
    public void syoMaukkaastiToimiiJosMaksuEiRiita() {
        kassa.syoMaukkaasti(200);
        assertTrue(kassa.maukkaitaLounaitaMyyty()==0 && kassa.kassassaRahaa() == 100000);
    }
    
    @Test
    public void syoMaukkaastiEiVaihtorahaaJosMaksuEiRiita() {
        assertTrue(kassa.syoMaukkaasti(200) == 200);
    }
    
    @Test
    public void syoMaukkaastiKortillaRahaaVeloittaaOikein() {
        Maksukortti kortti = new Maksukortti(500);
        kassa.syoMaukkaasti(kortti);
        assertTrue(kortti.saldo() == 100);
    }
    @Test
    public void syoEdullisestiKortillaRahaaVeloittaaOikein() {
        Maksukortti kortti = new Maksukortti(500);
        kassa.syoEdullisesti(kortti);
        assertTrue(kortti.saldo() == 260);
    }
    
    @Test
    public void syoEdullisestiKortillaIlmoittaaKunRahaa() {
        assertTrue(kassa.syoEdullisesti(new Maksukortti(400)));
    }
    
    @Test
    public void syoMaukkaastiKortillaIlmoittaaKunRahaa() {
        assertTrue(kassa.syoMaukkaasti(new Maksukortti(400)));
    }
    
    @Test
    public void syoEdullisestiKortillaMyytyjenMaaraKasvaa() {
        kassa.syoEdullisesti(new Maksukortti(400));
        assertTrue(kassa.edullisiaLounaitaMyyty()==1);
    }
   
    @Test
    public void syoEMaukkaastiKortillaMyytyjenMaaraKasvaa() {
        kassa.syoMaukkaasti(new Maksukortti(400));
        assertTrue(kassa.maukkaitaLounaitaMyyty()==1);
    }
    
    @Test
    public void syoEdullisestiKortillaEiRahaaEiVeloiteta() {
        Maksukortti kortti = new Maksukortti(100);
        kassa.syoEdullisesti(kortti);
        assertTrue(100==kortti.saldo());
    }
    
    @Test
    public void syoMaukkaastiKortillaEiRahaaEiVeloiteta() {
        Maksukortti kortti = new Maksukortti(100);
        kassa.syoMaukkaasti(kortti);
        assertTrue(100==kortti.saldo());
    }
    
    @Test
    public void syoEdullisestiKortillaEiRahaaEiTienata() {
        Maksukortti kortti = new Maksukortti(100);
        kassa.syoEdullisesti(kortti);
        assertTrue(100000==kassa.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiKortillaEiRahaaEiTienata() {
        Maksukortti kortti = new Maksukortti(100);
        kassa.syoMaukkaasti(kortti);
        assertTrue(100000==kassa.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisestiKortillaEiRahaaMyytyjenMaaraEiKasva() {
        kassa.syoEdullisesti(new Maksukortti(100));
        assertTrue(kassa.edullisiaLounaitaMyyty()==0);
    }
    
    @Test
    public void syoMaukkaastiKortillaEiRahaaMyytyjenMaaraEiKasva() {
        kassa.syoMaukkaasti(new Maksukortti(100));
        assertTrue(kassa.maukkaitaLounaitaMyyty()==0);
    }
    
    @Test 
    public void syoEdullisestiKortillaEiRahaaPalautaFalse() {
        assertFalse(kassa.syoEdullisesti(new Maksukortti(100)));
    }
    
    @Test 
    public void syoMaukkaastiKortillaEiRahaaPalautaFalse() {
        assertFalse(kassa.syoMaukkaasti(new Maksukortti(100)));
    }
    
    @Test
    public void edullinenKorttimaksuEiMuutaKassanSaldoa() {
        kassa.syoEdullisesti(new Maksukortti(1000));
        assertTrue(100000==kassa.kassassaRahaa());
    }
    
    @Test
    public void maukasKorttimaksuEiMuutaKassanSaldoa() {
        kassa.syoMaukkaasti(new Maksukortti(1000));
        assertTrue(100000==kassa.kassassaRahaa());
    }
    
    @Test 
    public void lataaminenKasvattaaKassanSaldoaOikein() {
        kassa.lataaRahaaKortille(new Maksukortti(0), 1000);
        assertTrue(100000+1000 == kassa.kassassaRahaa());
    }
    
    @Test
    public void lataaminenKasvattaaKortinSaldoaOiken() {
        Maksukortti kortti = new Maksukortti(0);
        kassa.lataaRahaaKortille(kortti, 1000);
        assertTrue(1000 == kortti.saldo());
    }
    
    @Test
    public void eiVoiLadataNegatiivistaSummaa() {
        Maksukortti kortti = new Maksukortti(1000);
        kassa.lataaRahaaKortille(kortti, -1000);
        assertTrue(1000 == kortti.saldo() && kassa.kassassaRahaa() == 100000);
    }
}
