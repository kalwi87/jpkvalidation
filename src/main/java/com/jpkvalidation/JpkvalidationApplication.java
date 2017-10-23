package com.jpkvalidation;

import com.jpkvalidation.service.StorageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class JpkvalidationApplication {

	@Resource
	StorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(JpkvalidationApplication.class, args);
	}


	public void run(String... args) throws Exception {
		storageService.deleteAll();
		storageService.init();
	}
}
