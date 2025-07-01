package io.project.DTO;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Getter
@Setter
public class ClientUpdateDTO {
    JsonNullable<String> firstName;
    JsonNullable<String> lastName;
    @Email
    JsonNullable<String> email;
    JsonNullable<String> telephoneNumber;
}
