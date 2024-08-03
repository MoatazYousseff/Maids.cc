package com.Library.LibraryMS.models;

import jakarta.persistence.*;

import java.util.Set;

@Table(name = "patron")
@Entity
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patronId;




    private String name;
    private Long phone;
    private String email;

    public Long getPatronId() {
        return patronId;
    }

    public void setPatronId(Long patronId) {
        this.patronId = patronId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
