package io.project.DTO.order;

import io.project.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.List;

@Getter
@Setter
public class OrderUpdateDTO {
    JsonNullable<OrderStatus> status;
    JsonNullable<List<Long>> productsId;
}
