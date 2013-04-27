/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.services;

import java.util.List;
import javax.persistence.OptimisticLockException;
import olutopas.domain.Beer;
import olutopas.domain.Brewery;
import olutopas.domain.User;

/**
 *
 * @author Arto
 */
public interface Datamapper {

    public Brewery findBrewery(String n);

    public Beer findBeer(String n);
    
    public User findUser(String n);

    public void addRating(Beer foundBeer, User user, int value);

    public void save(Object object);

    public List<Brewery> listBreweries();

    public List<Beer> listBeers();
    
    public List<User> listUsers();

    public void deleteBeer(Beer beer);
    
    public void deleteBrewery(Brewery brewery);
    
    public void seedDatabase() throws OptimisticLockException;
}
