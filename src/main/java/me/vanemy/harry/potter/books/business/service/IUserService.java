package me.vanemy.harry.potter.books.business.service;

import me.vanemy.harry.potter.books.api.v1.model.User;

public interface IUserService {

    void createUser(User user);

    boolean userExists(String userIdentifier);
}
