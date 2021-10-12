package com.freedom.datazipcodeconsumer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freedom.datazipcodeconsumer.domain.DataZipCode;
import com.freedom.datazipcodeconsumer.exception.DataZipCodeConsumerException;
import com.freedom.datazipcodeconsumer.exception.ZipCodeInvalidException;
import com.freedom.datazipcodeconsumer.repository.ZipCodeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ZipCodeServiceImpl implements ZipCodeService {
	
	@Autowired
	private ZipCodeRepository zipCodeRepository;

	@Override
	public DataZipCode getDataZipCode(String zipCode) throws DataZipCodeConsumerException {
		
		try {
		
			zipCode = formatZipCode(zipCode);
			
			isValidZipCode(zipCode);
			
			List<DataZipCode> listDataZipCode = zipCodeRepository.findByZipCode(zipCode);		
			
			return checkDataZipCode(listDataZipCode, zipCode);
		
		} catch (Exception ex) {			
			throw new ZipCodeInvalidException("Não foi possível carregar os dados do CEP: " +zipCode, ex);
		} 
	}
	
	
	private void isValidZipCode(String zipCode) throws DataZipCodeConsumerException {
		if (zipCode.length() != 8) {
			throw new ZipCodeInvalidException("CEP inválido");
		}		
	}
	
	private String formatZipCode(String zipCode) {
		return zipCode.replaceAll("[^\\d ]", "");
	}
	
	private DataZipCode checkDataZipCode(List<DataZipCode> listDataZipCode, String zipCode) throws DataZipCodeConsumerException {
		int pos = zipCode.length();
		
		while (listDataZipCode.get(0).getStreet().isBlank()) {
				
			log.warn("Endereço para o CEP {} está vazio", listDataZipCode.get(0).getZipCode());
			
			String newZipCode = replaceZeroZipCode(listDataZipCode.get(0).getZipCode(), pos);			
			listDataZipCode = zipCodeRepository.findByZipCode(newZipCode);			
			pos--;
		} 
			
		return listDataZipCode.get(0);		
	}
	
	private String replaceZeroZipCode(String zipCode, int pos) throws DataZipCodeConsumerException {
				
		StringBuilder zipCodeStringBuilder = new StringBuilder(zipCode);
		zipCodeStringBuilder.setCharAt(pos-1, '0');
		return zipCodeStringBuilder.toString();				
	}

}
