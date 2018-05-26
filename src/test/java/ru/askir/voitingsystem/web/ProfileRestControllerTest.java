package ru.askir.voitingsystem.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.askir.voitingsystem.TestUtil;
import ru.askir.voitingsystem.model.User;
import ru.askir.voitingsystem.service.UserService;
import ru.askir.voitingsystem.to.UserTo;
import ru.askir.voitingsystem.util.UserUtil;
import ru.askir.voitingsystem.util.exception.ErrorType;
import ru.askir.voitingsystem.web.handler.ExceptionInfoHandler;
import ru.askir.voitingsystem.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.askir.voitingsystem.TestUtil.userHttpBasic;
import static ru.askir.voitingsystem.data.UserTestData.*;
import static ru.askir.voitingsystem.web.ProfileRestController.REST_URL;
import static ru.askir.voitingsystem.web.handler.ExceptionInfoHandler.EXCEPTION_DUPLICATE_EMAIL;

public class ProfileRestControllerTest extends AbstractControllerTest {

    @Autowired
    protected UserService userService;

    @Test
    public void testGet() throws Exception {
        TestUtil.print(
                mockMvc.perform(get(REST_URL)
                        .with(userHttpBasic(USER)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andExpect(contentJson(USER))
        );
    }

    @Test
    public void testGetUnAuth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isNoContent());
        assertMatch(userService.getAll(), ADMIN);
    }

    @Test
    public void testUpdate() throws Exception {
        UserTo updatedTo = new UserTo(null, "newName", "newemail@ya.ru", "newPassword");

        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER))
                .content(JsonUtil.writeValue(updatedTo)))
                .andDo(print())
                .andExpect(status().isOk());

        assertMatch(userService.getByEmail("newemail@ya.ru"), UserUtil.updateFromTo(new User(USER), updatedTo));
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void testDublicateEmail() throws Exception {
        UserTo updatedTo = new UserTo(null, "admin", "admin@gmail.com", "password");

        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER))
                .content(JsonUtil.writeValue(updatedTo)))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andDo(print())
                .andExpect(jsonMessage("$.detail", EXCEPTION_DUPLICATE_EMAIL))
                .andDo(print());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void testValidatedUpdate() throws Exception {
        UserTo updatedTo = new UserTo(null, "N", "newemail@ya.ru", "newPassword");

        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER))
                .content(JsonUtil.writeValue(updatedTo)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print())
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andDo(print());
    }
}
