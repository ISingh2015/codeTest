package com.test.rest.controller;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.rest.dao.OrderDAO;
import com.test.rest.dao.ProductDAO;
import com.test.rest.model.Porder;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(path = "/order")
public class OrderController {

	@Autowired
	private ProductDAO productDao;

	@Autowired
	private OrderDAO orderDao;

	/**
	 * Save dummy order if stock of product ordered is <> dummy orders.
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@PostMapping(path = "/addOrder", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> addOrder(@RequestBody Porder order) throws Exception {
		productDao.getAllProducts().getProductList().stream().filter(prod -> order.getProductId() == prod.getId())
				.findFirst().ifPresent(product -> {
					long ordCount = orderDao.findAll().stream().filter(ord -> product.getId() == ord.getProductId())
							.count();
					if (product.getStock() != ordCount) {
						log.info("Saving Order -> {}", order.getDescription());
						orderDao.save(order);
					}
				});
		return ResponseEntity.ok("Added ok " + order.getId());
	}

	/**
	 * Delete dummy order or delete all if not present
	 * 
	 * @param orderNo
	 * @return
	 */
	
	@RequestMapping(path= { "deleteAll", "delete/{id}" }, method=RequestMethod.DELETE)
	public Boolean delOrder(@PathVariable(name = "id", required = false) Optional<Integer> orderNo) {
		boolean ordDeleted = true;
		if (orderNo.isPresent()) {
			Optional<Porder> porder = orderDao.findById(orderNo.get());
			if (porder.isPresent()) {
				orderDao.delete(porder.get());
			} else {
				ordDeleted=false;
			}
		} else {
			orderDao.deleteAll();
		}
		return ordDeleted;
	}
}
