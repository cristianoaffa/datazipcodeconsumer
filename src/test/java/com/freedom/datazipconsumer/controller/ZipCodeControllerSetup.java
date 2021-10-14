package com.freedom.datazipconsumer.controller;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freedom.datazipcodeconsumer.controller.ZipCodeController;
import com.freedom.datazipcodeconsumer.service.ZipCodeService;

public abstract class ZipCodeControllerSetup {
	
	protected final ObjectMapper objectMapper = new ObjectMapper();
	    
	protected MockMvc mockMvc;

	@Mock
	protected ZipCodeService mockZipCodeService;
	
 	@Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        final var zipCodeController = new ZipCodeController(mockZipCodeService);
        mockMvc = MockMvcBuilders.standaloneSetup(zipCodeController).build();
    }

}
