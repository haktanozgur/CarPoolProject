package com.haktanozgur.CarPoolProject.Service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.haktanozgur.CarPoolProject.Dao.ListingServiceDao;
import com.haktanozgur.CarPoolProject.Dto.FiloAuthoryDto;
import com.haktanozgur.CarPoolProject.Entity.FiloAuthory;
import com.haktanozgur.CarPoolProject.Entity.User;
import com.haktanozgur.CarPoolProject.Enum.Roles;
import com.haktanozgur.CarPoolProject.Models.AddUserRequestModel;
import com.haktanozgur.CarPoolProject.Models.ServiceResponseModel;
import com.haktanozgur.CarPoolProject.Repository.FiloAuthoryRepository;
import com.haktanozgur.CarPoolProject.Repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserManagementServiceImpl implements UserManagementService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;	
	private final ListingServiceDao listingServiceDao;
	private final FiloAuthoryRepository filoAuthoryRepository;

	
	//Kullanıcı adminrolünde ise kendi bulunduğu şirket bünyesine standart rolünde bir kullanıcı ekler. request modeline göre kullanıcı bilgileri tabloya yazdırılarak yeni hesap oluşturulur.
	@Override
	public ServiceResponseModel addUser(AddUserRequestModel model, User userAdmin) {
		
		   try {
			   
			  if (model.getUsername()
					   .equals(userRepository.findByUsername(model.getUsername())
							   .get()
							   .getUsername())) {
				  return ServiceResponseModel.fail().message("Bu Kullanıcı adı daha önceden alınmıştır");
		      }
		      else {	   
		        	 
			      return ServiceResponseModel.fail().message("Hata");
		      }  
		   }
		   
		   catch( NoSuchElementException e) {
			   
			   if(model.getPassword().length() < 6) {   
				   return ServiceResponseModel.fail().message("Belirlediğiniz şifre 6 karakterden az olmamalıdır");
			   }
			  
			   if (userAdmin.getRole().equals(Roles.ADMIN)) {
		          User user = User.builder().name(model.getName())
				       .username(model.getUsername())
				       .identifier(model.getIdentifier())
				       .surname(model.getSurname())
				       .companyId(userAdmin.getCompanyId())
				       .password(passwordEncoder.encode(model.getPassword())).role(Roles.STANDART).build();
		
		          userRepository.save(user);
		          
		          return ServiceResponseModel.success().message("Kullanıcı sisteme eklenmiştir.");
			   }
			   else if (userAdmin.getRole().equals(Roles.STANDART)) {	   
			          return ServiceResponseModel.fail().message("Kullanıcı eklemeye yetkiniz bulunmamaktadır.");
			   }
			   else {
			          return ServiceResponseModel.fail().message("Bir Hata oluştu.");

			   }
	       }
	}

	//Kullanıcı admin rolündeyse kendi şirket bünyesinde bulunan standart bir kullanıcıya hangi bölgelerde yetkisi olacağını bu metod sayesinde belirler ve yetkiyi atar.
	//listingServiceDao sınıfında bulunan filoGroupTreeList metodu kullanıcıya atanacak olan bölgelerin id listesini döner ve bu liste filo_authory tablosuna kaydedilir.
	@Override
	public ServiceResponseModel addUserAuthory(User user, FiloAuthoryDto filoGroup) {
		User user2 = new User();
		user2.setId(filoGroup.getUserId());
	    List <String> list = listingServiceDao.filoGroupTreeList(filoGroup.getFiloGroupId()); 
	    String combinedString = String.join(", ", list);
	    FiloAuthory filoAuthory = FiloAuthory.builder()
	    		.companyId(user.getCompanyId())
	    		.filoGroupId(combinedString)
	    		.userId(user2)
	    		.build();
	    
	    filoAuthory.getUserId().setId(filoGroup.getUserId());
	    
	    filoAuthoryRepository.save(filoAuthory);  
	    
		return ServiceResponseModel.success().message("Kaydınız başarıyla alınmıştır");
	}
}
