package ru.askir.voitingsystem.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.askir.voitingsystem.model.Menu;
import ru.askir.voitingsystem.service.MenuService;

import java.net.URI;
import java.util.List;

import static ru.askir.voitingsystem.util.ValidationUtil.assureIdConsistent;
import static ru.askir.voitingsystem.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = MenuRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuRestController {
    static final String REST_URL = RestaurantRestController.REST_URL + "/{restaurant_id}/menu";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuService service;

    @GetMapping()
    public List<Menu> getAll(@PathVariable("restaurant_id") int restaurant_id) {
        return service.getAll(restaurant_id);
    }

    @GetMapping(value = "/{id}")
    public Menu get(@PathVariable("restaurant_id") int restaurant_id, @PathVariable("id") int id) {
        return service.get(id, restaurant_id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createWithLocation(@RequestBody Menu menu, @PathVariable("restaurant_id") int restaurant_id) {
        checkNew(menu);
        Menu created = service.create(menu, restaurant_id);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurant_id, created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("restaurant_id") int restaurant_id, @PathVariable("id") int id) {
        service.delete(id, restaurant_id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Menu menu, @PathVariable("restaurant_id") int restaurant_id, @PathVariable("id") int id) {
        assureIdConsistent(menu, id);
        service.update(menu, restaurant_id);
    }
}
