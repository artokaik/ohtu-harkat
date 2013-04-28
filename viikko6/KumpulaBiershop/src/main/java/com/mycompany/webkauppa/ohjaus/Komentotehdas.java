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
public class Komentotehdas {

    List<Komento> komennot;

    public Komentotehdas() {
    }

    public Komento ostoksenLisaysKoriin(Ostoskori ostoskori, long tuoteId) {
        return new OstoksenLisaysKoriin(ostoskori, tuoteId);
    }

    public Komento ostoksenPoistoKorista(Ostoskori ostoskori, long tuoteId) {
        return new OstoksenPoistoKorista(ostoskori, tuoteId);
    }

    public Komento ostoksenSuoritus(String nimi, String osoite, String luottokorttinumero, Ostoskori kori) {
        return new OstoksenSuoritus(nimi, osoite, luottokorttinumero, kori) ;
    }
}
