/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.commands;

import java.util.List;
import olutopas.IO;
import olutopas.domain.Beer;
import olutopas.domain.User;
import olutopas.services.Datamapper;

/**
 *
 * @author Arto
 */
public class BeerLister implements Command {

    private IO io;
    private Datamapper mapper;

    public BeerLister(IO io, Datamapper mapper) {
        this.io = io;
        this.mapper = mapper;
    }

    @Override
    public void suorita(User user) {
        List<Beer> beers = mapper.listBeers();
        for (Beer beer : beers) {
            io.print(beer);
            if (beer.getRatings() != null && beer.getRatings().size() != 0) {
                io.print("  ratings given "+beer.getRatings().size() + " average " + beer.averageRating());
            } else {
                io.print("  no ratings");
            }
        }
    }
}
