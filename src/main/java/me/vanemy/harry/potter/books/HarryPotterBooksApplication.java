package me.vanemy.harry.potter.books;

import me.vanemy.harry.potter.books.business.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class HarryPotterBooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarryPotterBooksApplication.class, args);
	}


}
