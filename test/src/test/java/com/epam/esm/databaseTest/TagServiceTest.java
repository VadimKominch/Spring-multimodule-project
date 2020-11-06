package com.epam.esm.databaseTest;


import com.epam.esm.MyWebApplicationInitializer;
import com.epam.esm.SpringConfig;
import com.epam.esm.converter.DateConverter;
import com.epam.esm.database.DatabaseDataSource;
import com.epam.esm.entity.GiftSertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.TagService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = DatabaseDataSource.class)
class TagServiceTest {

    private static TagService tagService;
    private static final List<Tag> testTags = new ArrayList<>();
    private static final int TEST_CERTIFICATES_COUNT = 10;

    @BeforeAll
    static void setup() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.epam.esm");
        context.getEnvironment().addActiveProfile("test");
        context.refresh();
        tagService = context.getBean(TagService.class);
        for(int i = 0;i< TEST_CERTIFICATES_COUNT;i++) {
            testTags.add(new Tag("Name",+1));
        }
        testTags.forEach(el->tagService.save(el));
    }

    @Test
    void checkForGetAllMethod() {
        assertEquals(tagService.getAll().size(), TEST_CERTIFICATES_COUNT);
    }

    @Test
    void checkForGettingById() {
        int checkedId = 3;
        assertEquals(testTags.get(checkedId-1),tagService.getById(checkedId));
    }

    @Test
    void checkForGetByIdForNonExistingEntity() {
        assertNull(tagService.getById(2000));
    }

    @Test
    void checkIfValidDataWillBeSaved() {
        tagService.save(testTags.get(2));
        assertEquals(TEST_CERTIFICATES_COUNT+1,tagService.getAll().size());
        tagService.delete(TEST_CERTIFICATES_COUNT+1);
    }
}
