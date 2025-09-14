package io.project.mapper;

import io.project.dto.subscriptionDTO.SubscriptionCreateDTO;
import io.project.dto.subscriptionDTO.SubscriptionDTO;
import io.project.dto.subscriptionDTO.SubscriptionUpdateDTO;
import io.project.model.Subscription;
import org.mapstruct.*;


@Mapper(
        uses = {JsonNullableMapper.class, ReferenceMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class SubscriptionMapper {
    public abstract Subscription map(SubscriptionCreateDTO subscriptionCreateDTO);

    public abstract SubscriptionDTO map(Subscription subscription);

    public abstract void update(SubscriptionUpdateDTO subscriptionUpdateDTO,
                                @MappingTarget Subscription subscription);
}
