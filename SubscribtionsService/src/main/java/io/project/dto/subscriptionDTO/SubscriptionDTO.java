package io.project.dto.subscriptionDTO;

import io.project.model.EventType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SubscriptionDTO {
    private Long id;
    private Long clientId;
    private Long productId;
    private EventType eventType;
    private LocalDate createdAt;

}
