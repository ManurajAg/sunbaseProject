package com.sunbase.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.sunbase.entities.Customer;
import com.sunbase.entities.JwtRequest;
import com.sunbase.entities.JwtResponse;
import com.sunbase.repository.customerRepository;

/*
 * Controller which handles all the request like adding contact, search it, and get contacts
 */

@RestController
@CrossOrigin("*")
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private customerRepository customerRepo;
	/*
	@PostMapping("/token")
	public ResponseEntity<?> loginUser(@RequestBody JwtRequest req){
		String email = req.getEmail();
		String password = req.getPassword();
		System.out.println(email+""+password);
		JwtResponse response = JwtResponse.builder()
                .jwtToken("Sdfasdfwer1232141")
                .username("Admin").build();
        return new ResponseEntity<>(response, HttpStatus.OK);
	}
	*/
	@PostMapping("/addContact")
	public ResponseEntity<?> saveUser(@RequestBody Customer customer) {
		Customer cust = customerRepo.save(customer);
		if(cust != null)
			return ResponseEntity.ok("Contact Added");
		else 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@GetMapping("/getCustomers/{page}")
	public ResponseEntity<Page<Customer>>getCust(@PathVariable("page") int page){
		PageRequest  pageable = PageRequest.of(page, 5);	//5 contacts per page
		Page<Customer> l = customerRepo.findAllByOrderByEmailAsc(pageable); 
		return ResponseEntity.of(Optional.of(l));
	}
	@GetMapping("/getCustomersById/{id}")
	public ResponseEntity<Optional> getCustById(@PathVariable("id")String id){
		
		
		Optional c = customerRepo.findById(id.substring(1,id.length()-1));
		if(c == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(c));
	}
	
	@PostMapping("/updateCustomer")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer){
		Customer FinalCust = customerRepo.save(customer);
		if(FinalCust != null)
			return ResponseEntity.ok("Customer Updated");
		else 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@PostMapping("/updateCustomersFromAPI")
	public ResponseEntity<?> updateCustomer(@RequestBody List<Customer> customers){
		for(Customer c:customers) {
			Customer FinalCust = customerRepo.save(c);
		}
		return ResponseEntity.ok("Customer Updated");
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String uuid){
		try {
			
			customerRepo.deleteById(uuid);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/getCustomerByProperty/{prop}/{query}")
	public ResponseEntity<List<Customer>> getCustByProp(@PathVariable("prop")String prop,@PathVariable("query") String query){
		List<Customer> l=new ArrayList<>();
			
			if(prop.equals("first_name")){
				l = customerRepo.getByfirstName(query);
			}
			else if(prop.equals("city")){
				System.out.println("Hi");
				l = customerRepo.findByCityContaining(query);
			}
			else if(prop.equals("email")){
				l = customerRepo.findByEmailContaining(query);
			}
			else if(prop.equals("phone")){
				l = customerRepo.findByPhoneContaining(query);
			}
		
		
		if(l.size()>0) {
			return ResponseEntity.of(Optional.of(l));
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
 	
}
