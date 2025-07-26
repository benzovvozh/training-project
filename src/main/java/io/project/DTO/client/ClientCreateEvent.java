package io.project.DTO.client;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientCreateEvent {
    private String firstName;
    private String lastName;
    private String email;
    private String telephoneNumber;
}
