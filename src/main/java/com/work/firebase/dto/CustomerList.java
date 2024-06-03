package com.work.firebase.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.work.firebase.entity.Customer;

import lombok.Data;

@Data
@Component
public class CustomerList {
	
	private List<Customer> list;

}
