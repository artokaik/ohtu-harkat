/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.services;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import javax.persistence.OptimisticLockException;
import olutopas.domain.Beer;
import olutopas.domain.Brewery;
import olutopas.domain.Rating;
import olutopas.domain.User;

/**
 *
 * @author Arto
 */
public class BasicDatamapper implements Datamapper {

    private EbeanServer server;

    public BasicDatamapper(EbeanServer server) {
        this.server = server;
    }

    @Override
    public Brewery findBrewery(String n) {
        return server.find(Brewery.class).where().like("name", n).findUnique();
    }

    @Override
    public Beer findBeer(String n) {
        return server.find(Beer.class).where().like("name", n).findUnique();
    }

    @Override
    public void addRating(Beer foundBeer, User user, int value) {
        Rating rating = new Rating(foundBeer, user, value);
        server.save(rating);
    }

    @Override
    public void save(Object object) {
        server.save(object);
    }

    @Override
    public List<Brewery> listBreweries() {
        return server.find(Brewery.class).findList();
    }

    @Override
    public void deleteBeer(Beer beer) {
        server.delete(beer);
    }

    @Override
    public List<Beer> listBeers() {
        return server.find(Beer.class).findList();
    }

    @Override
    public void deleteBrewery(Brewery brewery) {
        server.delete(brewery);
    }

    @Override
    public List<User> listUsers() {
        return server.find(User.class).findList();
    }
    
    @Override
    public void seedDatabase() throws OptimisticLockException {
        Brewery brewery = new Brewery("Schlenkerla");
        brewery.addBeer(new Beer("Urbock"));
        brewery.addBeer(new Beer("Lager"));
        // tallettaa myös luodut oluet, sillä Brewery:n OneToMany-mappingiin on määritelty
        // CascadeType.all
        server.save(brewery);


        // luodaan olut ilman panimon asettamista
        Beer b = new Beer("Märzen");
        server.save(b);

        // jotta saamme panimon asetettua, tulee olot lukea uudelleen kannasta
        b = server.find(Beer.class, b.getId());
        brewery = server.find(Brewery.class, brewery.getId());
        brewery.addBeer(b);
        server.save(brewery);

        server.save(new Brewery("Paulaner"));

        server.save(new User("mluukkai"));
    }

    @Override
    public User findUser(String n) {
        return server.find(User.class).where().like("name", n).findUnique();
    }
}
