/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.commands;

import com.avaje.ebean.EbeanServer;
import olutopas.IO;
import olutopas.domain.Beer;
import olutopas.domain.Brewery;
import olutopas.domain.User;
import olutopas.services.Datamapper;

/**
 *
 * @author Arto
 */
public class BreweryFinder implements Command{
    
    private IO io;
    private Datamapper mapper;

    public BreweryFinder(IO io, Datamapper mapper) {
        this.io = io;
        this.mapper = mapper;
    }

    @Override
    public void suorita(User user) {
        io.print("brewery to find: ");
        String n = io.read();
        Brewery foundBrewery = mapper.findBrewery(n);

        if (foundBrewery == null) {
            io.print(n + " not found");
            return;
        }

        io.print(foundBrewery);
        for (Beer bier : foundBrewery.getBeers()) {
            io.print("   " + bier.getName());
        }
    }
    
}
