package com.epam.esm.entity;

import java.util.Date;

public class GiftSertificate {
    private String name;
    private String description;
    private double price;
    private Date creationDate;
    private Date lastUpdateDate;
    private double duration;

    public GiftSertificate() {
    }

    public GiftSertificate(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "GiftSertificate{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
