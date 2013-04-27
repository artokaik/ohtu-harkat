/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.commands;

import com.avaje.ebean.EbeanServer;
import olutopas.IO;
import olutopas.domain.Beer;
import olutopas.domain.Brewery;
import olutopas.domain.Rating;
import olutopas.domain.User;
import olutopas.services.Datamapper;

/**
 *
 * @author Arto
 */
public class BreweryRemover implements Command {

    private IO io;
    private Datamapper mapper;

    public BreweryRemover(IO io, Datamapper mapper) {
        this.io = io;
        this.mapper = mapper;
    }

    @Override
    public void suorita(User user) {
        io.print("to which brewery: ");
        String name = io.read();
        Brewery brewery = mapper.findBrewery(name);

        if (brewery == null) {
            io.print(name + " does not exist");
            return;
        }

        mapper.deleteBrewery(brewery);
        io.print("deleted: " + name);
    }
    
}
