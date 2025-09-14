package io.project.DTO.product;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductCreateDTO {
    private String title;
    private String description;
    private Integer price;
    private List<Long> ordersId;
}
