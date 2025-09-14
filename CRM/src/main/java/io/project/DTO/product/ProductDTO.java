package io.project.DTO.product;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String title;
    private String description;
    private Integer price;
    private List<Long> ordersId;
}
