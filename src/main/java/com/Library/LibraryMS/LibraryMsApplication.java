package com.Library.LibraryMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LibraryMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryMsApplication.class, args);
	}

}
