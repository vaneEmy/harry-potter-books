package me.vanemy.harry.potter.books.api.v1.assembler;

import me.vanemy.harry.potter.books.api.v1.model.Book;
import me.vanemy.harry.potter.books.business.repository.BookEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookAssembler {

    public static Book toModel(BookEntity bookEntity, String path) {
        Book book = new Book();

        Optional.of(bookEntity)
            .map(BookEntity::getId).ifPresent(id-> book.setIdentifier(id.toString()));

        Optional.of(bookEntity)
                .map(BookEntity::getName).ifPresent(book::setName);

        Optional.of(bookEntity)
                .map(BookEntity::getPrice).ifPresent(price -> book.setPrice(price.toString()));

        Optional.of(bookEntity)
                .map(BookEntity::getQuantity).ifPresent(quantity -> book.setQuantity(quantity.toString()));


        if(bookEntity.getFileName() != null){
            String fileName = bookEntity.getFileName();
            String uploadDir = bookEntity.getUploadDir();
            book.setBookImage(ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(uploadDir)
                    .path(fileName)
                    .toUriString());

        }


        return book;
    }

    public static List<Book> toModels(List<BookEntity> bookEntities, String path){
        final List<Book> bookModels = new ArrayList<>();
        bookEntities.forEach(bookEntity -> bookModels.add(toModel(bookEntity, path)));
        return bookModels;
    }
}
