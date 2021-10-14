package com.freedom.datazipcodeconsumer.service;

import com.freedom.datazipcodeconsumer.domain.DataZipCode;
import com.freedom.datazipcodeconsumer.exception.DataZipCodeConsumerException;

public interface ZipCodeService {
	
	DataZipCode getDataZipCode(String zipCode) throws DataZipCodeConsumerException;

}
