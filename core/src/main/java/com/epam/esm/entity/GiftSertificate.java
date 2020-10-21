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

    public GiftSertificate(String name, String description, double price, Date creationDate, Date lastUpdateDate, double duration) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
        this.duration = duration;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}
