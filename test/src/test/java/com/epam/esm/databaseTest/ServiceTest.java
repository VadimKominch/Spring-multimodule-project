package com.epam.esm.databaseTest;


import com.epam.esm.database.DatabaseDataSource;
import com.epam.esm.service.GiftService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration(classes = DatabaseDataSource.class)
class ServiceTest {

    private static GiftService giftService;

    @BeforeAll
    static void setup() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.epam.esm");
        context.getEnvironment().addActiveProfile("test");
        context.refresh();
        giftService = context.getBean(GiftService.class);
    }
    //Add embedded
    @Test
    void checkForGetAllMethod() {
        assertTrue(giftService.getAll().size() == 1);
}
}