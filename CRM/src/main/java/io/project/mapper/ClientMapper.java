package io.project.mapper;

import io.project.DTO.client.ClientCreateDTO;
import io.project.DTO.client.ClientDTO;
import io.project.DTO.client.ClientUpdateDTO;
import io.project.model.Client;
import org.mapstruct.*;

@Mapper(
        uses = {JsonNullableMapper.class, ReferenceMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class ClientMapper {
    public abstract Client map(ClientCreateDTO clientCreateDTO);
    public abstract ClientDTO map(Client client);
    public abstract void update(ClientUpdateDTO updateDTO, @MappingTarget Client client);
}
