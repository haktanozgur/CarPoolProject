package com.haktanozgur.CarPoolProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haktanozgur.CarPoolProject.Config.CoreSecurityContext;
import com.haktanozgur.CarPoolProject.Dto.FiloAuthoryDto;
import com.haktanozgur.CarPoolProject.Entity.User;
import com.haktanozgur.CarPoolProject.Models.AddUserRequestModel;
import com.haktanozgur.CarPoolProject.Models.ServiceResponseModel;
import com.haktanozgur.CarPoolProject.Service.UserManagementService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserManagementServiceController {

	
	@Autowired
	UserManagementService userManagementService;
	
	
	@PostMapping("/addUser")
	public ResponseEntity<ServiceResponseModel> addUser(@RequestBody AddUserRequestModel request) {
		User userId = CoreSecurityContext.getUserDetails();
		ServiceResponseModel response = userManagementService.addUser(request , userId);
		
		if(request != null) {
			
			return ResponseEntity.ok(response);
		}
		else {
			return ResponseEntity.ok().body(ServiceResponseModel.fail());
		}		
	}
	
	@SuppressWarnings("unused")
	@PostMapping("/addUserAuthory")
	public ResponseEntity<ServiceResponseModel> addUserAuthory(@RequestBody FiloAuthoryDto request) {
		User userId = CoreSecurityContext.getUserDetails();
		ServiceResponseModel response = userManagementService.addUserAuthory(userId , request);
		
		if(request != null) {
			
			return ResponseEntity.ok(response);
		}
		else {
			return ResponseEntity.ok().body(ServiceResponseModel.fail());
		}		
	}
}
