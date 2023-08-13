package com.haktanozgur.CarPoolProject.Service;

import com.haktanozgur.CarPoolProject.Dto.FiloAuthoryDto;
import com.haktanozgur.CarPoolProject.Entity.User;
import com.haktanozgur.CarPoolProject.Models.AddUserRequestModel;
import com.haktanozgur.CarPoolProject.Models.ServiceResponseModel;

public interface UserManagementService {

	ServiceResponseModel addUser(AddUserRequestModel model, User userAdmin);
	ServiceResponseModel addUserAuthory(User user, FiloAuthoryDto filoGroup);
}
