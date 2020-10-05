package me.vanemy.harry.potter.books.api.v1.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class BookOrder {

    @NonNull
    private String bookIdentifier;

    @NonNull
    private String quantity;
}
