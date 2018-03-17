package com.skip.dishes.hackathon.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Store implements Serializable {
    private static final long serialVersionUID = 4646382914183046493L;

    private Long id;
    private String name;
    private String address;
    private Long cousineId;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getCousineId() {
        return cousineId;
    }

    public void setCousineId(Long cousineId) {
        this.cousineId = cousineId;
    }
}
