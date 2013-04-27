/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas;

import java.util.ArrayList;

/**
 *
 * @author Arto
 */
public class IOStub implements IO {

    private ArrayList<String> syotteet;
    private ArrayList<String> tulosteet;
    private int seuraavaSyote;

    public IOStub() {
        this.syotteet = new ArrayList<String>();
        this.tulosteet = new ArrayList<String>();
        seuraavaSyote = 0;
    }

    @Override
    public void print(Object text) {
        System.out.println(text);
        tulosteet.add(text.toString());
    }

    @Override
    public String read() {
        seuraavaSyote++;
        return syotteet.get(seuraavaSyote - 1);
    }

    public void lisaaSyote(String syote) {
        syotteet.add(syote);
    }

    public String haeTulostus(int monesko) {
        return tulosteet.get(monesko);
    }

    public ArrayList<String> getTulosteet() {
        return tulosteet;
    }

    public boolean tulosteetSisaltaa(String teksti) {
        for (String string : tulosteet) {
            if(string.contains(teksti)){
                return true;
            }
        }
        return false;
    }
}
