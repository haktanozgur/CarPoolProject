package com.haktanozgur.CarPoolProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.haktanozgur.CarPoolProject.Config.CoreSecurityContext;
import com.haktanozgur.CarPoolProject.Dto.CarDto;
import com.haktanozgur.CarPoolProject.Entity.User;
import com.haktanozgur.CarPoolProject.Models.ServiceResponseModel;
import com.haktanozgur.CarPoolProject.Service.FiloManagementService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("filo")
@RequiredArgsConstructor
public class FiloManagementServiceController {

	@Autowired
	FiloManagementService filoManagementService;
	
	
	@PostMapping("/addCar")
	public ResponseEntity<ServiceResponseModel> addUser(@RequestBody CarDto request) {
		User user = CoreSecurityContext.getUserDetails();
		ServiceResponseModel response = filoManagementService.addCar(request,user);
		
		if(request != null) {
			
			return ResponseEntity.ok(response);
		}
		else {
			return ResponseEntity.ok().body(ServiceResponseModel.fail());
		}		
	}
}
