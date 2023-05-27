package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserClientRequestDto {
    @Column(nullable = false, length = 125)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Provide a Name, please.Can't be null. (numbers not allow)")
    private String name;
    @Column(nullable = false, length = 125)
    private String surname;
    @Email
    @Column(nullable = false, length = 125)
    private String mail;
    @Column(nullable = false, length = 13)
    @Pattern(regexp = "^\\+?[0-9]{12}$", message = "number must be numeric, content 12 numbers and "+" optional")
    private String phone;
    @Column(unique = true, nullable = false, length = 20)
    @Pattern(regexp = "^[0-9]+$", message = "must be numeric")
    private String dniNumber;
    @Column(nullable = false)
    private String password;
    @Past(message = "Birth date must be in the past")
    @Column(nullable = true)
    private LocalDate birthdate;
}
