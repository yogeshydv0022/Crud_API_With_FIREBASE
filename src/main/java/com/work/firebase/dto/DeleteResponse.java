package com.work.firebase.dto;

import java.util.Date;

import lombok.Data;

@Data
public class DeleteResponse {
	
	private Date deleteDate;
	
	private boolean status;

}
