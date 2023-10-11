package org.restapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name= "test_tabell")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name", nullable=false)
    @NotBlank
    @Size(max = 150)
    private String name;

    @Column(name="country", nullable = false)
    @NotBlank
    @Size(max = 150)
    private String country;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // // @Override
    // // public int compareTo(User o) {
    // //     return id - o.getId();
    // // }
}