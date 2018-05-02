package ru.askir.voitingsystem.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.askir.voitingsystem.TestUtil;
import ru.askir.voitingsystem.model.Restaurant;
import ru.askir.voitingsystem.model.Role;
import ru.askir.voitingsystem.model.User;
import ru.askir.voitingsystem.service.RestaurantService;
import ru.askir.voitingsystem.service.UserService;
import ru.askir.voitingsystem.web.json.JsonUtil;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.askir.voitingsystem.TestUtil.readFromJson;
import static ru.askir.voitingsystem.data.RestaurantTestData.*;

public class RestaurantRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantRestController.REST_URL + '/';

    @Autowired
    protected RestaurantService restaurantService;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT1));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT1_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(restaurantService.getAll(), RESTAURANT2);
    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant updated = new Restaurant(RESTAURANT1);
        updated.setName("UpdatedName");
        mockMvc.perform(put(REST_URL + RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        assertMatch(restaurantService.get(RESTAURANT1_ID), updated);
    }

    @Test
    public void testCreate() throws Exception {
        Restaurant expected = new Restaurant(null, "3New");
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        Restaurant returned = readFromJson(action, Restaurant.class);
        expected.setId(returned.getId());

        assertMatch(returned, expected);
        assertMatch(restaurantService.getAll(), RESTAURANT1, RESTAURANT2, expected);
    }

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT1, RESTAURANT2)));
    }
}
