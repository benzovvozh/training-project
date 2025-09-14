package io.project.service;

import io.project.DTO.product.ProductCreateDTO;
import io.project.DTO.product.ProductCreatedEvent;
import io.project.DTO.product.ProductDTO;
import io.project.DTO.product.ProductUpdateDTO;
import io.project.customException.NotFoundException;
import io.project.mapper.ProductMapper;
import io.project.model.Product;
import io.project.repository.ProductRepository;
import io.project.specification.ProductSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@Slf4j
public class ProductService {
//    @Autowired
//    private KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;


    @Autowired
    private ProductRepository repository;
    @Autowired
    private ProductMapper mapper;
    @Autowired
    private ProductSpecification productSpecification;

    public ProductDTO show(Long id) {
        var product = repository.findById(id).orElseThrow(() -> new NotFoundException("Product not found in CRM"));
        return mapper.map(product);
    }

    public List<ProductDTO> showAll(Integer price, String title, String description, Pageable paging) {
        var spec = productSpecification.build(price, title, description);
        return repository.findAll(spec, paging).stream()
                .map(mapper::map)
                .toList();
    }

    public ProductDTO create(ProductCreateDTO createDTO) {
        var product = mapper.map(createDTO);
        repository.save(product);
//        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(
//                product.getId(),
//                product.getTitle(),
//                product.getDescription(),
//                product.getPrice());
//        kafkaTemplate.send("product-created-event-topic", String.valueOf(product.getId()), productCreatedEvent);
        return mapper.map(product);
    }

    public void destroy(Long id) {
        repository.deleteById(id);
    }

    public ProductDTO update(ProductUpdateDTO updateDTO, Long id) {
        var product = repository.findById(id).orElseThrow();
        mapper.update(updateDTO, product);
        repository.save(product);
        return mapper.map(product);
    }
}
