package me.vanemy.harry.potter.books;

import me.vanemy.harry.potter.books.business.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
@Import(SwaggerConfiguration.class)
public class HarryPotterBooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarryPotterBooksApplication.class, args);
	}


}
