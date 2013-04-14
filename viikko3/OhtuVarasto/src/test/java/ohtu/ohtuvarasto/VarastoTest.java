package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaAlleNollanVarastollaOikeaTilavuus() {
        varasto = new Varasto(-0.1);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaPienellaVarastollaOikeaTilavuus() {
        varasto = new Varasto(0.1);
        assertEquals(0.1, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaAlleNollanVarastollaJaAlkusaldollaOikeaTilavuus() {
        varasto = new Varasto(-1, 1);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void alkusaldoOikeinKunSaldoPienempiKuinVarastonKoko() {
        varasto = new Varasto(10, 2);
        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void alkusaldoOikeinKunSaldoIsompiKuinVarastonKoko() {
        varasto = new Varasto(10, 12);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void alkusaldoNollaKunParametriNegatiivinen() {
        varasto = new Varasto(10, -12);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void konstr() {
        varasto = new Varasto(10, 5);
        assertEquals("saldo = 5.0, vielä tilaa 5.0", varasto.toString());
    }

    @Test
    public void lisaaTyhjaanVarastoonAlleNolla() {
        varasto.lisaaVarastoon(-1.0);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaaTyhjaanVarastoonNolla() {
        varasto.lisaaVarastoon(0);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaaTyhjaanVarastoonKaksiJaKolme() {
        varasto.lisaaVarastoon(2);
        varasto.lisaaVarastoon(3);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaaTyhjaanVarastoonViisiJaViisi() {
        varasto.lisaaVarastoon(5);
        varasto.lisaaVarastoon(5);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaaTyhjaanVarastoonKuusiJaViisi() {
        varasto.lisaaVarastoon(6);
        varasto.lisaaVarastoon(5);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaaTyhjaanVarastoonKuusiJaOtaAlleNollaPalauttaaNolla() {
        varasto.lisaaVarastoon(6);
        assertEquals(0, varasto.otaVarastosta(-2), vertailuTarkkuus);
    }

    @Test
    public void lisaaTyhjaanVarastoonKuusiJaOtaAlleNollaEiMuutaVarastosaldoa() {
        varasto.lisaaVarastoon(6);
        varasto.otaVarastosta(-2);
        assertEquals(6, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaaTyhjaanVarastoonNeljaJaOtaNollaPalauttaaNolla() {
        varasto.lisaaVarastoon(4);
        assertEquals(0, varasto.otaVarastosta(0), vertailuTarkkuus);
    }

    @Test
    public void lisaaTyhjaanVarastoonViisiJaOtaNollaEiMuutaSaldoa() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(0);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaaTyhjaanVarastoonKuusiJaOtaViisiPalauttaaViisi() {
        varasto.lisaaVarastoon(6);
        assertEquals(5, varasto.otaVarastosta(5), vertailuTarkkuus);
    }

    @Test
    public void lisaaTyhjaanVarastoonKuusiJaOtaViisiJaSaldoOnYksi() {
        varasto.lisaaVarastoon(6);
        varasto.otaVarastosta(5);
        assertEquals(1, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaaTyhjaanVarastoonKolmeJaOtaViisiPalauttaaKolme() {
        varasto.lisaaVarastoon(3);
        assertEquals(3, varasto.otaVarastosta(5), vertailuTarkkuus);
    }

    @Test
    public void lisaaTyhjaanVarastoonKolmeJaOtaViisiJaSaldoOnNolla() {
        varasto.lisaaVarastoon(3);
        varasto.otaVarastosta(5);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
}