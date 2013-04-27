/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.commands;

import com.avaje.ebean.EbeanServer;
import java.util.List;
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
public class UserLister implements Command{
    
    private IO io;
    private Datamapper mapper;

    public UserLister(IO io, Datamapper mapper) {
        this.io = io;
        this.mapper = mapper;
    }

    @Override
    public void suorita(User user) {
        List<User> users = mapper.listUsers();
        for (User listedUser : users) {
            io.print(listedUser.getName() + " " + listedUser.getRatings().size() + " ratings");
        }
    }
    
}
