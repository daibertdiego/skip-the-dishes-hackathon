package com.skip.dishes.hackathon.model;

import java.io.Serializable;

public class Cousine implements Serializable {
    private static final long serialVersionUID = 2596092457704917790L;
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}