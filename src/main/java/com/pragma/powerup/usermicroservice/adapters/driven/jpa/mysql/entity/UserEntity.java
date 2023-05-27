package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 125)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Provide a Name, not a number")
    private String name;
    @Column(nullable = false, length = 125)
    private String surname;
    @Email
    @Column(nullable = false, length = 125)
    private String mail;
    @Column(nullable = false, length = 13)
    @Pattern(regexp = "^\\+?[0-9]{12}$")
    private String phone;
    @Column(unique = true, nullable = false, length = 20)
    @Pattern(regexp = "^[0-9]+$")
    private String dniNumber;
    @Column(nullable = false)
    private String password;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_role")
    private RoleEntity role;
    @Past(message = "Birth date must be in the past")
    @Column(nullable = false)
    private LocalDate birthdate;
}
