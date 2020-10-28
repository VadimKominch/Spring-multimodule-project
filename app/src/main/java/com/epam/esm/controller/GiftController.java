package com.epam.esm.controller;

import com.epam.esm.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GiftController {

    private GiftService giftService;
    @Autowired
    public GiftController(GiftService giftService) {
        this.giftService = giftService;
        System.out.println(this.giftService == null);
    }

    @GetMapping(value = "/")
    public String getString() {
        System.out.println("1");
        return "Hello,world";
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public @ResponseBody String getGiftById(@PathVariable int id) {
        giftService.getById(id);
        System.out.println("1");
        return "Hello,world!";
    }
}
