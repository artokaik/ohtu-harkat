package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;
 
public class Main {
 
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Anna opiskelijanumero: ");
        String studentNr = scanner.nextLine();
        if ( args.length>0) {
            studentNr = args[0];
        }
 
        String url = "http://ohtustats-2013.herokuapp.com/opiskelija/"+studentNr+".json";
 
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        client.executeMethod(method);
 
        InputStream stream =  method.getResponseBodyAsStream();
 
        String bodyText = IOUtils.toString(stream);
 
        System.out.println("opiskelijanumero " + studentNr);
 
        Gson mapper = new Gson();
        Palautukset palautukset = mapper.fromJson(bodyText, Palautukset.class);
        System.out.println("");
        for (Palautus palautus : palautukset.getPalautukset()) {
            System.out.println( palautus );
        }
        
        System.out.println("");
        
        System.out.println("yhteensä " + palautukset.sumTehtavat() + " tehtävää " + palautukset.sumTunnit() + " tuntia" );
 
    }
}

