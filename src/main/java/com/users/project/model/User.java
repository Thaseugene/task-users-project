package com.users.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "surname", length = 40, nullable = false)
    private String surname;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "middle_name", length = 40)
    private String middleName;

    @Column(length = 50, nullable = false, name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false, columnDefinition = "ENUM('ADMINISTRATOR', 'SALE_USER', 'CUSTOMER_USER', 'SECURE_API_USER')")
    private UserRole role;


    public User() {
    }

    public User(String surname, String name, String middleName, String email, UserRole role) {
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.email = email;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

}
