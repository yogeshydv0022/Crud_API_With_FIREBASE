package com.work.firebase.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class UpdateResponse {

	private String id;

	private Date updateAt;

}
