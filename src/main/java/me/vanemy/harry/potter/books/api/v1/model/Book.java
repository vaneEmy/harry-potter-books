package me.vanemy.harry.potter.books.api.v1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {

    private String identifier;

    private String name;

    private String price;

    private String quantity;

    private String bookImage;
}
