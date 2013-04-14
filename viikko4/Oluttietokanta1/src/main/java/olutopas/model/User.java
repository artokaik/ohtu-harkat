/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Arto
 */
@Entity
public class User {

    private String username;
    @Id
    private Integer id;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public Integer getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return username;
    }
    
    
}
