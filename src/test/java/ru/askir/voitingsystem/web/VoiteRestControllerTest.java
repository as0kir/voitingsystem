package ru.askir.voitingsystem.web;

import org.junit.Test;
import org.springframework.http.MediaType;
import ru.askir.voitingsystem.TestUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.askir.voitingsystem.TestUtil.userHttpBasic;
import static ru.askir.voitingsystem.data.DishTestData.*;
import static ru.askir.voitingsystem.data.MenuTestData.MENU1_1;
import static ru.askir.voitingsystem.data.RestaurantTestData.RESTAURANT1_ID;
import static ru.askir.voitingsystem.data.UserTestData.ADMIN;
import static ru.askir.voitingsystem.data.UserTestData.USER;

public class VoiteRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = VoiteRestController.REST_URL + '/';

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
                //.andExpect(content().json({"sdfsdf", "sdfsdf"}));
    }

    @Test
    public void testGetAllByDate() throws Exception {
        mockMvc.perform(get(REST_URL + "{dateSet}", MENU1_1.getDateSet())
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        //.andExpect(content().json({"sdfsdf", "sdfsdf"}));
    }

}
