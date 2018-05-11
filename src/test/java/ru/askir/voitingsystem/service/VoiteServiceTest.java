package ru.askir.voitingsystem.service;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.askir.voitingsystem.model.Voite;
import ru.askir.voitingsystem.model.Voite;
import ru.askir.voitingsystem.util.DateUtil;
import ru.askir.voitingsystem.util.NotFoundException;

import static ru.askir.voitingsystem.data.MenuTestData.*;
import static ru.askir.voitingsystem.data.VoiteTestData.*;
import static ru.askir.voitingsystem.data.VoiteTestData.VOITES;
import static ru.askir.voitingsystem.data.UserTestData.ADMIN_ID;
import static ru.askir.voitingsystem.data.UserTestData.USER_ID;

@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ContextConfiguration({"classpath:spring/spring-context.xml"})
public class VoiteServiceTest extends AbstractServiceTest{

    @Autowired
    private VoiteService service;

    @BeforeClass
    public static void before(){
        DateUtil.setMockDate(null);
    }

    @Test
    public void setVoite() throws Exception {
        DateUtil.setMockDate(rightDate);
        service.setVoite(USER_ID, MENU2_1_ID);
        assertMatch(service.get(USER_ID, rightDate.toLocalDate()), getVoited());
    }

    /*@Test
    public void delete() throws Exception {
        service.delete(VOITE1_ID, USER_ID, MENU1_1_ID);
        assertMatch(service.getAll(USER_ID, MENU1_1_ID));
    }

    @Test
    public void deleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(VOITE1_ID, 1, 1);
    }

    @Test
    public void create() throws Exception {
        Voite created = getCreated();
        service.create(created, ADMIN_ID, MENU2_2_ID);
        assertMatch(service.getAll(ADMIN_ID, MENU2_2_ID), created);
    }

    @Test
    public void get() throws Exception {
        Voite actual = service.get(VOITE1_ID, USER_ID, MENU1_1_ID);
        assertMatch(actual, VOITE1_1);
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(VOITE1_ID, USER_ID, MENU1_2_ID);
    }

    @Test
    public void update() throws Exception {
        Voite updated = getUpdated();
        service.update(updated, USER_ID, MENU1_1_ID);
        assertMatch(service.get(VOITE1_ID, USER_ID, MENU1_1_ID), updated);
    }

    @Test
    public void updateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + VOITE1_ID);
        service.update(VOITE1_1, USER_ID, MENU1_2_ID);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(USER_ID, MENU1_1_ID), VOITES);
    }*/

}
