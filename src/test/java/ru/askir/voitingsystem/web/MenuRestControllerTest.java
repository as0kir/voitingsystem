package ru.askir.voitingsystem.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.askir.voitingsystem.TestUtil;
import ru.askir.voitingsystem.model.Menu;
import ru.askir.voitingsystem.service.MenuService;
import ru.askir.voitingsystem.web.json.JsonUtil;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.askir.voitingsystem.TestUtil.readFromJson;
import static ru.askir.voitingsystem.data.MenuTestData.*;
import static ru.askir.voitingsystem.data.RestaurantTestData.RESTAURANT1_ID;

public class MenuRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = MenuRestController.REST_URL + '/';

    @Autowired
    protected MenuService menuService;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + MENU1_1_ID, RESTAURANT1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MENU1_1));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + MENU1_1_ID, RESTAURANT1_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(menuService.getAll(RESTAURANT1_ID), MENU1_2);
    }

    @Test
    public void testUpdate() throws Exception {
        Menu updated = new Menu(MENU1_1);
        updated.setDateSet(LocalDate.of(2018, 04, 20));
        mockMvc.perform(put(REST_URL + MENU1_1_ID, RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        assertMatch(menuService.get(MENU1_1_ID, RESTAURANT1_ID), updated);
    }

    @Test
    public void testCreate() throws Exception {
        Menu expected = new Menu(null, LocalDate.of(2018, 04, 20));
        ResultActions action = mockMvc.perform(post(REST_URL, RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        Menu returned = readFromJson(action, Menu.class);
        expected.setId(returned.getId());

        assertMatch(returned, expected);
        assertMatch(menuService.getAll(RESTAURANT1_ID), expected, MENU1_2, MENU1_1);
    }

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL, RESTAURANT1_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MENU1_1, MENU1_2)));
    }
}
