/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webkauppa.ohjaus;

import com.mycompany.webkauppa.sovelluslogiikka.Ostoskori;

/**
 *
 * @author Arto
 */
public interface Komentotehdas {

    public Komento ostoksenLisaysKoriin(Ostoskori ostoskori, long tuoteId);

    public Komento ostoksenPoistoKorista(Ostoskori ostoskori, long tuoteId);

    public Komento ostoksenSuoritus(String nimi, String osoite, String luottokorttinumero, Ostoskori kori);
}
