package ru.askir.voitingsystem.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.askir.voitingsystem.model.Voite;
import ru.askir.voitingsystem.service.VoiteService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = VoiteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoiteRestController {

    //static final String REST_URL = MenuRestController.REST_URL + "/{menu_id}/voites";
    static final String REST_URL = "/rest/voites";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoiteService service;

    @GetMapping()
    public List<Voite> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{dateSet}")
    public List<Voite> getAll(@PathVariable("dateSet") LocalDate dateSet) {
        return service.getAll(dateSet);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void setVoite(@RequestParam int idMenu) {
        int idUser = AuthorizedUser.id();
        service.setVoite(idUser, idMenu);
    }


}
