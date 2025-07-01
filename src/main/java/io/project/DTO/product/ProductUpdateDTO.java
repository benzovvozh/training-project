package io.project.DTO.product;

import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.List;

@Getter
@Setter
public class ProductUpdateDTO {
    JsonNullable<String> title;
    JsonNullable<String> description;
    JsonNullable<Integer> price;
    JsonNullable<List<Long>> ordersId;
}
