package com.work.firebase.entity;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
	private String id;
	
	private String name;
	
	private String email;
	
	private String mobile;
	

}
