package com.goit.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserDto {

    @NotBlank
    @Email
    public String email;

    @NotBlank
    @Size(min = 6)
    public String password;
}
