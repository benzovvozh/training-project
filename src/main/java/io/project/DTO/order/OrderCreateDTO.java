package io.project.DTO.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.project.model.Client;
import io.project.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
public class OrderCreateDTO {
    private Long clientId;
    private OrderStatus status;
    private List<Long> productsId;
}
