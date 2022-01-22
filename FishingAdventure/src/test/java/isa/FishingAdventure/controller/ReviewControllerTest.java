package isa.FishingAdventure.controller;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReviewControllerTest {
    private static final String URL_PREFIX = "/review";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Transactional
    @Rollback(true)
    @WithMockUser(authorities = "ROLE_ADMIN")
    public void testGetReviewsForAdmin() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/getReviewsForAdmin")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(101)))
                .andExpect(jsonPath("$.[*].content").value(hasItem("It was great!")))
                .andExpect(jsonPath("$.[*].rating").value(hasItem(5)));
    }

    @Test
    @Transactional
    public void testGetReviewsForService() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/getReviewsForService/101")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(103)))
                .andExpect(jsonPath("$.[*].content").value(hasItem("It was great!")))
                .andExpect(jsonPath("$.[*].rating").value(hasItem(4)));
    }
}
