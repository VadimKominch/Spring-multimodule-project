package com.epam.esm.controller;

import com.epam.esm.entity.GiftSertificate;
import com.epam.esm.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GiftController {

    private GiftService giftService;
    @Autowired
    public GiftController(GiftService giftService) {
        this.giftService = giftService;
    }

    @GetMapping(value = "/")
    public String getString() {
        return "Hello,world";
    }

    @GetMapping(value = "/{id}")
    public GiftSertificate getGiftById(@PathVariable int id) {
        return giftService.getById(id);
    }
/**
 * Get method for requesting all existing Sertificates. Use gift service for reciveing info from database
 * @see GiftService
 * */
    @GetMapping(value = "/all")
    public List<GiftSertificate> getAll() {
        return giftService.getAll();
    }

    /**
     * Rest endpoint for getting user from request body. Post method is used.
     * Entity is saved in database.
     * */
    @PostMapping(value="/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveEntity(@RequestBody GiftSertificate giftSertificate) {
        giftService.save(giftSertificate);
        return "OK";
    }
}
