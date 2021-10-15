package com.freedom.datazipconsumer.controller;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;

import com.freedom.datazipcodeconsumer.domain.DataZipCode;
import com.freedom.datazipcodeconsumer.domain.ZipCodeVO;
import com.freedom.datazipcodeconsumer.exception.ZipCodeInvalidException;
import com.freedom.datazipconsumer.builder.DataZipCodeBuilder;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ZipCodeControllerTest extends ZipCodeControllerSetup {

    @Test
    public void shouldReturnOneDataZipCodeWithSuccess() throws Exception {
    	
        String zipCode = "37505026";
        ZipCodeVO zipCodeVO = ZipCodeVO.builder().zipCode(zipCode).build();
        List<DataZipCode> listDataZipCodeTest = DataZipCodeBuilder.dataZipCodeListValidOneElementControllerTest(zipCode);
        
        when(mockZipCodeService.getDataZipCode(zipCode)).thenReturn(listDataZipCodeTest.get(0));

        MvcResult mvcResult = mockMvc.perform(post("/api/zipcode/data")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)                        
                        .content(objectMapper.writeValueAsString(zipCodeVO)))
                		.andReturn();
        
        mvcResult.getResponse().setCharacterEncoding("UTF-8");        

        assertThat(mvcResult.getResponse().getStatus(), is(OK.value()));
        assertThat(mvcResult.getResponse().getContentAsString(), hasJsonPath("$.street", equalTo("Vila Coronel Carneiro Júnior")));
        assertThat(mvcResult.getResponse().getContentAsString(), hasJsonPath("$.neighborhood", equalTo("Boa Vista")));
        assertThat(mvcResult.getResponse().getContentAsString(), hasJsonPath("$.city", equalTo("Itajubá")));
        assertThat(mvcResult.getResponse().getContentAsString(), hasJsonPath("$.state", equalTo("MG")));
    }
    
    @Test
    public void shouldReturnMessageZipCodeInvalidError() throws Exception {
    	
        String zipCode = "1234";
        ZipCodeVO zipCodeVO = ZipCodeVO.builder().zipCode(zipCode).build();
        
        when(mockZipCodeService.getDataZipCode(zipCode)).thenThrow(new ZipCodeInvalidException("Não foi possível carregar os dados do CEP: 1234")
        												.initCause(new ZipCodeInvalidException("CEP inválido")));

        MvcResult mvcResult = mockMvc.perform(post("/api/zipcode/data")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)                        
                        .content(objectMapper.writeValueAsString(zipCodeVO)))
                		.andReturn();
        
        mvcResult.getResponse().setCharacterEncoding("UTF-8");

        assertThat(mvcResult.getResponse().getStatus(), is(NOT_FOUND.value()));
        assertThat(mvcResult.getResponse().getContentAsString(), containsString("CEP inválido"));
    }
    
    @Test
    public void shouldReturnMessageZipCodeInvalidErrorZipCodeLength() throws Exception {
    	
        String zipCode = "12345678";
        ZipCodeVO zipCodeVO = ZipCodeVO.builder().zipCode(zipCode).build();
        
        when(mockZipCodeService.getDataZipCode(zipCode)).thenThrow(new ZipCodeInvalidException("Não foi possível carregar os dados do CEP: 12345678")
        												.initCause(new ZipCodeInvalidException("CEP inválido")));

        MvcResult mvcResult = mockMvc.perform(post("/api/zipcode/data")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(zipCodeVO)))
                		.andReturn();
        
        mvcResult.getResponse().setCharacterEncoding("UTF-8");

        assertThat(mvcResult.getResponse().getStatus(), is(NOT_FOUND.value()));
        assertThat(mvcResult.getResponse().getContentAsString(), containsString("CEP inválido"));
    }
    
}