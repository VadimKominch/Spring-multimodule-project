package com.epam.esm.controller;

import com.epam.esm.entity.GiftSertificate;
import com.epam.esm.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
    }

    @GetMapping(value = "/")
    public String getString() {
        System.out.println("1");
        return "Hello,world";
    }

    @GetMapping(value = "/{id}")
    public GiftSertificate getGiftById(@PathVariable int id) {
        return giftService.getById(id);
    }
}
