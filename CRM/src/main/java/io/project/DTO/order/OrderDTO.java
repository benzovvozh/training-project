package io.project.DTO.order;

import io.project.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private Long id;
    private LocalDate createdAt;
    private OrderStatus status;
    private Long clientId;
    private List<Long> productsId;
}
