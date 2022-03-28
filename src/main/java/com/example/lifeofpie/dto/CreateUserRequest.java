package com.example.lifeofpie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    @NotBlank(message = "Name should be not empty!!")
    private String name;
    @NotBlank(message = "Surname should be not empty!!")
    private String surname;
    @Pattern(regexp = "^(\\\\+\\\\d{1,3}( )?)?((\\\\(\\\\d{1,3}\\\\))|\\\\d{1,3})[- .]?\\\\d{3,4}[- .]?\\\\d{4}$")
    @NotEmpty(message = "PhoneNumber should be not empty")
    private String phone;
    @NotBlank(message = "Email should be not empty!!")
    @Email(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$")
    private String email;
    @NotEmpty(message = "Password should be not empty!!")
    @Size(min = 3, max = 25, message = "Password length should be between 3 and 25")
    private String password;


}
