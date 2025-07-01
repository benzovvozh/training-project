package io.project.controller;

import io.project.DTO.client.ClientCreateDTO;
import io.project.DTO.client.ClientDTO;
import io.project.DTO.client.ClientUpdateDTO;
import io.project.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService service;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ClientDTO>> showAll() {
        var list = service.getAll();
        return ResponseEntity.ok()
                .body(list);
    }

    @GetMapping("/{id}")
    public ClientDTO show(@PathVariable("id") Long id) {
        var client = service.show(id);
        return client;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO create(@RequestBody @Valid ClientCreateDTO createDTO) {
        var client = service.create(createDTO);
        return client;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable("id") Long id) {
        service.remove(id);
    }

    @PutMapping("/{id}")
    public ClientDTO update(@PathVariable("id") Long id, @Valid @RequestBody ClientUpdateDTO updateDTO) {
        var client = service.update(id, updateDTO);
        return client;
    }
}
