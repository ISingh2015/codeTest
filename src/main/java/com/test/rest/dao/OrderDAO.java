package com.test.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.rest.model.Porder;

/**
 * Orders CRUD done using JPA repository no additional methods added for now
 * can be implemented is Service required on top of DAO
 * @author 10659074
 *
 */
@Repository
public interface OrderDAO extends JpaRepository<Porder, Integer> {
}
