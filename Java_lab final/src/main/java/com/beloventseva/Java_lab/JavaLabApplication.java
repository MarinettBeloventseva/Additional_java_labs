package com.beloventseva.Java_lab;

import com.beloventseva.Java_lab.services.ExceleFileManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication

public class JavaLabApplication {
	public static void main(String[] args) {

		SpringApplication.run(JavaLabApplication.class, args);

		parse();
	}

	private static void parse() {
		ExceleFileManager exceleFileManager = new ExceleFileManager();
		exceleFileManager.createRow();
	}

}
