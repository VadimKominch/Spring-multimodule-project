package com.epam.esm.controller;

import com.epam.esm.entity.GiftSertificate;
import com.epam.esm.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;
/**
 *
 * Rest Controller for basic crud operations and operations with list of data.
 * Use service as way to connect with database
 * @author Vadim Kominch
 *
 * */
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
/**
 * Get method for reading one entity if its present. Receive id as integer in url path
 * and find entity in database.
 * */
    @GetMapping(value = "/{id}")
    public GiftSertificate getGiftById(@PathVariable int id) {
        return giftService.getById(id);
    }
/**
 * Get method for requesting all existing certificates. Use gift service
 * for receiving info from database.
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

    /**
     * Post method for storing list of data from json format. Entity saved in database. Receive
     * list in JSON.
     * Return 201 status code if request is successful.
     * */
    @PostMapping(value = "/add_all")
    @ResponseStatus(HttpStatus.CREATED)
    public String addAll(@RequestBody List<GiftSertificate> certificates) {
        certificates.forEach(el->giftService.save(el));
        return "OK";
    }
    /**
     * Delete method for deleting entity by id from database. Receive id as integer
     * and find entity with id in database.
     * */
    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteOne(@PathVariable int id) {
        giftService.delete(id);
        return "OK";
    }
}
