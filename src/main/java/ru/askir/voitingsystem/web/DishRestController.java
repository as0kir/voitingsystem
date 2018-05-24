package ru.askir.voitingsystem.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.askir.voitingsystem.model.Dish;
import ru.askir.voitingsystem.service.DishService;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.askir.voitingsystem.util.ValidationUtil.assureIdConsistent;
import static ru.askir.voitingsystem.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = DishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {
    static final String REST_URL = RestaurantRestController.REST_URL + "/{restaurant_id}/menu/{date_set}/dishes";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishService service;

    @GetMapping()
    public List<Dish> getAll(@PathVariable("restaurant_id") int restaurantId, @PathVariable("date_set") LocalDate dateSet) {
        return service.getAll(restaurantId, dateSet);
    }

    @GetMapping(value = "/{id}")
    public Dish get(@PathVariable("restaurant_id") int restaurantId, @PathVariable("date_set") LocalDate dateSet, @PathVariable("id") int id) {
        return service.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@RequestBody @Valid Dish dish, @PathVariable("restaurant_id") int restaurant_id, @PathVariable("date_set") LocalDate dateSet) {
        checkNew(dish);
        Dish created = service.create(dish, restaurant_id, dateSet);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurant_id, dateSet, created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody @Valid Dish dish, @PathVariable("restaurant_id") int restaurant_id, @PathVariable("date_set") LocalDate dateSet, @PathVariable("id") int id) {
        assureIdConsistent(dish, id);
        service.update(dish, restaurant_id, dateSet);
    }    
}
