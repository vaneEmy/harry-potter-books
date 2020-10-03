package me.vanemy.harry.potter.books.business.service;

import me.vanemy.harry.potter.books.api.v1.assembler.BookAssembler;
import me.vanemy.harry.potter.books.api.v1.exception.FileStorageException;
import me.vanemy.harry.potter.books.api.v1.exception.MyFileNotFoundException;
import me.vanemy.harry.potter.books.api.v1.model.Book;
import me.vanemy.harry.potter.books.business.property.FileStorageProperties;
import me.vanemy.harry.potter.books.business.repository.BookEntity;
import me.vanemy.harry.potter.books.business.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class BookService {

    private final static String PATH_URL = "/api/v1/downloadFile/";
    private final Path fileStorageLocation;
    private final BookRepository bookRepository;

    @Autowired
    public BookService(FileStorageProperties fileStorageProperties, BookRepository bookRepository) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        this.bookRepository = bookRepository;
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public void createBook(MultipartFile file, String name, String price, String quantity) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName(name);
        bookEntity.setPrice(Integer.parseInt(price));
        bookEntity.setQuantity(Integer.parseInt(quantity));

        if(file != null) {
            String fileName = storeFile(file);
            bookEntity.setFileName(fileName);
            bookEntity.setUploadDir(PATH_URL);
            bookEntity.setSize(file.getSize());
            bookEntity.setDocumentFormat(file.getContentType());
        }

        bookRepository.save(bookEntity);
    }

    public Resource loadFileAsResource(String fileName) throws MyFileNotFoundException {
        try {
            boolean imageExists = this.bookRepository.existByFileName(fileName);

            if(imageExists){
                Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
                Resource resource = new UrlResource(filePath.toUri());

                if(resource.exists()) {
                    return resource;
                } else {
                    throw new MyFileNotFoundException("File not found " + fileName);
                }
            }else{
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

    public List<Book> getBooks(){


        return BookAssembler.toModels(this.bookRepository.findAll(), PATH_URL);
    }


    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

}
