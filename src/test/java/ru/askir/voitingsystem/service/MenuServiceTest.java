package ru.askir.voitingsystem.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.askir.voitingsystem.model.Menu;
import ru.askir.voitingsystem.util.exception.NotFoundException;

import static ru.askir.voitingsystem.data.MenuTestData.*;
import static ru.askir.voitingsystem.data.RestaurantTestData.*;

@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
//@ContextConfiguration({"classpath:spring/spring-context.xml"})
@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
public class MenuServiceTest extends AbstractServiceTest {

    @Autowired
    private MenuService service;

    @Test
    public void delete() throws Exception {
        service.delete(MENU1_1_ID, RESTAURANT1_ID);
        assertMatch(service.getAll(RESTAURANT1_ID), MENU1_2);
    }

    @Test
    public void deleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(MENU1_1_ID, 1);
    }

    @Test
    public void create() throws Exception {
        Menu created = getCreated();
        service.create(created, RESTAURANT1_ID);
        assertMatch(service.getAll(RESTAURANT1_ID), created, MENU1_2, MENU1_1);
    }

    @Test
    public void get() throws Exception {
        Menu actual = service.get(MENU1_1_ID, RESTAURANT1_ID);
        assertMatch(actual, MENU1_1);
    }

    @Test
    public void getByDateSet() throws Exception {
        Menu actual = service.getByDateSet(MENU1_1.getDateSet(), RESTAURANT1_ID);
        assertMatch(actual, MENU1_1);
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(MENU1_1_ID, RESTAURANT2_ID);
    }

    @Test
    public void getByDateSetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        Menu actual = service.getByDateSet(MENU1_1.getDateSet().plusYears(1), RESTAURANT1_ID);
    }

    @Test
    public void update() throws Exception {
        Menu updated = getUpdated();
        service.update(updated, RESTAURANT1_ID);
        assertMatch(service.get(MENU1_1_ID, RESTAURANT1_ID), updated);
    }

    @Test
    public void updateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + MENU1_1_ID);
        service.update(MENU1_1, RESTAURANT2_ID);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(RESTAURANT1_ID), MENUS);
    }
}
