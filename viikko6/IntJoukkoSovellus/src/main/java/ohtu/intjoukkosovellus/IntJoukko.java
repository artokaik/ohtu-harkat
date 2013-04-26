package ohtu.intjoukkosovellus;

import java.util.ArrayList;

public class IntJoukko {

    private ArrayList<Integer> ljono;

    public IntJoukko() {
        ljono = new ArrayList<Integer>();
    }

    public IntJoukko(int kapasiteetti) {
        this();
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        this();
    }

    public boolean lisaa(int luku) {
        if (!ljono.contains(luku)) {
            return ljono.add(luku);
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        return ljono.contains(luku);
    }

    public boolean poista(int luku) {
        Integer poistettava = (Integer) luku;
        return ljono.remove(poistettava);

    }

    private IntJoukko teeKopio() {
        IntJoukko uusiJoukko = new IntJoukko();
        for (Integer x : ljono) {
            uusiJoukko.lisaa(x);
        }
        return uusiJoukko;
    }

    public int mahtavuus() {
        return ljono.size();
    }

    @Override
    public String toString() {
        String tuotos = "{";
        tuotos += ljono.toString().substring(1, ljono.toString().length() - 1);
        tuotos += "}";
        return tuotos;
    }

    public int[] toIntArray() {
        int[] taulu = new int[ljono.size()];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono.get(i);
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = a.teeKopio();
        for (int i : b.getLjono()) {
            x.lisaa(i);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        for (int i : b.getLjono()) {
            if (a.kuuluu(i)) {
                x.lisaa(i);
            }
        }
        return x;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko x = a.teeKopio();
        for (int i : b.getLjono()) {
            x.poista(i);
        }
        return x;
    }

    public ArrayList<Integer> getLjono() {
        return ljono;
    }
}