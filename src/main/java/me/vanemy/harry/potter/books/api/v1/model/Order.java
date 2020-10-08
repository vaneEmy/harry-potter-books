package me.vanemy.harry.potter.books.api.v1.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Order {

    @NonNull
    private List<BookOrder> books;

    @NonNull
    private String total;

    @NonNull
    private String username;
}
