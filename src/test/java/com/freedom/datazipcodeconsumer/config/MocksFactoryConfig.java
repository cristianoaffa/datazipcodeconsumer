package com.freedom.datazipcodeconsumer.config;

import com.freedom.datazipcodeconsumer.repository.ZipCodeRepository;
import com.freedom.datazipcodeconsumer.service.ZipCodeService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.freedom.datazipcodeconsumer"})
public class MocksFactoryConfig {

	@MockBean
	private ZipCodeService mockZipCodeService;
	
	@MockBean
	private ZipCodeRepository zipCodeRepository;
	
}
