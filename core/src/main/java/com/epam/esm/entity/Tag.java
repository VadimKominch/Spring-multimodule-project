package com.epam.esm.entity;

import com.epam.esm.converter.DateConverter;

import java.util.Date;

public class Tag {
    private String name;
    private String date;
    private DateConverter converter;

    public Tag(String name) {
        this.name = name;
        this.converter = new DateConverter();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = converter.formatDate(date);
    }
}
