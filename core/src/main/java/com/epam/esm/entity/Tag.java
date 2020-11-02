package com.epam.esm.entity;

import com.epam.esm.converter.DateConverter;

import java.util.Date;

public class Tag {

    private String name;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
