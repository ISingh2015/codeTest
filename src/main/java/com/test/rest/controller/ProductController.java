package com.test.rest.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.rest.dao.OrderDAO;
import com.test.rest.dao.ProductDAO;
import com.test.rest.model.Porder;
import com.test.rest.model.Product;
import com.test.rest.model.Products;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(path = "/products")
public class ProductController {

	@Autowired
	private ProductDAO productDao;
	
	@Autowired
	private OrderDAO orderDao;

	/**
	 * Get All Products.
	 * @return
	 */
	@GetMapping(path = "/allProducts", produces = "application/json")
	public Products getAllProducts() {
		log.info("GetAllProducts");
		return productDao.getAllProducts();
	}

	/**
	 * Get Products Sold by a Company and by price, where price is not equal to entered price.
	 * @param company
	 * @param price
	 * @return
	 */
	@GetMapping(path = "/byCompany", produces = "application/json")
	public List<Product> getAllByCompanyOrPrice(@RequestParam(name = "comp") String company,
			@RequestParam(name = "price", required = false) Optional<Double> price) {
		log.info("GetAllProductsByCompany -> {} or Price -> {}", company, price);
		List<Product> products;
		if (price.isPresent()) {
			products = productDao.getAllProducts().getProductList().stream()
					.filter(prod -> (company.equals(prod.getCompany()) && price.get() != prod.getPrice()))
					.collect(Collectors.toList());

		} else {
			products = productDao.getAllProducts().getProductList().stream()
					.filter(prod -> company.equals(prod.getCompany())).collect(Collectors.toList());
		}
		return products;
	}


	/**
	 * Get Products Stock from Product and add OrderedQty (i.e. stock ordered) for the product from Orders placed.
	 * @param prodCode
	 * @return
	 */
	@GetMapping(path = "/getStock", produces = "application/json")
	public ResponseEntity<Double> getStock(@RequestParam(name = "prodCode") Optional<Integer> productCD) {
		log.info("GetStock -> {}", productCD);
		List<Product> products;
		List<Porder> orders;
		if (productCD.isPresent()) {
			products = productDao.getAllProducts().getProductList().stream()
					.filter(prod -> (productCD.get()==prod.getId()))
					.collect(Collectors.toList());
			orders = orderDao.getAllOrdersForProduct(productCD.get());
			if (!orders.isEmpty()) {
				double prodStockFromOrder = orders.stream().mapToDouble(ord -> ord.getQtyOrdered()).sum();
				return ResponseEntity.ok().body(products.get(0).getStock() + prodStockFromOrder);				
			} else {
				return ResponseEntity.ok().body(new Double(products.get(0).getStock()));
			}

		} else {
			return ResponseEntity.notFound().build(); // error 404
		}
	}
	
	/**
	 * Get Products sold By Category and by price, where price is not equal to entered price.
	 * @param category
	 * @param price
	 * @return
	 */
	@GetMapping(path = "/byCategory", produces = "application/json")
	public List<Product> getAllByCategoryOrPrice(@RequestParam(name = "cat") String category,
			@RequestParam(name = "price", required = false) Optional<Double> price) {
		log.info("GetAllProductsByCategory -> {} or Price -> {}", category, price);
		List<Product> products;
		if (price.isPresent()) {
			products = productDao.getAllProducts().getProductList().stream()
					.filter(prod -> (category.equals(prod.getCategory()) && price.get() != prod.getPrice()))
					.collect(Collectors.toList());

		} else {
			products = productDao.getAllProducts().getProductList().stream()
					.filter(prod -> category.equals(prod.getCategory())).collect(Collectors.toList());
		}
		return products;
	}

}
