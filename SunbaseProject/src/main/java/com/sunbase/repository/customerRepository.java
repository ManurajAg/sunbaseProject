package com.sunbase.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunbase.entities.Customer;

/*
 * This interface extends built in JpaRepository which provides built in database operations
 */

public interface customerRepository extends JpaRepository<Customer,String>{
	
	public Page<Customer> findAllByOrderByEmailAsc(Pageable page);
	
    @Query("select c from Customer c where lower(c.first_name) like lower(CONCAT('%', :n, '%'))")
	public List<Customer> getByfirstName(@Param("n") String first_name);
	
	public List<Customer> findByCityContaining(String city);
	public List<Customer> findByEmailContaining(String email);
	public List<Customer> findByPhoneContaining(String phone);
	
}
