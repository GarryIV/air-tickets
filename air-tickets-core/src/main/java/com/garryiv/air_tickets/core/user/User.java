package com.garryiv.air_tickets.core.user;

import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "USER_TBL")
@ToString

public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue
    private Long id;

    private String email;

    private String accessKey;

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

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }
}
