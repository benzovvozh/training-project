package io.project.controller;

import io.project.DTO.product.ProductCreateDTO;
import io.project.DTO.product.ProductDTO;
import io.project.DTO.product.ProductUpdateDTO;
import io.project.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductDTO>> showAll() {
        return ResponseEntity.ok()
                .body(service.getAll());
    }

    @GetMapping("/{id}")
    public ProductDTO show(@PathVariable("id") Long id) {
        return service.show(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@Valid @RequestBody ProductCreateDTO createDTO) {
        return service.create(createDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable("id") Long id) {
        service.destroy(id);
    }

    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable("id") Long id,
                             @Valid @RequestBody ProductUpdateDTO updateDTO) {
        return service.update(updateDTO,id);
    }


}
