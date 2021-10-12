package com.freedom.datazipcodeconsumer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freedom.datazipcodeconsumer.domain.DataZipCode;
import com.freedom.datazipcodeconsumer.exception.DataZipCodeConsumerException;
import com.freedom.datazipcodeconsumer.exception.ZipCodeInvalidException;
import com.freedom.datazipcodeconsumer.repository.ZipCodeRepository;

@Service
public class ZipCodeServiceImpl implements ZipCodeService {
	
	@Autowired
	private ZipCodeRepository zipCodeRepository;

	@Override
	public DataZipCode getDataZipCode(String zipCode) throws DataZipCodeConsumerException {
		
		try {
		
		zipCode = formatZipCode(zipCode);
		
		isValidZipCode(zipCode);				
		
		List<DataZipCode> lista = zipCodeRepository.findByZipCode(zipCode);
		
		return lista.get(0);
		
		} catch (Exception ex) {			
			throw new ZipCodeInvalidException("Não foi possível carregar os dados do CEP: " + zipCode, ex);
		} 
	}
	
	
	private void isValidZipCode(String zipCode) throws DataZipCodeConsumerException{
		if(zipCode.length() != 8) {
			throw new ZipCodeInvalidException("CEP inválido!");
		}		
	}
	
	private String formatZipCode(String zipCode) {
		return zipCode.replaceAll("[^\\d ]", "");
	}

}
