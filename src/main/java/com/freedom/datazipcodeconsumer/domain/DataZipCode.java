package com.freedom.datazipcodeconsumer.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Document(collection = "dataZipCode")
public class DataZipCode {
	
	@Id
	private String id;
	
	private String zipCode;	
	private String street;
	private String neighborhood;
	private String city;
	private String state;	

}