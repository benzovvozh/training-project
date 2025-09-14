package io.project.dto.subscriptionDTO;

import io.project.model.EventType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionCreateDTO {
    private Long clientId;
    private Long productId;
    private EventType eventType;
}
