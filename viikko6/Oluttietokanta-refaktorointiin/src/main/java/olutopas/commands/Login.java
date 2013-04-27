/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.commands;

import olutopas.IO;
import olutopas.domain.User;
import olutopas.services.Datamapper;

/**
 *
 * @author Arto
 */
public class Login {
    
    IO io;
    Datamapper mapper;

    public Login(IO io, Datamapper mapper) {
        this.io = io;
        this.mapper = mapper;
    }
   

    public User login() {
        while (true) {
            User user;
            io.print("\nLogin (give ? to register a new user)\n");

            io.print("username: ");
            String name = io.read();

            if (name.equals("?")) {
                registerUser();
                continue;
            }

            user = mapper.findUser(name);

            if (user != null) {
                io.print("\nWelcome to Ratebeer " + user.getName());
                return user;
            }
            io.print("unknown user");
        }
    }

    private void registerUser() {
        io.print("Register a new user");
        io.print("give username: ");
        String name = io.read();
        User u = mapper.findUser(name);
        if (u != null) {
            io.print("user already exists!");
            return;
        }
        mapper.save(new User(name));
        io.print("user created!\n");
    }
}
