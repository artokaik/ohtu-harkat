/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas;

import com.avaje.ebean.EbeanServer;
import olutopas.services.BasicDatamapper;
import olutopas.services.DBConfigurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Arto
 */
public class MainTest {

    IOStub io;

    enum Database {

        H2, SQLite
    }

    public MainTest() {
    }

    @Before
    public void setUp() {
        io = new IOStub();
    }

    @Test
    public void KirjautuminenSisaanJaPoistuminenOnnistuu() {
        io.lisaaSyote("mluukkai");
        io.lisaaSyote("q");
        try {
            ajaOhjelma();
        } catch (Exception e) {
        }
        Assert.assertTrue(io.getTulosteet().contains("\nWelcome to Ratebeer mluukkai"));
        Assert.assertTrue(io.getTulosteet().contains("bye"));
    }

    @Test
    public void uudenHenkilonLisaysOnnistuu() {
        io.lisaaSyote("?");
        io.lisaaSyote("arto");
        io.lisaaSyote("arto");
        io.lisaaSyote("q");
        try {
            ajaOhjelma();
        } catch (Exception e) {
            try {
                ajaOhjelma();
            } catch (Exception ex) {
            }
        }
        Assert.assertTrue(io.getTulosteet().contains("\nWelcome to Ratebeer arto"));
        Assert.assertTrue(io.getTulosteet().contains("bye"));
    }

    @Test
    public void kaljanLisaysJaListausToimii() {
        io.lisaaSyote("mluukkai");
        io.lisaaSyote("3");
        io.lisaaSyote("Paulaner");
        io.lisaaSyote("kalja");
        io.lisaaSyote("");
        io.lisaaSyote("6");
        io.lisaaSyote("");
        io.lisaaSyote("q");
        try {
            ajaOhjelma();
        } catch (Exception e) {
        }
        Assert.assertTrue(io.tulosteetSisaltaa("kalja added to Paulaner"));
        Assert.assertTrue(io.tulosteetSisaltaa("kalja (Paulaner)"));
    }

    @Test
    public void panimonLisaysJaListausToimii() {
        io.lisaaSyote("mluukkai");
        io.lisaaSyote("8");
        io.lisaaSyote("Hartwall");
        io.lisaaSyote("");
        io.lisaaSyote("4");
        io.lisaaSyote("");
        io.lisaaSyote("3");
        io.lisaaSyote("Hartwall");
        io.lisaaSyote("Karjala");
        io.lisaaSyote("");
        io.lisaaSyote("4");
        io.lisaaSyote("");
        io.lisaaSyote("6");
        io.lisaaSyote("");
        io.lisaaSyote("q");
        try {
            ajaOhjelma();
        } catch (Exception e) {
        }
        Assert.assertTrue(io.tulosteetSisaltaa("Hartwall, beers: 0"));
        Assert.assertTrue(io.tulosteetSisaltaa("Karjala added to Hartwall"));
        Assert.assertTrue(io.tulosteetSisaltaa("Hartwall, beers: 1"));
        Assert.assertTrue(io.tulosteetSisaltaa("Karjala (Hartwall)"));
    }

    private void ajaOhjelma() throws Exception {
        boolean dropAndCreateTables = true;
        EbeanServer server = DBConfigurator.initializeSQLiteDatabase(dropAndCreateTables);
        new Application(new BasicDatamapper(server), io).run(dropAndCreateTables);
    }
}
