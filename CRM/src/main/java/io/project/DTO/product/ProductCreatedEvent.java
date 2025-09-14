package io.project.DTO.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductCreatedEvent {
    private Long id;
    private String title;
    private String description;
    private Integer price;
}
