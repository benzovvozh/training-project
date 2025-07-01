package io.project.mapper;

import io.project.DTO.emp.EmployeeCreateDTO;
import io.project.DTO.emp.EmployeeUpdateDTO;
import io.project.DTO.emp.EmployeeDTO;
import io.project.model.Employee;
import org.mapstruct.*;

@Mapper(
        uses = {JsonNullableMapper.class, ReferenceMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class EmployeeMapper {

    public abstract Employee map(EmployeeCreateDTO employeeCreateDTO);
    public abstract EmployeeDTO map(Employee employee);

    public abstract void update(EmployeeUpdateDTO employeeUpdateDTO, @MappingTarget Employee model);
}
