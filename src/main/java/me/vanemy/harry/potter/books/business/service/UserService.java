package me.vanemy.harry.potter.books.business.service;

import me.vanemy.harry.potter.books.api.v1.model.User;
import me.vanemy.harry.potter.books.business.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public void createUser(User user) {
        System.out.println("------------");
        System.out.println("------ SERVICE ----");
    }

    @Override
    public boolean userExists(String userIdentifier) {
        return this.userRepository.existByIdentifier(Integer.parseInt(userIdentifier));
    }
}
