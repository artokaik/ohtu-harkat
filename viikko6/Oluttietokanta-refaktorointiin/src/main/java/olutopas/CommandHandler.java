/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas;

import java.util.HashMap;
import olutopas.commands.BeerAdder;
import olutopas.commands.BeerFinder;
import olutopas.commands.BeerLister;
import olutopas.commands.BeerRemover;
import olutopas.commands.BreweryAdder;
import olutopas.commands.BreweryFinder;
import olutopas.commands.BreweryLister;
import olutopas.commands.BreweryRemover;
import olutopas.commands.Command;
import olutopas.commands.UserLister;
import olutopas.commands.UserRatings;
import olutopas.domain.User;
import olutopas.services.Datamapper;

/**
 *
 * @author Arto
 */
public class CommandHandler {

    private HashMap<String, Command> commands;
    private Datamapper mapper;
    private IO io;
    User user;

    public CommandHandler(Datamapper mapper, IO io, User user) {
        this.mapper = mapper;
        this.io = io;
        setCommands();
    }

    private void setCommands() {
        commands = new HashMap<String, Command>();
        commands.put("1", new BreweryFinder(io, mapper));
        commands.put("2", new BeerFinder(io, mapper));
        commands.put("3", new BeerAdder(io, mapper));
        commands.put("4", new BreweryLister(io, mapper));
        commands.put("5", new BeerRemover(io, mapper));
        commands.put("6", new BeerLister(io, mapper));
        commands.put("7", new BreweryRemover(io, mapper));
        commands.put("8", new BreweryAdder(io, mapper));
        commands.put("9", new UserRatings(io, mapper));
        commands.put("0", new UserLister(io, mapper));
    }
    
    public void suorita(String komento){
        Command command = commands.get(komento);
        if(command==null){
            io.print("unknown command");
        } else{
            command.suorita(user);
        }
    }

}
