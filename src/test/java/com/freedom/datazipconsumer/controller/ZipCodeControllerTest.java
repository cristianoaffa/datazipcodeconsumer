package com.freedom.datazipconsumer.controller;
import com.freedom.datazipcodeconsumer.domain.DataZipCode;
import com.freedom.datazipcodeconsumer.domain.ZipCodeVO;
import com.freedom.datazipcodeconsumer.exception.ZipCodeInvalidException;
import com.freedom.datazipconsumer.builder.DataZipCodeBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ZipCodeControllerTest extends ZipCodeControllerSetup {

    @Test
    public void shouldReturnOneDataZipCodeWithSuccess() throws Exception {
    	
        String zipCode = "60011120";
        ZipCodeVO zipCodeVO = ZipCodeVO.builder().zipCode(zipCode).build();
        List<DataZipCode> listDataZipCodeTest = DataZipCodeBuilder.dataZipCodeListValidOneElementControllerTest(zipCode);
        
        when(mockZipCodeService.getDataZipCode(zipCode)).thenReturn(listDataZipCodeTest.get(0));

        MvcResult mvcResult = mockMvc.perform(post("/api/zipcode/data")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(zipCodeVO)))
                		.andReturn();

        assertThat(mvcResult.getResponse().getStatus(), is(OK.value()));
        assertThat(mvcResult.getResponse().getContentAsString(), hasJsonPath("$.street", equalTo("Rua Bela Cruz")));
        assertThat(mvcResult.getResponse().getContentAsString(), hasJsonPath("$.neighborhood", equalTo("Farias Brito")));
        assertThat(mvcResult.getResponse().getContentAsString(), hasJsonPath("$.city", equalTo("Fortaleza")));
        assertThat(mvcResult.getResponse().getContentAsString(), hasJsonPath("$.state", equalTo("CE")));
    }
    
    //@Test
    public void shouldReturnMessageZipCodeInvalidError() throws Exception {
    	
        String zipCode = "1234";
        ZipCodeVO zipCodeVO = ZipCodeVO.builder().zipCode(zipCode).build();
        
        when(mockZipCodeService.getDataZipCode(zipCode)).thenThrow(new ZipCodeInvalidException("Não foi possível carregar os dados do CEP: 1234"));

        MvcResult mvcResult = mockMvc.perform(post("/api/zipcode/data")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(zipCodeVO)))
                		.andReturn();

        assertThat(mvcResult.getResponse().getStatus(), is(NOT_FOUND.value()));
    }
    
  
}