package ru.askir.voitingsystem.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.askir.voitingsystem.model.User;
import ru.askir.voitingsystem.service.UserService;

import java.net.URI;
import java.util.List;

import static ru.askir.voitingsystem.util.ValidationUtil.assureIdConsistent;
import static ru.askir.voitingsystem.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestController {
    static final String REST_URL = "/rest/admin/users";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    @GetMapping()
    public List<User> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public User get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createWithLocation(@RequestBody User user) {
        checkNew(user);
        User created = service.create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user, @PathVariable("id") int id) {
        assureIdConsistent(user, id);
        service.update(user);
    }
}
