package io.project.controller;

import io.project.DTO.order.OrderCreateDTO;
import io.project.DTO.order.OrderDTO;
import io.project.DTO.order.OrderUpdateDTO;
import io.project.model.Product;
import io.project.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> showAll(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) List<Long> productIds,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int limit
    ) {
        return ResponseEntity.ok()
                .body(service.showAll(status,productIds, PageRequest.of(page, limit)));
    }

    @GetMapping("/{id}")
    public OrderDTO show(@PathVariable("id") Long id) {
        return service.show(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO create(@RequestBody @Valid OrderCreateDTO createDTO) {
        return service.create(createDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable("id") Long id) {
        service.destroy(id);
    }

    @PutMapping("/{id}")
    public OrderDTO update(@PathVariable("id") Long id,
                           @Valid @RequestBody OrderUpdateDTO updateDTO) {
        return service.update(updateDTO, id);
    }
}
