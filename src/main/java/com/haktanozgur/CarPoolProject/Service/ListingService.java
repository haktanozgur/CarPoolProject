package com.haktanozgur.CarPoolProject.Service;

import com.haktanozgur.CarPoolProject.Models.ServiceResponseModel;

public interface ListingService {

	ServiceResponseModel filoList(Long companyId);
	ServiceResponseModel carList(Long companyId);

}
