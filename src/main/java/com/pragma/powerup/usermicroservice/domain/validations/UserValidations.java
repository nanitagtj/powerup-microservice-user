package com.pragma.powerup.usermicroservice.domain.validations;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.InvalidAgeException;
import com.pragma.powerup.usermicroservice.domain.exceptions.BadRequestException;

import java.time.LocalDate;
import java.time.Period;

import static com.pragma.powerup.usermicroservice.configuration.Constants.INVALID_AGE_EXCEPTION;

public class UserValidations {
    public static void validateAge(LocalDate birthDate) {
        if (birthDate == null) {
            throw new BadRequestException("Birth date is required");
        }

        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDate, currentDate).getYears();

        if (age < 18) {
            throw new InvalidAgeException(INVALID_AGE_EXCEPTION);
        }
    }
}
