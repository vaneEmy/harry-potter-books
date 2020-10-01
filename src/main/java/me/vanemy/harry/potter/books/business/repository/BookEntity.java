package me.vanemy.harry.potter.books.business.repository;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "books_catalog")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "upload_dir")
    private String uploadDir;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "document_format")
    private String documentFormat;

    @Column(name = "price")
    private int price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "size")
    private long size;

}
