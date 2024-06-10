package com.example.mobcheck.repository;

import com.example.mobcheck.model.Roles;
import com.example.mobcheck.model.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UsersRepositoryTests {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RolesRepository rolesRepository;

    @Test
    public void saveAllUsers(){
        Roles role = Roles.builder().name("ROLE").build();
        rolesRepository.save(role);
        Users user = Users.builder().email("email@email.com").firstname("first name").lastname("last name")
                .password("password").status(Boolean.TRUE).role(role).build();
        List<Users> usersList = new ArrayList<>();
        usersList.add(user);

        List<Users> savedUsers = usersRepository.saveAll(usersList);

        Assertions.assertFalse(savedUsers.isEmpty());
        Assertions.assertEquals(1, savedUsers.size());
        Assertions.assertEquals("email@email.com", savedUsers.get(0).getEmail());
    }
    @Test
    public void findUserByEmail(){
        Roles role = Roles.builder().name("ROLE").build();
        rolesRepository.save(role);
        Users user1 = Users.builder().email("email@email.com").firstname("first name").lastname("last name")
                .password("password").status(Boolean.TRUE).role(role).build();
        Users user2 = Users.builder().email("test@email.com").firstname("first name2").lastname("last name2")
                .password("password2").status(Boolean.TRUE).role(role).build();
        List<Users> usersList = new ArrayList<>();
        usersList.add(user1);
        usersList.add(user2);

        usersRepository.saveAll(usersList);
        Optional<Users> userFound = usersRepository.findByEmail("email@email.com");

        Assertions.assertFalse(userFound.isEmpty());
        Assertions.assertEquals("email@email.com", userFound.get().getEmail());
    }
}
