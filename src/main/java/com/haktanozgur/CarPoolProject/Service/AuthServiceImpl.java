package com.haktanozgur.CarPoolProject.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.haktanozgur.CarPoolProject.Entity.User;
import com.haktanozgur.CarPoolProject.Models.LoginRequestModel;
import com.haktanozgur.CarPoolProject.Models.LoginResponseModel;
import com.haktanozgur.CarPoolProject.Repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	//Kullanıcı, kullanıcı adı ve şifresi ile istek gönderir. Gönderilen istek doğrultusunda veritabanında kontrol sağlanır ve bilgiler doğru ise kullanıcıya response olarak bir token döner.
	@Override
	public LoginResponseModel login(LoginRequestModel model) {
		
	   try {
		      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(model.getUsername(), model.getPassword()));
			  
		      User user = userRepository.findByUsername(model.getUsername()).orElseThrow(); 
			  
		      String jwtToken = jwtService.generateToken(user);
			  
		      return LoginResponseModel.success().token(jwtToken).message("Giriş Başarıyla Yapılmıştır");
	   }
	   
	   catch(UsernameNotFoundException e) {
		   
		      return LoginResponseModel.wrongInformation();
	   }
	}
}
