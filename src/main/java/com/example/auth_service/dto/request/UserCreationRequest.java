package com.example.auth_service.dto.request;

import com.example.auth_service.validator.DobConstraint;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 3, message = "USERNAME_INVALID")
    String username;
    @Size(min = 8, message = "INVALID_PASSWORD")
    String password;
    @Size(min = 1, message = "FIRSTNAME_IS_NULL")
    String firstName;
    @Size(min = 1, message = "LASTNAME_IS_NULL")
    String lastName;
    @DobConstraint(min = 18, message = "INVALID_DOB")
    LocalDate dob;
    Set<String> roles;
}
