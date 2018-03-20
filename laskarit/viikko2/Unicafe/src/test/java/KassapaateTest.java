/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.unicafe.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jussiste
 */
public class KassapaateTest {

    Kassapaate paate;
    Maksukortti kortti;
    @Before
    public void setUp() {
        paate=new Kassapaate();
    }
    @Test
    public void luotuOikein(){
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void syoEdullisesti(){
        assertEquals(260, paate.syoEdullisesti(500));
        assertEquals(100240, paate.kassassaRahaa());
    }
    @Test
    public void syoMaukkaasti(){
        assertEquals(100, paate.syoMaukkaasti(500));
        assertEquals(100400, paate.kassassaRahaa());
    }
    @Test
    public void rahaEiRiita(){
        assertEquals(300, paate.syoMaukkaasti(300));
        assertEquals(100, paate.syoEdullisesti(100));
        assertEquals(100000, paate.kassassaRahaa());
    }
    @Test
    public void edullinenKortilla(){
        kortti=new Maksukortti(500);
        assertEquals(true, paate.syoEdullisesti(kortti));
        assertEquals(1, paate.edullisiaLounaitaMyyty());
        assertEquals(100000, paate.kassassaRahaa());
    }
    @Test
    public void maukkaastiKortilla(){
        kortti=new Maksukortti(500);
        assertEquals(true, paate.syoMaukkaasti(kortti));
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
        assertEquals(100000, paate.kassassaRahaa());
    }
    @Test
    public void kortillaEiRahaa(){
        kortti=new Maksukortti(200);
        assertEquals(false, paate.syoMaukkaasti(kortti));
        assertEquals(false, paate.syoEdullisesti(kortti));
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertEquals(200, kortti.saldo());
    }
    @Test
    public void lataaRahaaKortille(){
        kortti=new Maksukortti(1000);
        paate.lataaRahaaKortille(kortti, 1000);
        paate.lataaRahaaKortille(kortti, -10);
        assertEquals(2000, kortti.saldo());
        assertEquals(101000, paate.kassassaRahaa());
        paate.lataaRahaaKortille(kortti, -10);
    }
}
