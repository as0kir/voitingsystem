package ru.askir.voitingsystem.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.askir.voitingsystem.model.Dish;
import ru.askir.voitingsystem.util.exception.NotFoundException;

import static ru.askir.voitingsystem.data.DishTestData.*;
import static ru.askir.voitingsystem.data.MenuTestData.MENU1_1_ID;
import static ru.askir.voitingsystem.data.MenuTestData.MENU1_2_ID;

@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ContextConfiguration({"classpath:spring/spring-context.xml"})
public class DishServiceTest extends AbstractServiceTest {

    @Autowired
    private DishService service;

    @Test
    public void delete() throws Exception {
        service.delete(DISH1_ID, MENU1_1_ID);
        assertMatch(service.getAll(MENU1_1_ID), DISH1_2, DISH1_3);
    }

    @Test
    public void deleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(DISH1_ID, 1);
    }

    @Test
    public void create() throws Exception {
        Dish created = getCreated();
        service.create(created, MENU1_1_ID);
        assertMatch(service.getAll(MENU1_1_ID), DISH1_1, DISH1_2, DISH1_3, created);
    }

    @Test
    public void get() throws Exception {
        Dish actual = service.get(DISH1_ID, MENU1_1_ID);
        assertMatch(actual, DISH1_1);
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(DISH1_ID, MENU1_2_ID);
    }

    @Test
    public void update() throws Exception {
        Dish updated = getUpdated();
        service.update(updated, MENU1_1_ID);
        assertMatch(service.get(DISH1_ID, MENU1_1_ID), updated);
    }

    @Test
    public void updateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + DISH1_ID);
        service.update(DISH1_1, MENU1_2_ID);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(MENU1_1_ID), DISHES);
    }
}
