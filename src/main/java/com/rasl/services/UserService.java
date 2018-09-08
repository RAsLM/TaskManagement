package com.rasl.services;

import com.google.common.collect.ImmutableList;
import com.rasl.pojo.Role;
import com.rasl.pojo.User;
import com.rasl.pojo.dto.UserDTO;
import com.rasl.repositories.UserRepository;
import com.rasl.services.interfaces.PojoService;
import lombok.NonNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * Service class fof {@link User}.
 * Implementation of {@link PojoService} interface.
 *
 * @author Aslanov Ruslan
 * @version 1.0
 */

@Service
public class UserService implements UserDetailsService {
    private UserRepository repository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> list() {
        return repository.findAll();
    }

    public User getById(Integer id) {
        return repository.getOne(id);
    }

    public User save(User user) {
        user.setAuthorities(ImmutableList.of(Role.USER));
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        return repository.save(user);
    }


    public void delete(Integer id) {
        repository.delete(id);
    }

    public User getCurrentLoggedInUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        User user = (User) loadUserByUsername(name);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        for (User user : list()){
            if (user.getUsername().equals(username))
                return user;
        }
        throw new UsernameNotFoundException(username + " not found!");
    }

    public boolean userExists(@NotNull @NotEmpty String username) {
        for (User user : list()){
            if (user.getUsername().equals(username))
                return true;
        }
        return false;
    }

    public boolean userWithEmailExists(@NotNull @NotEmpty String email){
        for (User user : list()){
            if (email.equals(user.getEmail()))
                return true;
        }

        return false;
    }

    public User createUser(UserDTO userDto){
        User user = new User();
        user.setAuthorities(Arrays.asList(Role.values()));
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        repository.save(user);
        return user;
    }
}