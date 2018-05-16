package ru.askir.voitingsystem.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.askir.voitingsystem.TestUtil;
import ru.askir.voitingsystem.model.Restaurant;
import ru.askir.voitingsystem.service.RestaurantService;
import ru.askir.voitingsystem.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.askir.voitingsystem.TestUtil.readFromJson;
import static ru.askir.voitingsystem.TestUtil.userHttpBasic;
import static ru.askir.voitingsystem.data.RestaurantTestData.*;
import static ru.askir.voitingsystem.data.UserTestData.ADMIN;
import static ru.askir.voitingsystem.data.UserTestData.USER;

public class RestaurantRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantRestController.REST_URL + '/';

    @Autowired
    protected RestaurantService restaurantService;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT1));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT1_ID)
                .with(userHttpBasic(ADMIN)))
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
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk());

        assertMatch(restaurantService.get(RESTAURANT1_ID), updated);
    }

    @Test
    public void testCreate() throws Exception {
        Restaurant expected = new Restaurant(null, "3New");
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isCreated());

        Restaurant returned = readFromJson(action, Restaurant.class);
        expected.setId(returned.getId());

        assertMatch(returned, expected);
        assertMatch(restaurantService.getAll(), RESTAURANT1, RESTAURANT2, expected);
    }

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT1, RESTAURANT2)));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // проверка на отсутствие доступа
    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void testGetForbidden() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testDeleteForbidden() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT1_ID)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void testUpdateForbidden() throws Exception {
        Restaurant updated = new Restaurant(RESTAURANT1);
        updated.setName("UpdatedName");
        mockMvc.perform(put(REST_URL + RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testCreateForbidden() throws Exception {
        Restaurant expected = new Restaurant(null, "3New");
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected))
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testGetAllForbidden() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden()));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // проверка при отсутствии аутентификации
    // -----------------------------------------------------------------------------------------------------------------
    @Test
    public void testGetUnAuth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }
}
