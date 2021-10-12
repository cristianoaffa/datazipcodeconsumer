
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.freedom.datazipcodeconsumer.domain.ZipCodeVO;
import com.freedom.datazipcodeconsumer.service.ZipCodeService;

import io.swagger.annotations.ApiOperation;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          @RestController
@RestController
@RequestMapping("/api/zipCode")
public class ZipCodeController {
	
	@Autowired
	private ZipCodeService zipCodeService;
	
	@ApiOperation(value = "Return the data of a valid ZipCode")
	@PostMapping(value="/data")
	public ResponseEntity<DataZipCode> returnDataZipCode(@ResponseBody ZipCodeVO zipCodeVO) throws DataZipCodeConsumerException {
		
		try {
			
			DataZipCode dataZipCode = zipCodeService.getDataZipCode(zipCodeVO.getZipCode());
			
			return new ResponseEntity<dataZipCode>(dataZipCode, HttpStatus.OK);					
			
		} catch (Exception ex) {
			return new ResponseEntity<>("CEP inválido", HttpStatus.BAD_REQUEST);			
		}
			
	}
	
	
	
	
	                                                                                                                                                                        
}