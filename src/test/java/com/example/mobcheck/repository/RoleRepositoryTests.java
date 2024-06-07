package com.example.mobcheck.repository;

import com.example.mobcheck.model.Roles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RoleRepositoryTests {
    @Autowired
    private RolesRepository rolesRepository;

    @Test
    public void rolesAdding(){
        Roles role1 = Roles.builder().name("ADMIN").build();
        Roles role2 = Roles.builder().name("CLIENT").build();
        List<Roles> roles = new ArrayList<>();
        roles.add(role1);
        roles.add(role2);

        List<Roles> savedRoles = rolesRepository.saveAll(roles);

        Assertions.assertFalse(savedRoles.isEmpty());
        Assertions.assertTrue(savedRoles.get(0).getId()>0);
    }
}
