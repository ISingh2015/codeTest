package com.test.rest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Please Ignore as used only for generating PRODUCT object from Test.
 * using for RND
 * 
 * @author Inderjit SS
 *
 */

@Slf4j
public class ReaderTest2 {
	private static final String SAMPLE_CSV_FILE_PATH = "products.csv";

	public static void main(String[] args) throws IOException {
		Resource resource = new ClassPathResource("products.csv");

		InputStream input = resource.getInputStream();
		File file = resource.getFile();
		System.out.println(file.getAbsolutePath());
//		File file1 = ResourceUtils.getFile("classpath:products.csv");
		try
	    {
	        byte[] bdata = FileCopyUtils.copyToByteArray(input);
	        String data = new String(bdata, StandardCharsets.UTF_8);
//	        log.info(data);
	    } 
	    catch (IOException e) 
	    {
	        log.error("IOException", e);
	    }
		//		try (Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
//		try (Reader reader = Files.newBufferedReader(Paths.get(resource.getFilename()));
//		CSVReader  csvReader = new CSVReader(reader);) {
//			String[] nextRecord;
//			while ((nextRecord = csvReader.readNext()) != null) {
//				System.out.println("Name : " + nextRecord[0]);
//				System.out.println("Email : " + nextRecord[1]);
//				System.out.println("Phone : " + nextRecord[2]);
//				System.out.println("Country : " + nextRecord[3]);
//				System.out.println("==========================");
//			}
//		}
	}
}
