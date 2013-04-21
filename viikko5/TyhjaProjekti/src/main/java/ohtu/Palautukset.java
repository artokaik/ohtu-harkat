
package ohtu;

import java.util.ArrayList;
import java.util.List;

public class Palautukset {
    List<Palautus> palautukset = new ArrayList<Palautus>();

    public void setPalautukset(List<Palautus> palautukset) {
        this.palautukset = palautukset;
    }

    public List<Palautus> getPalautukset() {
        return palautukset;
    }
    
    public int sumTunnit(){
        int tunnit = 0;
        for (Palautus palautus : palautukset) {
            tunnit += palautus.getTunteja();
        }
        return tunnit;
    }
    
        public int sumTehtavat(){
        int tehtavat = 0;
        for (Palautus palautus : palautukset) {
            tehtavat += palautus.getTehtavia();
        }
        return tehtavat;
    }
    
    
}
