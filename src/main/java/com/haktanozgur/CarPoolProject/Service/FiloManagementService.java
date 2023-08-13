package com.haktanozgur.CarPoolProject.Service;

import com.haktanozgur.CarPoolProject.Dto.CarDto;
import com.haktanozgur.CarPoolProject.Entity.User;
import com.haktanozgur.CarPoolProject.Models.ServiceResponseModel;

public interface FiloManagementService {

	ServiceResponseModel addCar(CarDto model ,User user);

}
