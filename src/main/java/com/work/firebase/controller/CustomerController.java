package com.work.firebase.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.work.firebase.dto.CreateResponse;
import com.work.firebase.dto.CustomerList;
import com.work.firebase.dto.DeleteResponse;
import com.work.firebase.dto.UpdateResponse;
import com.work.firebase.entity.Customer;
import com.work.firebase.service.CustomerService;


@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/")
	public CreateResponse createResponse(@RequestBody Customer customer) throws InterruptedException, ExecutionException {
		return customerService.createCustomer(customer);
	}
	
	@GetMapping("/")
	public CustomerList getAllCustomer() throws InterruptedException, ExecutionException {
		return customerService.getCustomerList();	
	}
	
	@GetMapping("/{name}")
	public CustomerList getByName(@PathVariable("name") String name) throws InterruptedException, ExecutionException {
		
		return customerService.getCustomerByKey(name);
	}
	
	@PutMapping("/")
	public UpdateResponse Update(@RequestBody Customer customer) throws InterruptedException, ExecutionException {
		
		return customerService.updateCustomer(customer);
	}
	
	
	@DeleteMapping("/{id}")
	public DeleteResponse deleteCustomer(@PathVariable("id") String id) throws InterruptedException, ExecutionException {
		
		return customerService.deleteCustomer(id);
	}
	
	
	
	
	

}
