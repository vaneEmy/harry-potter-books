package me.vanemy.harry.potter.books.api.v1.assembler;

import me.vanemy.harry.potter.books.api.v1.model.User;
import me.vanemy.harry.potter.books.business.repository.UserEntity;

public class UserAssembler {

    public static UserEntity toModel(User user){
        UserEntity userEntity = new UserEntity();

        return userEntity;
    }

}
