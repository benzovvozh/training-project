package io.project.controller;

import io.project.customException.ClientNotFoundException;
import io.project.dto.subscriptionDTO.SubscriptionCreateDTO;
import io.project.dto.subscriptionDTO.SubscriptionDTO;
import io.project.dto.subscriptionDTO.SubscriptionUpdateDTO;
import io.project.service.SubscriptionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService service;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.service = subscriptionService;
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionDTO>> showAll() {
        return ResponseEntity.ok()
                .body(service.showAll());
    }

    @GetMapping("/{id}")
    public SubscriptionDTO show(@PathVariable("id") Long id) {
        return service.show(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubscriptionDTO create(@RequestBody @Valid SubscriptionCreateDTO createDTO) {
        return service.create(createDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        service.destroy(id);
    }

    @PutMapping("/{id}")
    public SubscriptionDTO upate(@PathVariable("id") Long id,
                                 @RequestBody @Valid SubscriptionUpdateDTO updateDTO) {
        return service.update(id, updateDTO);
    }
}
