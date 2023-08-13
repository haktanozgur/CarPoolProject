package com.haktanozgur.CarPoolProject.Service;

import com.haktanozgur.CarPoolProject.Models.LoginRequestModel;
import com.haktanozgur.CarPoolProject.Models.LoginResponseModel;

public interface AuthService {

	LoginResponseModel login(LoginRequestModel model);
}
