package com.freedom.datazipconsumer.builder;

import java.util.List;

import com.freedom.datazipcodeconsumer.domain.DataZipCode;

public class DataZipCodeBuilder {
	
	public static List<DataZipCode> dataZipCodeListValidOneElementTest1(String zipCode) {
        return List.of(
                DataZipCode.builder()
                		.id("6165009bf8ea4fac08afa9e0")
                		.zipCode(zipCode)
                        .street("Rua Santos Pereira")                                    
                        .neighborhood("Centro")
                        .city("Itajubá")
                        .state("MG")                        
                        .build()
        );
	}
	
	public static List<DataZipCode> dataZipCodeListValidOneElementTest2(String zipCode) {
        return List.of(
                DataZipCode.builder()
                		.id("6165009bf8ea4fac08afaa0b")
                		.zipCode(zipCode)
                        .street("Avenida Presidente Vargas - até 379/380")                                    
                        .neighborhood("Campina")
                        .city("Belém")
                        .state("PA")                        
                        .build()
        );
	}
	
	public static List<DataZipCode> dataZipCodeListValidOneElementTest3(String zipCode) {
        return List.of(
                DataZipCode.builder()
                		.id("6165009bf8ea4fac08afa9f0")
                		.zipCode(zipCode)
                        .street("Avenida Rui Barbosa - até 614/615")                                    
                        .neighborhood("Jardim Bela Vista")
                        .city("São José dos Campos")
                        .state("SP")                        
                        .build()
        );
	}
	
    public static List<DataZipCode> dataZipCodeListValidElementEmptyTest(String zipCode) {
        return List.of(
                DataZipCode.builder()
		                .id("6165009bf8ea4fac08afaa0e")
		        		.zipCode(zipCode)
		                .street("")                                    
		                .neighborhood("")
		                .city("")
		                .state("") 
                        .build()
        );	    
	}

}
