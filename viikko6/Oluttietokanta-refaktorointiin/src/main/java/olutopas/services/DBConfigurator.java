/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.services;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.Transaction;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.SQLitePlatform;
import olutopas.domain.Beer;
import olutopas.domain.Brewery;
import olutopas.domain.Rating;
import olutopas.domain.User;

/**
 *
 * @author Arto
 */
public class DBConfigurator {

    enum Database {

        H2, SQLite
    }

    public static EbeanServer initializeH2Database(boolean dropAndCreateDatabase) throws Exception{
        return initializeDatabase(dropAndCreateDatabase, Database.H2);
    }

    public static EbeanServer initializeSQLiteDatabase(boolean dropAndCreateDatabase) throws Exception{
        return initializeDatabase(dropAndCreateDatabase, Database.SQLite);
    }

    private static EbeanServer initializeDatabase(boolean dropAndCreateDatabase, Database db) throws Exception{
        ServerConfig config = new ServerConfig();
        config.setName("beerDb");

        if (db == Database.H2) {
            DataSourceConfig hdDB = new DataSourceConfig();
            hdDB.setDriver("org.h2.Driver");
            hdDB.setUsername("test");
            hdDB.setPassword("test");
            hdDB.setUrl("jdbc:h2:mem:tests;DB_CLOSE_DELAY=-1");
            hdDB.setHeartbeatSql("select 1 ");
            config.setDataSourceConfig(hdDB);
        }

        if (db == Database.SQLite) {
            DataSourceConfig sqLite = new DataSourceConfig();
            sqLite.setDriver("org.sqlite.JDBC");
            sqLite.setUsername("mluukkai");
            sqLite.setPassword("mluukkai");
            sqLite.setUrl("jdbc:sqlite:beer.db");
            config.setDataSourceConfig(sqLite);
            config.setDatabasePlatform(new SQLitePlatform());
            config.getDataSourceConfig().setIsolationLevel(Transaction.READ_UNCOMMITTED);
        }

        config.setDefaultServer(false);
        config.setRegister(false);
        

        config.addClass(Beer.class);
        config.addClass(Brewery.class);
        config.addClass(User.class);
        config.addClass(Rating.class);

        if (dropAndCreateDatabase) {
            config.setDdlGenerate(true);
            config.setDdlRun(true);
            //config.setDebugSql(true);
        }

        return EbeanServerFactory.create(config);
    }
    
    
}
