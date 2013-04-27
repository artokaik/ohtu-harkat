/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.commands;

import com.avaje.ebean.EbeanServer;
import olutopas.IO;
import olutopas.domain.Beer;
import olutopas.domain.Rating;
import olutopas.domain.User;
import olutopas.services.Datamapper;

/**
 *
 * @author Arto
 */
public class BeerRemover implements Command {

    private IO io;
    private Datamapper mapper;

    public BeerRemover(IO io, Datamapper mapper) {
        this.io = io;
        this.mapper = mapper;
    }

    @Override
    public void suorita(User user) {
        io.print("beer to delete: ");
        String n = io.read();
        Beer beerToDelete = mapper.findBeer(n);

        if (beerToDelete == null) {
            io.print(n + " not found");
            return;
        }

        mapper.deleteBeer(beerToDelete);
        io.print("deleted: " + beerToDelete);

    }
    
}
