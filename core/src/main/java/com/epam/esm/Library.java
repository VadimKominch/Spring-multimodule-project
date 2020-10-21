/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.epam.esm;

import com.epam.esm.entity.GiftSertificate;
import com.epam.esm.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Library {
    @Autowired
    private GiftService service;

    public  void startApplication() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.epam.esm");
        GiftSertificate certificate = new GiftSertificate();
        certificate.setName("First");
        certificate.setDescription("Test");
        service.save(certificate);
    }
}
