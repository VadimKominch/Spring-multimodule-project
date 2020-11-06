package com.epam.esm.databaseTest;


import com.epam.esm.MyWebApplicationInitializer;
import com.epam.esm.SpringConfig;
import com.epam.esm.converter.DateConverter;
import com.epam.esm.database.DatabaseDataSource;
import com.epam.esm.entity.GiftSertificate;
import com.epam.esm.service.GiftService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Gift Service.
 * */


@WebAppConfiguration
@ContextConfiguration(classes = {DatabaseDataSource.class, MyWebApplicationInitializer.class,SpringConfig.class})
class ServiceTest {

    private static GiftService giftService;
    private static final List<GiftSertificate> testCertificates = new ArrayList<>();
    private static final int TEST_CERTIFICATES_COUNT = 10;

    @BeforeAll
    static void setup() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.epam.esm");
        context.getEnvironment().addActiveProfile("test");
        context.refresh();
        giftService = context.getBean(GiftService.class);
        for(int i = 0;i< TEST_CERTIFICATES_COUNT;i++) {
            testCertificates.add(new GiftSertificate(i+1,
                    "Name",
                    "description",
                    0.1*(i+1),
                    new DateConverter().formatDate(new Date()),
                    new DateConverter().formatDate(new Date()),
                    (short)25));
        }
        testCertificates.forEach(el->giftService.save(el));
    }

    @Test
    void checkForGetAllMethod() {
        assertEquals(giftService.getAll().size(), TEST_CERTIFICATES_COUNT);
}

    @Test
    void checkForGettingById() {
        int checkedId = 3;
        assertEquals(testCertificates.get(checkedId-1),giftService.getById(checkedId));
    }

    @Test
    void checkForGetByIdForNonExistingEntity() {
        assertNull(giftService.getById(20));
    }

    @Test
    void checkIfValidDataWillBeSaved() {
        assertTrue(giftService.save(testCertificates.get(2)));
        giftService.delete(TEST_CERTIFICATES_COUNT+1);
    }

    /**
     * Change last added certificate to first one. Check if certificate with id of last
     * will be the same as
     * */
    @Test
    void checkIfElementWithValidIdWillBeChangedInDb() {
        assertTrue(giftService.save(testCertificates.get(2)));
        giftService.update(TEST_CERTIFICATES_COUNT+2,testCertificates.get(0));
        assertEquals(testCertificates.get(0),giftService.getById(TEST_CERTIFICATES_COUNT+2));
    }


}