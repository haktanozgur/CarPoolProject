package com.haktanozgur.CarPoolProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.haktanozgur.CarPoolProject.Config.CoreSecurityContext;
import com.haktanozgur.CarPoolProject.Models.ServiceResponseModel;
import com.haktanozgur.CarPoolProject.Service.ListingService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("list")
@RequiredArgsConstructor
public class ListingServiceController {
	
	@Autowired
	ListingService listingService;
	
	@GetMapping("/filoGroupList")
	public ResponseEntity<ServiceResponseModel> filoGroupList() {
		Long companyId = CoreSecurityContext.getUserDetails().getCompanyId().getId();
		ServiceResponseModel response = listingService.filoList(companyId);
		
		if(response != null) {
			
			return ResponseEntity.ok(response);
		}
		else {
			return ResponseEntity.ok().body(ServiceResponseModel.fail());
		}		
	}
	
	@GetMapping("/carList")
	public ResponseEntity<ServiceResponseModel> carList() {
		Long companyId = CoreSecurityContext.getUserDetails().getCompanyId().getId();
		ServiceResponseModel response = listingService.carList(companyId);
		
		if(response != null) {
			
			return ResponseEntity.ok(response);
		}
		else {
			return ResponseEntity.ok().body(ServiceResponseModel.fail());
		}		
	}

}
