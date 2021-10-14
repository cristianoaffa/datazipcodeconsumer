package com.freedom.datazipconsumer.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.freedom.datazipcodeconsumer.config.MocksFactoryConfig;
import com.freedom.datazipcodeconsumer.domain.DataZipCode;
import com.freedom.datazipcodeconsumer.exception.DataZipCodeConsumerException;
import com.freedom.datazipcodeconsumer.repository.ZipCodeRepository;
import com.freedom.datazipcodeconsumer.service.ZipCodeService;
import com.freedom.datazipcodeconsumer.service.ZipCodeServiceImpl;
import com.freedom.datazipconsumer.builder.DataZipCodeBuilder;

@ContextConfiguration(classes= { MocksFactoryConfig.class })
@DataMongoTest
@ExtendWith(SpringExtension.class)
class ZipCodeServiceTest {
	
	@InjectMocks
	private ZipCodeService mockZipCodeService = new ZipCodeServiceImpl();
		
	@Mock
	private ZipCodeRepository mockZipCodeRepository; 
	
	@Test
	void shouldZipCodeInvalidLessLengthtWithError() throws DataZipCodeConsumerException {
		
		String zipCode = "1234";
		Throwable zipCodeInvalidException = catchThrowable(() -> mockZipCodeService.getDataZipCode(zipCode));
		
	    assertThat(zipCodeInvalidException).hasMessageContaining("Não foi possível carregar os dados do CEP: 1234");
	}
     
	@Test
	void shouldReturnMessageInvalidZipCodeNotFoundWithError() throws DataZipCodeConsumerException {
		
		String zipCode = "123450030";
		Throwable zipCodeInvalidException = catchThrowable(() -> mockZipCodeService.getDataZipCode(zipCode));
		
	    assertThat(zipCodeInvalidException).hasMessageContaining("Não foi possível carregar os dados do CEP: 123450030");
	}
	
	@Test
	void shouldReturnOneDataZipCodeWithSuccess() throws DataZipCodeConsumerException {
	
        String zipCode = "37500026";	        
        when(mockZipCodeRepository.findByZipCode(zipCode)).thenReturn(DataZipCodeBuilder.dataZipCodeListValidOneElementTest1(zipCode));
        
        DataZipCode dataZipCode = mockZipCodeService.getDataZipCode(zipCode);
        
        assertEquals("Rua Santos Pereira", dataZipCode.getStreet());
        assertEquals("Centro", dataZipCode.getNeighborhood());
        assertEquals("Itajubá", dataZipCode.getCity());
        assertEquals("MG", dataZipCode.getState());	        	
	}
	
	@Test
	void shouldReturnDataZipCodeOneChangeWithSuccess() throws DataZipCodeConsumerException {
		
		String zipCode = "66010900";		
		when(mockZipCodeRepository.findByZipCode(zipCode)).thenReturn(DataZipCodeBuilder.dataZipCodeListValidElementEmptyTest(zipCode));
		
		String zipCodeValid = "66010000";		
		when(mockZipCodeRepository.findByZipCode(zipCodeValid)).thenReturn(DataZipCodeBuilder.dataZipCodeListValidOneElementTest2(zipCodeValid));
		
		DataZipCode dataZipCode = mockZipCodeService.getDataZipCode(zipCode);
		
		assertEquals("Avenida Presidente Vargas - até 379/380", dataZipCode.getStreet());
		assertEquals("Campina", dataZipCode.getNeighborhood());
		assertEquals("Belém", dataZipCode.getCity());
		assertEquals("PA", dataZipCode.getState());				
	}
	
	@Test
	void shouldReturnDataZipCodeThreeChangesWithSuccess() throws DataZipCodeConsumerException {
		
		String zipCode = "12209675";
		when(mockZipCodeRepository.findByZipCode(zipCode)).thenReturn(DataZipCodeBuilder.dataZipCodeListValidElementEmptyTest(zipCode));
		
		String zipCode1 = "12209670";
		when(mockZipCodeRepository.findByZipCode(zipCode1)).thenReturn(DataZipCodeBuilder.dataZipCodeListValidElementEmptyTest(zipCode1));
		
		String zipCode2 = "12209600";
		when(mockZipCodeRepository.findByZipCode(zipCode2)).thenReturn(DataZipCodeBuilder.dataZipCodeListValidElementEmptyTest(zipCode2));
		
		String zipCodeValid = "12209000";
		when(mockZipCodeRepository.findByZipCode(zipCodeValid)).thenReturn(DataZipCodeBuilder.dataZipCodeListValidOneElementTest3(zipCodeValid));
				
		DataZipCode dataZipCode = mockZipCodeService.getDataZipCode(zipCode);
		
		assertEquals("Avenida Rui Barbosa - até 614/615", dataZipCode.getStreet());
		assertEquals("Jardim Bela Vista", dataZipCode.getNeighborhood());
		assertEquals("São José dos Campos", dataZipCode.getCity());
		assertEquals("SP", dataZipCode.getState());		
	}

}
