package me.vanemy.harry.potter.books.api.v1.controller;

import me.vanemy.harry.potter.books.business.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    BookService bookService;

    @PostMapping("/order")
    public ResponseEntity<String> createOrder(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
