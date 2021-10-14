package com.freedom.datazipcodeconsumer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freedom.datazipcodeconsumer.domain.DataZipCode;
import com.freedom.datazipcodeconsumer.domain.ZipCodeVO;
import com.freedom.datazipcodeconsumer.exception.DataZipCodeConsumerException;
import com.freedom.datazipcodeconsumer.service.ZipCodeService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/zipcode")
public class ZipCodeController {
	
	private final ZipCodeService zipCodeService;
	
	@ApiOperation(value = "Returns the data of a valid ZipCode")
	@PostMapping(value="/data", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> returnDataZipCode(@RequestBody ZipCodeVO zipCodeVO) throws DataZipCodeConsumerException {
		
		try {			
			DataZipCode dataZipCode = zipCodeService.getDataZipCode(zipCodeVO.getZipCode());
			return ResponseEntity.ok(dataZipCode);					
			
		} catch (Exception ex) {						
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getCause().getMessage());			
		}
			
	}
	
}