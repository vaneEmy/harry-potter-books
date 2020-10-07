package me.vanemy.harry.potter.books.business.service;

import me.vanemy.harry.potter.books.api.v1.model.User;
import me.vanemy.harry.potter.books.business.repository.UserEntity;
import me.vanemy.harry.potter.books.business.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
       /* Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(s);
        User user = new User();

        System.out.println("------------------------");

        if(!optionalUserEntity.isPresent()){
            throw new UsernameNotFoundException(s);
        }

        if(optionalUserEntity.isPresent()){
            user.setIdentifier(optionalUserEntity.get().getId().toString());
            user.setUsername(optionalUserEntity.get().getUsername());
            user.setPassword(optionalUserEntity.get().getPassword());
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());
        */
        if ("harrypotter".equals(s)) {
            return new org.springframework.security.core.userdetails.User("harrypotter", "$2a$10$4p.TsDTmkvHQpw3NFzUN6eAQz.lYFdmDee2dfIwLTSxvI44z7kYU6", Collections.emptyList());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + s);
        }
    }

}
