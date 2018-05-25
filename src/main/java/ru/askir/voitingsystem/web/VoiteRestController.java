package ru.askir.voitingsystem.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.askir.voitingsystem.service.MenuService;
import ru.askir.voitingsystem.service.VoiteService;
import ru.askir.voitingsystem.to.MenuTo;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = VoiteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoiteRestController {

    static final String REST_URL = "/rest/voites";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoiteService voiteService;

    @Autowired
    private MenuService menuService;

    @GetMapping
    public List<MenuTo> getAll() {
        return menuService.getAllWithVoites();
    }

    @GetMapping(value = "/{dateSet}")
    public List<MenuTo> getAllForDateSet(@PathVariable("dateSet") LocalDate dateSet) {
        return menuService.getAllWithVoites(dateSet);
    }

    @PostMapping(value = "/{idMenu}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void setVoite(@PathVariable("idMenu") int idMenu) {
        int idUser = AuthorizedUser.id();
        voiteService.setVoite(idUser, idMenu);
    }
}
