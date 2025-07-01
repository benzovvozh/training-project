package io.project.DTO;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientCreateDTO {
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String telephoneNumber;

}
