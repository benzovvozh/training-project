package io.project.dto.subscriptionDTO;

import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Getter
@Setter
public class SubscriptionUpdateDTO {
    JsonNullable<Long> productId;
}
