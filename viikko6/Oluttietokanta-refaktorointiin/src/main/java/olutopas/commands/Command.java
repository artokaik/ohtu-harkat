/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.commands;

import olutopas.domain.User;

/**
 *
 * @author Arto
 */
public interface Command {
    
    public void suorita(User user);
    
}
