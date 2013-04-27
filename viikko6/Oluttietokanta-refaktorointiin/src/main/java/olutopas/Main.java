package olutopas;


import com.avaje.ebean.EbeanServer;
import olutopas.services.BasicDatamapper;
import olutopas.services.DBConfigurator;

public class Main {

    public static void main(String[] args) {
        boolean dropAndCreateTables = true;
        try {
            EbeanServer server = DBConfigurator.initializeSQLiteDatabase(dropAndCreateTables);
            new Application(new BasicDatamapper(server), new BasicIO()).run(dropAndCreateTables);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
