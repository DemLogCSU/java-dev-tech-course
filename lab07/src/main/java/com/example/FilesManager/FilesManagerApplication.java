package com.example.FilesManager;

import com.example.FilesManager.services.DbService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FilesManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilesManagerApplication.class, args);
		DbService dbService = new DbService();
	}

}
