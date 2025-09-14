package io.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductSubDTO {
    private Long id;
    private String title;
    private String description;
    private Integer price;
    private List<Long> ordersId;
}
