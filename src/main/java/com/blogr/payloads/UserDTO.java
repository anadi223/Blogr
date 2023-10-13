package com.blogr.payloads;

import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private int id;

    @NotBlank
    @Size(min = 4,message = "Username should be more than 4 characters")
    private String name;

    @Email(message = "Email address is not valid")
    private String email;

    @NotEmpty
    @Size(min = 6, max = 10, message = "Password must be min of 6 and max of 10 characters")
    private String password;
    @NotBlank
    private String about;
}
