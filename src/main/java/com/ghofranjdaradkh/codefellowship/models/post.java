package com.ghofranjdaradkh.codefellowship.models;

import javax.persistence.*;

@Entity
public class post {
@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id ;
    private String body;

    private String createdAt;

    @ManyToOne
    ApplicationUser user ;

    public post(String body, String createdAt, ApplicationUser user) {

        this.body = body;
        this.createdAt = createdAt;
        this.user=user;
    }

    public post() {
    }

    public ApplicationUser getUser() {
        return user;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
