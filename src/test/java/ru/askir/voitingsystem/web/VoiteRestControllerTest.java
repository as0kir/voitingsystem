package ru.askir.voitingsystem.web;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import ru.askir.voitingsystem.service.MenuService;
import ru.askir.voitingsystem.util.DateTimeUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.askir.voitingsystem.TestUtil.contentJson;
import static ru.askir.voitingsystem.TestUtil.userHttpBasic;
import static ru.askir.voitingsystem.data.MenuTestData.MENU1_1;
import static ru.askir.voitingsystem.data.MenuTestData.MENU1_1_ID;
import static ru.askir.voitingsystem.data.UserTestData.USER;
import static ru.askir.voitingsystem.data.VoiteTestData.rightDate;

public class VoiteRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = VoiteRestController.REST_URL + '/';

    @Autowired
    private DateTimeUtil dateTimeUtil;

    @Autowired
    MenuService menuService;

    @Test
    public void testGetAll() throws Exception {
        dateTimeUtil.setMockDate(rightDate);

        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(menuService.getAllWithVoites(rightDate.toLocalDate())));
    }

    @Test
    public void testGetAllByDate() throws Exception {
        dateTimeUtil.setMockDate(MENU1_1.getDateSet().atStartOfDay());
        mockMvc.perform(get(REST_URL + "{dateSet}", MENU1_1.getDateSet())
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(menuService.getAllWithVoites(MENU1_1.getDateSet())));
    }

    @Test
    public void testSetVoite() throws Exception {

        LocalTime beginTime = dateTimeUtil.getBeginTime();
        LocalTime endTime = dateTimeUtil.getEndTime();

        LocalDateTime testDate = MENU1_1.getDateSet().atStartOfDay();

        dateTimeUtil.setMockDate(testDate);
        dateTimeUtil.setBeginTime(testDate.toLocalTime());
        dateTimeUtil.setEndTime(testDate.toLocalTime());

        mockMvc.perform(post(REST_URL + "{idMenu}", MENU1_1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isNoContent())
                .andDo(print());

        dateTimeUtil.setBeginTime(beginTime);
        dateTimeUtil.setBeginTime(endTime);
    }

    @Test
    public void testSetVoiteNotAllow() throws Exception {
        dateTimeUtil.setMockDate(MENU1_1.getDateSet().atTime(dateTimeUtil.getEndTime().plusHours(1)));

        mockMvc.perform(post(REST_URL + "{idMenu}", MENU1_1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void testSetVoiteUnAuth() throws Exception {
        dateTimeUtil.setMockDate(MENU1_1.getDateSet().atStartOfDay());

        mockMvc.perform(post(REST_URL + "{idMenu}", MENU1_1_ID))
                .andExpect(status().isUnauthorized());
    }

}
