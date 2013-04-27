/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.commands;


import olutopas.IO;
import olutopas.domain.Beer;
import olutopas.domain.Brewery;
import olutopas.domain.User;
import olutopas.services.Datamapper;

/**
 *
 * @author Arto
 */
public class BeerAdder implements Command {

    private IO io;
    private Datamapper mapper;

    public BeerAdder(IO io, Datamapper mapper) {
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

        io.print("beer to add: ");

        name = io.read();

        Beer exists = mapper.findBeer(name);
        if (exists != null) {
            io.print(name + " exists already");
            return;
        }

        brewery.addBeer(new Beer(name));
        mapper.save(brewery);
        io.print(name + " added to " + brewery.getName());
    }
}
