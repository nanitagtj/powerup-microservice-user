package com.pragma.powerup.usermicroservice.domain.validations;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.*;
import com.pragma.powerup.usermicroservice.configuration.Constants;

import java.time.LocalDate;
import java.time.Period;

public class UserValidations {
    public static void validateAge(LocalDate birthDate) {
        if (birthDate == null) {
            throw new BirthdateRequiredException();
        }

        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDate, currentDate).getYears();

        if (age < 18) {
            throw new InvalidAgeException(Constants.INVALID_AGE_EXCEPTION);
        }
    }

    public static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidNameException();
        }
    }

    public static void validateEmail(String email) {
        if (email == null || !email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")) {
            throw new InvalidMailException();
        }
    }
    public static void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches("\\+?[0-9]+")) {
            throw new InvalidPhoneException();
        }
    }

    public static void validateDni(String dni) {
        if (dni == null || !dni.matches("[0-9]+")) {
            throw new InvalidDniException();
        }
    }

    public static void validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new InvalidPasswordException();
        }
    }

    public static void validateDateInPast(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        if (date != null && date.isAfter(currentDate)) {
            throw new BirthdayPastException();
        }
    }

}
