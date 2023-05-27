package com.pragma.powerup.usermicroservice.domain.model;

import java.time.LocalDate;
import java.util.Optional;

public class User {
    private Long id;
    private String name;
    private String surname;
    private String mail;
    private String phone;
    private String dniNumber;
    private String password;
    private Role role;
    private LocalDate birthdate;

    public User(Long id, String name, String surname, String mail, String phone, String dniNumber, String password, Role role, LocalDate birthdate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.phone = phone;
        this.dniNumber = dniNumber;
        this.password = password;
        this.role = role;
        this.birthdate = birthdate;
    }

    public User() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDniNumber() {
        return dniNumber;
    }

    public void setDniNumber(String dniNumber) {
        this.dniNumber = dniNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
