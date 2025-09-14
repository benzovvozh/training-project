package io.project.DTO.emp;

import io.project.model.EmployeeRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Getter
@Setter
public class EmployeeUpdateDTO {
    private JsonNullable<String> firstName;
    private JsonNullable<String> lastName;
    @Size(min = 6)
    private JsonNullable<String> password;
    @Email
    private JsonNullable<String> email;
    private JsonNullable<EmployeeRole> role;
}
