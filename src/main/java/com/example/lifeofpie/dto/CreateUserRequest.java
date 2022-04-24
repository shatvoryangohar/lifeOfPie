package com.example.lifeofpie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    @NotBlank(message = "Name should be not empty!!")
    private String name;
    @NotBlank(message = "Surname should not be empty!!")
    private String surname;
    @NotEmpty(message = "PhoneNumber should not be empty")
    private String phone;
    @NotBlank(message = "Email should not be empty!!")

    private String email;
    @NotEmpty(message = "Password should not be empty!!")
    @Size(min = 3, max = 25, message = "Password length should be between 3 and 25")
    private String password;

}
