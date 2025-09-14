package io.project.controller;

import io.project.DTO.client.ClientCreateDTO;
import io.project.DTO.client.ClientDTO;
import io.project.DTO.client.ClientUpdateDTO;
import io.project.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Client", description = "The clients API")
@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService service;


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)                 //required - указывает, что параметр является необязательным
    public ResponseEntity<List<ClientDTO>> showAll(@RequestParam(required = false) String firstName,
                                                   @RequestParam(required = false) String lastName,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "5") int limit) {
        var result = service.getClientsWithSpec(firstName, lastName, PageRequest.of(page,limit));
        return ResponseEntity.ok().body(result);
    }

    @Operation(summary = "Found client by ID", tags = {"Client"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Founded client",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ClientDTO.class)
                    )

            )
    })
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
