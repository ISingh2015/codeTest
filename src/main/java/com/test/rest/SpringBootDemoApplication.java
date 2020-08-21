package com.test.rest;


import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EntityScan("com.test.rest.model")
@Slf4j
public class SpringBootDemoApplication implements CommandLineRunner {

	private static final String PRODUCT_CSV_FILE = "products.csv";
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SpringBootDemoApplication.class);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {

		
//		Resource resource = new ClassPathResource(PRODUCT_CSV_FILE);
//		InputStream input = resource.getInputStream();
//		try
//	    {
//	        byte[] bdata = FileCopyUtils.copyToByteArray(input);
//	        String data = new String(bdata, StandardCharsets.UTF_8);
//	        log.info(data);
//	    } 
//	    catch (IOException e) 
//	    {
//	        log.error("IOException", e);
//	    }
//		
//
	}
}
