package me.vanemy.harry.potter.books.api.v1.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class User {

    @NonNull
    private String identifier;

    @NonNull
    private String name;

    @NonNull
    private String password;
}
