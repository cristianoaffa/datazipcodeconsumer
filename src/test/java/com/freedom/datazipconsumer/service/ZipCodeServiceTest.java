package com.freedom.datazipconsumer.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;

import com.freedom.datazipcodeconsumer.config.MocksFactoryConfig;
import com.freedom.datazipcodeconsumer.domain.DataZipCode;
import com.freedom.datazipcodeconsumer.exception.DataZipCodeConsumerException;
import com.freedom.datazipcodeconsumer.repository.ZipCodeRepository;
import com.freedom.datazipcodeconsumer.service.ZipCodeService;
import com.freedom.datazipcodeconsumer.service.ZipCodeServiceImpl;

@ContextConfiguration(classes= { MocksFactoryConfig.class })
@DataMongoTest
public class ZipCodeServiceTest {
	
	@InjectMocks
	private ZipCodeService mockZipCodeService = new ZipCodeServiceImpl();
		
	@Mock
	private ZipCodeRepository mockZipCodeRepository; 
     
	@Test
	public void shouldReturnMessageZipCodeInvalid() throws DataZipCodeConsumerException {
		
		String zipCode = "123450030";
		Throwable zipCodeInvalidException = catchThrowable(() -> mockZipCodeService.getDataZipCode(zipCode));
	    assertThat(zipCodeInvalidException).hasMessageContaining("Não foi possível carregar os dados do CEP: 123450030");
	}
	
	//@Test
	public void shouldReturnOneElement() throws DataZipCodeConsumerException {
		
		String zipCode = "12209003";			
		DataZipCode dataZipCode = mockZipCodeService.getDataZipCode(zipCode);		
		assertEquals(dataZipCode, Matchers.notNullValue());		
	}
	
	//@Test
	public void shouldReturnDataZipCodeOneChange() throws DataZipCodeConsumerException {
		
		String zipCode = "66010900";
		DataZipCode dataZipCode = mockZipCodeService.getDataZipCode(zipCode);
		
		assertEquals("66010000", dataZipCode.getZipCode());
		assertEquals("Avenida Presidente Vargas - até 379/380", dataZipCode.getStreet());
		assertEquals("Campina", dataZipCode.getNeighborhood());
		assertEquals("Belém", dataZipCode.getCity());
		assertEquals("PA", dataZipCode.getState());		
		
	}
	
	//@Test
	public void shouldReturnDataZipCodeThreeChanges() throws DataZipCodeConsumerException {
		
		String zipCode = "12209675";
		DataZipCode dataZipCode = mockZipCodeService.getDataZipCode(zipCode);
		
		assertEquals("12209000", dataZipCode.getZipCode());
		assertEquals("Avenida Rui Barbosa - até 614/615", dataZipCode.getStreet());
		assertEquals("Jardim Bela Vista", dataZipCode.getNeighborhood());
		assertEquals("São José dos Campos", dataZipCode.getCity());
		assertEquals("SP", dataZipCode.getState());		
		
	}

}
