package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(KAPASITEETTI,OLETUSKASVATUS);
    }
    

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti,OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");//heitin vaan jotain :D
        }
        ljono = new int[kapasiteetti];
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            ljono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % ljono.length == 0) {
                kasvataTaulukonKokoa();
            }
            return true;
        }
        return false;
    }

    private void kasvataTaulukonKokoa() {
        int[] uusiLukujono = new int[alkioidenLkm + kasvatuskoko];
        for (int i = 0; i < ljono.length; i++) {
            uusiLukujono[i] = ljono[i];
        }
        ljono = uusiLukujono;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                return true;
            }
        }
        return false;

    }

    private int haeIndeksi(int luku) {
        for (int i = 0; i < ljono.length; i++) {
            if (ljono[i] == luku) {
                return i;
            }
        }
        return -1;
    }

    public boolean poista(int luku) {
        int i = haeIndeksi(luku);
        ljono[i] = ljono[alkioidenLkm - 1];
        ljono[alkioidenLkm - 1] = 0;
        alkioidenLkm -= 1;
        return false;
    }

    private IntJoukko teeKopio() {
        IntJoukko x = new IntJoukko();
        int[] luvut = this.toIntArray();
        for (int i = 0; i < this.alkioidenLkm; i++) {
            x.lisaa(luvut[i]);            
        }
        return x;
    }
    
    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        String tuotos = "{";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            tuotos += ljono[i];
            tuotos += ", ";
        }
        tuotos += ljono[alkioidenLkm - 1];
        tuotos += "}";
        return tuotos;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {        
        IntJoukko x = a.teeKopio();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            if (b.kuuluu(aTaulu[i])) {
                x.lisaa(aTaulu[i]);
            }
        }
        return x;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            if (!b.kuuluu(aTaulu[i])) {
                x.lisaa(aTaulu[i]);
            }
        }
        return x;
    }
}