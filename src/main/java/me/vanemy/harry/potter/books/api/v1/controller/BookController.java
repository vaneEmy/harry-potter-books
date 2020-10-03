package me.vanemy.harry.potter.books.api.v1.controller;

import me.vanemy.harry.potter.books.api.v1.model.Book;
import me.vanemy.harry.potter.books.business.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;


    @PostMapping("/books")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file", required = false ) MultipartFile file,
                                             @RequestParam("name") String name,
                                             @RequestParam("price") String price,
                                             @RequestParam("quantity") String quantity) {
        bookService.createBook(file, name, price, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = bookService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/books")
    public @ResponseBody List<Book> getBooks(){
        return this.bookService.getBooks();
    }

}
