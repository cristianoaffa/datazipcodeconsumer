package com.freedom.datazipcodeconsumer.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Document(collection = "dataZipCode")
public class DataZipCode {
	
	@Id
	@JsonIgnore
	private String id;
	
	@JsonIgnore
	private String zipCode;
	
	private String street;
	private String neighborhood;
	private String city;
	private String state;	

}
