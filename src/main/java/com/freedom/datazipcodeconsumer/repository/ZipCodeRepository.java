package com.freedom.datazipcodeconsumer.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.freedom.datazipcodeconsumer.domain.DataZipCode;

public interface ZipCodeRepository extends MongoRepository<DataZipCode, String> {
	
	public List<DataZipCode> findByZipCode(String zipCode);

}
