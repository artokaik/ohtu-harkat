/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webkauppa.ohjaus;

import com.mycompany.webkauppa.sovelluslogiikka.Ostoskori;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arto
 */
public class KomentojenTehtailija implements Komentotehdas{

    public KomentojenTehtailija() {
    }

    @Override
    public Komento ostoksenLisaysKoriin(Ostoskori ostoskori, long tuoteId) {
        return new OstoksenLisaysKoriin(ostoskori, tuoteId);
    }

    @Override
    public Komento ostoksenPoistoKorista(Ostoskori ostoskori, long tuoteId) {
        return new OstoksenPoistoKorista(ostoskori, tuoteId);
    }

    @Override
    public Komento ostoksenSuoritus(String nimi, String osoite, String luottokorttinumero, Ostoskori kori) {
        return new OstoksenSuoritus(nimi, osoite, luottokorttinumero, kori) ;
    }
}
