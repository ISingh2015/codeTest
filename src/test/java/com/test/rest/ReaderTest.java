package com.test.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import com.test.rest.model.Product;

import lombok.extern.slf4j.Slf4j;

/**
 * Please Ignore as used only for generating PRODUCT object from Test.
 * using for RND
 * 
 * @author Inderjit SS
 *
 */
@Slf4j
public class ReaderTest {
	private static final String SAMPLE_CSV_FILE_PATH = "products.csv";

	public static void main(String[] args) throws IOException {
		Resource resource = new ClassPathResource(SAMPLE_CSV_FILE_PATH);

		InputStream input = resource.getInputStream();
//		File file = resource.getFile();
//		System.out.println(file.getAbsolutePath());
//		File file1 = ResourceUtils.getFile("classpath:"+SAMPLE_CSV_FILE_PATH);
		try {
			byte[] bdata = FileCopyUtils.copyToByteArray(input);
//			String data = new String(bdata, StandardCharsets.UTF_8);
//	        log.info(data);
		} catch (IOException e) {
			log.error("IOException", e);
		}
		// try (Reader reader =
		// Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
//		try (Reader reader = new BufferedReader(new InputStreamReader(input,StandardCharsets.UTF_8));
//		CSVReader  csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();) {
//			String[] nextRecord;
//			while ((nextRecord = csvReader.readNext()) != null) {
//				System.out.println("Name : " + nextRecord[0]);
//				System.out.println("Email : " + nextRecord[1]);
//				System.out.println("Phone : " + nextRecord[2]);
//				System.out.println("Country : " + nextRecord[3]);
//				System.out.println("==========================");
//			}
//		}
		input = resource.getInputStream();
		InputStreamReader isReader = new InputStreamReader(input);
		BufferedReader reader = new BufferedReader(isReader);
//		StringBuffer sb = new StringBuffer();
		String str;
		int keyForItem = 0;
		while ((str = reader.readLine()) != null) {
			String data[] = str.split(",");
			List<Product> productsList = new ArrayList<>();
			try {
				Product product = new Product(keyForItem, data[0], data[1], data[2], data[3], data[4],
						new Double(data[5]).doubleValue(), new Double(data[6]).doubleValue(),
						new Integer(data[7]).intValue());
				productsList.add(product);
				System.out.println(product.toString());
			} catch (Exception e) {
				// ignoring errors (e.g: case of header present)
			}
//	    	  sb.append(str);
		}
		isReader.close();
		reader.close();
		// System.out.println(sb.toString());
	}
}
