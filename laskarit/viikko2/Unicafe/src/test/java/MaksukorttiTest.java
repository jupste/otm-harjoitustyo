/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.unicafe.Maksukortti;
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
public class MaksukorttiTest {
    
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kortti=new Maksukortti(1000);

    }
    @Test
    public void saldoOikein(){
        assertEquals(1000, kortti.saldo());
    }
    @Test
    public void lisaysOikein(){
        kortti.lataaRahaa(1000);
        assertEquals("saldo: 20.0", kortti.toString());
    }
    @Test
    public void ottaminenVahentaa(){
        kortti.otaRahaa(500);
        assertEquals("saldo: 5.0", kortti.toString());
    }
    @Test
    public void saldoEiMeneMiinukselle(){
        kortti.otaRahaa(2000);
        assertEquals("saldo: 10.0", kortti.toString());
    }
    @Test
    public void onnistunutMetodiPalauttaaTrue(){
        assertEquals(kortti.otaRahaa(500), true);
    }
     @Test
    public void eiOnnistunutMetodiPalauttaaFalse(){
        assertEquals(kortti.otaRahaa(2000), false);
    }
}
