package ohtu.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Kauppa {

    private VarastoInterface varasto;
    private PankkiInterface pankki;
    private Ostoskori ostoskori;
    private ViitegeneraattoriInterface viitegeneraattori;
    private String kaupanTili;

    @Autowired
    public Kauppa(VarastoInterface varasto,PankkiInterface pankki,ViitegeneraattoriInterface viitegeneraattori) {
        this.varasto = varasto;
        this.pankki = pankki;
        this.viitegeneraattori = viitegeneraattori;
        kaupanTili = "33333-44455";
    }

    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        Tuote t = varasto.haeTuote(id); 
        varasto.palautaVarastoon(t);
    }

    public void lisaaKoriin(int id) {
        if (varasto.saldo(id)>0) {
            Tuote t = varasto.haeTuote(id);             
            ostoskori.lisaa(t);
            varasto.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattori.uusi();
        int summa = ostoskori.hinta();
        
        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }
    
        public boolean paskametodi(String nimi, String tiliNumero) {
        int viite = viitegeneraattori.uusi();
        int summa = ostoskori.hinta();
        
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    for (int k = 0; k < 2; k++) {
                        int h = 1;
                        
                    }
                    if(1>2){
                        if(2>3 && 3>4 && 5>6 && 7>8 || 9 >10){
                            if(3==4){
                            int x = 2;
                            }
                        }
                    }
                    
                }
                
            }
                        for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    if(1>2){
                        if(2>3 && 3>4 && 5>6 && 7>8 || 9 >10){
                            int x = 2;
                            
                        }
                    }
                    
                }
                
            }
        
        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
        
        
    }
    
    

}
