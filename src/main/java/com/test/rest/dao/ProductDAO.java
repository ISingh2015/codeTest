package com.test.rest.dao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import com.test.rest.model.Product;
import com.test.rest.model.Products;

@Repository
public class ProductDAO 
{
    private static Products list = new Products();
	private static final String SAMPLE_CSV_FILE_PATH = "products.csv";
	
//    /**
//     * Populate Test Products for Get End points. 
//     */
//    static 
//    {
//        list.getProductList().add(new Product(1, "Mobiles","Apple","AP1","Black","Some description about AP1",70000,13,11));
//        list.getProductList().add(new Product(2, "Mobiles","Samsung","SP1","Grey","Some description about SP1",50000,2,2));        
//        list.getProductList().add(new Product(3, "Mobiles","MI","MP1","Black","Some description about MP1",20000,9,35 ));
//        list.getProductList().add(new Product(4, "Computers","Intel","IL1","Grey","Some description about IL1",67000,0,106));
//        list.getProductList().add(new Product(5, "Computers","Intel","IL2","Black","Some description about IL2",74000,6,300));
//        list.getProductList().add(new Product(6, "Computers","Lenovo","LL1","Black","Some description about LL2",80000,10,138));
//        list.getProductList().add(new Product(7, "Television","LG","LT1","Black","Some description about LT1",42500,8,62));
//        list.getProductList().add(new Product(8, "Television","Samsung","ST1","Grey","Some description about ST1",58360,16,168));
//
//    }
    
    public ProductDAO () throws Exception{
    	populateData();
    }
    
    public Products getAllProducts() 
    {
        return list;
    }
    
    /**
     * Adding to existing product List.
     * @param product
     */
    
    public void addProduct(Product product) {
        list.getProductList().add(product);
    }

    /**
     * Method to work with CSV file. creates PRODUCT object from data in CSV file. 
     * Included key and 7 others fields for PRODUCT.CSV for now. 
     * Should work with 10000 records in CSV file with expected FORMAT. 
     * Please see CSV file for Format.
     * @throws Exception
     */
    private void populateData() throws Exception {
		Resource resource = new ClassPathResource(SAMPLE_CSV_FILE_PATH);
		
    	InputStream input = resource.getInputStream();
		input = resource.getInputStream();
		InputStreamReader isReader = new InputStreamReader(input);
		BufferedReader reader = new BufferedReader(isReader);
		String str;
		int keyForItem = 0;
		while ((str = reader.readLine()) != null) {
			String data[] = str.split(",");
			try {
				Product product = new Product(keyForItem, data[0], data[1], data[2], data[3], data[4],
						new Double(data[5]).doubleValue(), new Double(data[6]).doubleValue(),
						new Integer(data[7]).intValue());
				addProduct(product);
				keyForItem++;
				System.out.println(product.toString());
			} catch (Exception e) {
				// ignoring errors (e.g: case of header present)
			}
		}
		isReader.close();
		reader.close();
    }
}
