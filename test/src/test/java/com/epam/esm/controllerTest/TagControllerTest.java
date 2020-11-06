package com.epam.esm.controllerTest;

import com.epam.esm.controller.TagController;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.TagService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
public class TagControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private TagController tagController;

    @Mock
    private TagService service;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(tagController).build();
        Tag tag1 = new Tag("First",1);
        Tag tag2 = new Tag("Second",2);
        when(service.getAll()).thenReturn(Arrays.asList(tag1, tag2));
    }

    @Test
    public void checkForGetAllMappingForTagController() throws Exception {
        mockMvc.perform(get("/tag/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void checkForUnknownMapping() throws Exception {
        mockMvc.perform(get("/all"))
                .andExpect(status().is4xxClientError());
    }
}
