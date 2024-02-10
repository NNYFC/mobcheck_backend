package com.example.mobcheck.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "roles",uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Roles(String name) {
        this.name = name;
    }
}
