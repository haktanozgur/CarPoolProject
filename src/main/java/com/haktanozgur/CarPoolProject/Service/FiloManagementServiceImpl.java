package com.haktanozgur.CarPoolProject.Service;

import org.springframework.stereotype.Service;

import com.haktanozgur.CarPoolProject.Dao.ListingServiceDao;
import com.haktanozgur.CarPoolProject.Dto.CarDto;
import com.haktanozgur.CarPoolProject.Entity.Car;
import com.haktanozgur.CarPoolProject.Entity.FiloGroup;
import com.haktanozgur.CarPoolProject.Entity.User;
import com.haktanozgur.CarPoolProject.Enum.Roles;
import com.haktanozgur.CarPoolProject.Models.ServiceResponseModel;
import com.haktanozgur.CarPoolProject.Repository.CarRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FiloManagementServiceImpl implements FiloManagementService {

	private final CarRepository carRepository; 
	private final ListingServiceDao listingServiceDao;
	
	//Kullanıcı admin rolündeyse sisteme ekleyeceği araba, yetki kontrolü olmadan request içerisinde yer alan bilgilere göre şirket ve kullanıcı id'si ile birlikte cars tablosuna kaydedilir kaydedilir.
	//Kullanıcı rolü standart ise listingServiceDao sınıfından authoryCheck metodu çağırılır ve kullanıcının aracı eklemek istediği bölgeye yetkisi kontrol edilir. Yetkisi var ise araç tabloya kaydedilir ve başarılı response döner.
	@Override
	public ServiceResponseModel addCar(CarDto model, User user) {
		
		 FiloGroup filo = new FiloGroup();
		 filo.setId(model.getFiloGroupId());
		 
		 if (user.getRole().equals(Roles.ADMIN)) {
			Car car = Car.builder().model(model.getModel())
					.brand(model.getBrand())
					.plateNumber(model.getPlateNumber())
					.chassisNumber(model.getChassisNumber())
					.ticket(model.getTicket())
					.filogroup(filo)
					.modelYear(model.getModelYear()).build();
			car.setUser(user);
			car.setCompany(user.getCompanyId());
			carRepository.save(car);
			
		    return ServiceResponseModel.success().message("Araba başarılı bir şekilde kaydedilmiştir.");
		}
		else if (user.getRole().equals(Roles.STANDART)) {
			
			boolean check = listingServiceDao.authoryCheck(model.getFiloGroupId(), user.getId());
			
			if (check == true) {
				Car car = Car.builder().model(model.getModel())
						.brand(model.getBrand())
						.plateNumber(model.getPlateNumber())
						.chassisNumber(model.getChassisNumber())
						.ticket(model.getTicket())
						.filogroup(filo)
						.modelYear(model.getModelYear()).build();
				car.setUser(user);
				car.setCompany(user.getCompanyId());
				carRepository.save(car);
				
				return ServiceResponseModel.success().message("Araba başarılı bir şekilde kaydedilmiştir.");
			}
			else {
				return ServiceResponseModel.fail().message("Bu bölgeye araba eklemek için yetkiniz bulunmamaktadır");
			}
		}
		
		else {
			return ServiceResponseModel.fail().message("Bir hata oluştu");
		}
		
	}

}
