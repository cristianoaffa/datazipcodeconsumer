package com.freedom.datazipcodeconsumer.config;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.freedom.datazipcodeconsumer.repository.ZipCodeRepository;
import com.freedom.datazipcodeconsumer.service.ZipCodeService;

@Configuration
@ComponentScan(basePackages = {"com.freedom.datazipcodeconsumer"})
public class MocksFactoryConfig {

	@MockBean
	private ZipCodeService mockZipCodeService;
	
	@MockBean
	private ZipCodeRepository mockZipCodeRepository;
	
}
