package com.freedom.datazipcodeconsumer.repository;

import com.freedom.datazipcodeconsumer.domain.DataZipCode;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ZipCodeRepository extends MongoRepository<DataZipCode, String> {
	
	public List<DataZipCode> findByZipCode(String zipCode);

}
