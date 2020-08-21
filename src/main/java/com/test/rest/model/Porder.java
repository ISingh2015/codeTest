package com.test.rest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Porder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	private Integer id;

	@Column(name = "productId")
	private int productId;

	@Column(name = "qtyOrdered")
	private double qtyOrdered;

	@Column(name = "description")
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	public Porder(Integer id, int productId, double qtyOrdered, String description, Date createdDate) {
		super();
		this.id = id;
		this.productId = productId;
		this.qtyOrdered = qtyOrdered;
		this.description = description;
		this.createdDate = createdDate;
	}

	
}
