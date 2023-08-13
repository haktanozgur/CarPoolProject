package com.haktanozgur.CarPoolProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.haktanozgur.CarPoolProject.Models.LoginRequestModel;
import com.haktanozgur.CarPoolProject.Models.LoginResponseModel;
import com.haktanozgur.CarPoolProject.Service.AuthService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthServiceController {

	
	@Autowired
	AuthService authService;
	
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseModel> login(@RequestBody LoginRequestModel request) {
		
		LoginResponseModel result = authService.login(request);
		Object a = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println( a.toString());
		if(request != null) {
			
			return ResponseEntity.ok(result);
		}
		else {
			return ResponseEntity.ok().body(LoginResponseModel.fail());
		}
	}
	

	
}
