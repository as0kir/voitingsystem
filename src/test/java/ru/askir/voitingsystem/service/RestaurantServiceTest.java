package ru.askir.voitingsystem.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.askir.voitingsystem.model.Restaurant;
import ru.askir.voitingsystem.util.exception.NotFoundException;

import java.util.List;

import static ru.askir.voitingsystem.data.RestaurantTestData.*;

@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
//@ContextConfiguration({"classpath:spring/spring-context.xml"})
@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void create() throws Exception {
        Restaurant newRestaurant = new Restaurant("3Новый");
        Restaurant created = service.create(newRestaurant);
        newRestaurant.setId(created.getId());
        assertMatch(service.getAll(), RESTAURANT1, RESTAURANT2, newRestaurant);
    }

    @Test
    public void delete() throws Exception {
        service.delete(RESTAURANT1_ID);
        assertMatch(service.getAll(), RESTAURANT2);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void get() throws Exception {
        Restaurant restaurant = service.get(RESTAURANT1_ID);
        assertMatch(restaurant, RESTAURANT1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void update() throws Exception {
        Restaurant updated = new Restaurant(RESTAURANT1);
        updated.setName("UpdatedName");
        service.update(updated);
        assertMatch(service.get(RESTAURANT1_ID), updated);
    }

    @Test
    public void getAll() throws Exception {
        List<Restaurant> all = service.getAll();
        assertMatch(all, RESTAURANT1, RESTAURANT2);
    }
}
