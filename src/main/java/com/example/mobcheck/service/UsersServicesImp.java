package com.example.mobcheck.service;

import com.example.mobcheck.dto.BlacklistDto;
import com.example.mobcheck.dto.ResponseBody;
import com.example.mobcheck.dto.UsersDto;
import com.example.mobcheck.model.Roles;
import com.example.mobcheck.model.Users;
import com.example.mobcheck.repository.RolesRepository;
import com.example.mobcheck.repository.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersServicesImp implements UsersServices{
    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = usersRepository.findByEmail(username);
        if(user.isPresent() && user.get().getStatus().equals(Boolean.TRUE)){
            return new org.springframework.security.core.userdetails.User(
                    user.get().getEmail(), user.get().getPassword(),mapRoleToAuthority(user.get().getRole().getName())
            );
        }else{
            throw new UsernameNotFoundException("User not Found");
        }
    }

    private Collection<? extends GrantedAuthority> mapRoleToAuthority(String role){
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public void addRoleAndAdmin() {
        List<Roles> roles = rolesRepository.findAll();
        if(roles.isEmpty()){
            roles.add(new Roles("USER"));
            roles.add(new Roles("ADMIN"));
            rolesRepository.saveAll(roles);
            Optional<Roles> role = rolesRepository.findByName("ADMIN");
            role.ifPresent(value -> usersRepository.save(
                    new Users("admin", "admin", "admin@admin.com", passwordEncoder.encode("12345"), value)
                    ));
        }
    }

    @Override
    public ResponseEntity<?> addUser(UsersDto usersDto) {
        Optional<Users> user = usersRepository.findByEmail(usersDto.getEmail());
        if (user.isPresent()){
            return ResponseEntity.status(403).body("User already exist");
        }else {
            Optional<Roles> roles = rolesRepository.findByName("USER");
            if(roles.isPresent()){
                Users users = new Users(usersDto.getFirstname(), usersDto.getLastname(), usersDto.getEmail(), passwordEncoder.encode(usersDto.getPassword()),roles.get());
                return ResponseEntity.status(201).body(new ResponseBody(201,"Save successfully",usersRepository.save(users)));
            }else {
                return ResponseEntity.status(404).body(new ResponseBody(404,"Role not found",null));
            }
        }
    }

    @Override
    public ResponseEntity<?> getPersonalData(String email) {
        return ResponseEntity.status(200).body(new ResponseBody(200,"Fetch successfully",usersRepository.findByEmail(email)));
    }

    @Override
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.status(200).body(new ResponseBody(200,"Fetch successfully",usersRepository.findAll()));
    }

    @Override
    public ResponseEntity<?> changeUserStatus(Long id) {
        Optional<Users> users = usersRepository.findById(id);
        if (users.isPresent()){
            if(users.get().getStatus().equals(Boolean.TRUE)){
                users.get().setStatus(Boolean.FALSE);
            }else{
                users.get().setStatus(Boolean.TRUE);
            }
            return ResponseEntity.status(200).body(new ResponseBody(200,"Fetch successfully",usersRepository.save(users.get())));
        }else {
            return ResponseEntity.status(404).body(new ResponseBody(404,"User with id not found",null));
        }
    }

    @Override
    public ResponseEntity<?> addBlacklist(BlacklistDto blacklistDto) {
        return null;
    }
}
