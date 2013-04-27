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
public class BeerFinder implements Command {

    private IO io;
    private Datamapper mapper;

    public BeerFinder(IO io, Datamapper mapper) {
        this.io = io;
        this.mapper = mapper;
    }

    @Override
    public void suorita(User user) {
        io.print("beer to find: ");
        String n = io.read();
        Beer foundBeer = mapper.findBeer(n);

        if (foundBeer == null) {
            io.print(n + " not found");
            return;
        }

        io.print(foundBeer);

        if (foundBeer.getRatings() != null && foundBeer.getRatings().size() != 0) {
            io.print("  number of ratings: " + foundBeer.getRatings().size() + " average " + foundBeer.averageRating());
        } else {
            io.print("no ratings");
        }

        io.print("give rating (leave emtpy if not): ");
        try {
            int rating = Integer.parseInt(io.read());
            mapper.addRating(foundBeer, user, rating);
        } catch (Exception e) {
        }
    }
    
}
