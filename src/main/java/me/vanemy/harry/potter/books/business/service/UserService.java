package me.vanemy.harry.potter.books.business.service;

import me.vanemy.harry.potter.books.api.v1.assembler.UserAssembler;
import me.vanemy.harry.potter.books.api.v1.model.User;
import me.vanemy.harry.potter.books.business.repository.UserEntity;
import me.vanemy.harry.potter.books.business.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UserService implements IUserService {

    private final static int STRENGTH = 10;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public void createUser(User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder =
                new BCryptPasswordEncoder(STRENGTH, new SecureRandom());
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        UserEntity userEntity = UserAssembler.toModel(user, encodedPassword);
        this.userRepository.save(userEntity);
    }

    @Override
    public boolean userExists(String userIdentifier) {
        return this.userRepository.existByIdentifier(Integer.parseInt(userIdentifier));
    }
}
