package com.garryiv.air_tickets.core.services.user;

import com.garryiv.air_tickets.core.services.EntitySupport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User extends EntitySupport {
    @Id
    @Column(name = "user_id")
    @GeneratedValue
    private Long id;

    private String email;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
