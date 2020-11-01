package com.epam.esm.entity;


import com.epam.esm.converter.DateConverter;

import java.util.Date;

public class GiftSertificate {
    private String name;
    private String description;
    private double price;
    private String creationDate;

    private DateConverter converter;
    /*private Date lastUpdateDate;
    private double duration;*/

    public GiftSertificate() {
        this.converter = new DateConverter();
    }

    public GiftSertificate(String name, String description, double price,String creationDate) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.creationDate = creationDate;
        this.converter = new DateConverter();
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = converter.formatDate(date);
    }

    @Override
    public String toString() {
        return "GiftSertificate{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
