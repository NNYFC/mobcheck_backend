package com.example.mobcheck.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users",uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@NamedNativeQuery(
        name = "Users.findByEmail",
        query = "SELECT u.id,u.firstname,u.lastname,u.email,u.password,u.status,u.role " +
                "FROM users u WHERE u.email = :email",
        resultClass = Users.class
)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    @Column(nullable = false)
    private Boolean status=true;
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name = "role",referencedColumnName = "id")
    private Roles role;

    public Users(String firstname, String lastname, String email, String password, Roles role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = true;
    }
}
