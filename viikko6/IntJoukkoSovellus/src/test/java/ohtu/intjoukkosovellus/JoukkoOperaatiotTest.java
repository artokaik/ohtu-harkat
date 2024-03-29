
package ohtu.intjoukkosovellus;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JoukkoOperaatiotTest {
    
    
    @Test
    public void testSomething() {
        IntJoukko eka = teeJoukko(1,2);
        IntJoukko toka = teeJoukko(3,4);
        
        IntJoukko tulos = IntJoukko.yhdiste(eka, toka);
        int[] vastauksenLuvut = tulos.toIntArray();
        Arrays.sort(vastauksenLuvut);
        
        int[] odotettu = {1,2,3,4};
        
        assertArrayEquals(odotettu, vastauksenLuvut);        
    } 

    private IntJoukko teeJoukko(int... luvut) {
        IntJoukko joukko = new IntJoukko();
        
        for (int luku : luvut) {
            joukko.lisaa(luku);
        }
        
        return joukko;
    }
    @Test
        public void testLeikkaus() {
        IntJoukko eka = teeJoukko(1,2,3,4,5);
        IntJoukko toka = teeJoukko(3,4,5,6,7,8,9);
        
        IntJoukko tulos = IntJoukko.leikkaus(eka, toka);
        int[] vastauksenLuvut = tulos.toIntArray();
        Arrays.sort(vastauksenLuvut);
        
        int[] odotettu = {3,4,5};
        
        assertArrayEquals(odotettu, vastauksenLuvut);        
    } 
    
        @Test
        public void testErotus() {
        IntJoukko eka = teeJoukko(1,2,3,4,5);
        IntJoukko toka = teeJoukko(3,4,5,6,7,8,9);
        
        IntJoukko tulos = IntJoukko.erotus(eka, toka);
        int[] vastauksenLuvut = tulos.toIntArray();
        Arrays.sort(vastauksenLuvut);
        
        int[] odotettu = {1,2};
        
        assertArrayEquals(odotettu, vastauksenLuvut);        
    } 
}
