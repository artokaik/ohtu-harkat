/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Arto
 */
@Entity
public class Rating {

    @Id
    private Integer id;
    @ManyToOne
    private Beer beer;
    @ManyToOne
    private User user;
    private int value;

    public Rating() {
    }

    public Integer getId() {
        return id;
    }

    public Beer getBeer() {
        return beer;
    }

    public User getUser() {
        return user;
    }

    public int getValue() {
        return value;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
