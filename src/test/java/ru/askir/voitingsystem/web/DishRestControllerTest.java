package ru.askir.voitingsystem.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.askir.voitingsystem.TestUtil;
import ru.askir.voitingsystem.model.Dish;
import ru.askir.voitingsystem.service.DishService;
import ru.askir.voitingsystem.web.json.JsonUtil;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.askir.voitingsystem.TestUtil.readFromJson;

import static ru.askir.voitingsystem.data.DishTestData.*;
import static ru.askir.voitingsystem.data.RestaurantTestData.RESTAURANT1_ID;
import static ru.askir.voitingsystem.data.MenuTestData.MENU1_1_ID;

public class DishRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = DishRestController.REST_URL + '/';

    @Autowired
    protected DishService dishService;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + DISH1_ID, RESTAURANT1_ID, MENU1_1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                        // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(DISH1_1));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + DISH1_ID, RESTAURANT1_ID, MENU1_1_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(dishService.getAll(MENU1_1_ID), DISH1_2, DISH1_3);
    }

    @Test
    public void testUpdate() throws Exception {
        Dish updated = new Dish(DISH1_1);
        updated.setPrice(new BigDecimal(100));
        mockMvc.perform(put(REST_URL + DISH1_ID, RESTAURANT1_ID, MENU1_1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        assertMatch(dishService.get(DISH1_ID, MENU1_1_ID), updated);
    }

    @Test
    public void testCreate() throws Exception {
        Dish expected = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL, RESTAURANT1_ID, MENU1_1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        Dish returned = readFromJson(action, Dish.class);
        expected.setId(returned.getId());

        assertMatch(returned, expected);
        assertMatch(dishService.getAll(MENU1_1_ID), DISH1_1, DISH1_2, DISH1_3, expected);
    }

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL, RESTAURANT1_ID, MENU1_1_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(DISH1_1, DISH1_2, DISH1_3)));
    }
}
