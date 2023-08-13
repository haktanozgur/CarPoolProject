package com.haktanozgur.CarPoolProject.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haktanozgur.CarPoolProject.Dao.ListingServiceDao;
import com.haktanozgur.CarPoolProject.Dto.CarDto;
import com.haktanozgur.CarPoolProject.Dto.FiloDto;
import com.haktanozgur.CarPoolProject.Models.ServiceResponseModel;

@Service
public class ListingServiceImpl implements ListingService{

	
	@Autowired
	ListingServiceDao listingServiceDao;

	//Kullanıcı bilgilerinden kullanıcının bağlı olduğu şirket kontrol edilir ve şirketin sahip olduğu bölgeler listelenir.
	@Override
	public ServiceResponseModel filoList(Long companyId) {
		
		List<FiloDto> result = listingServiceDao.filoList(companyId);
		return ServiceResponseModel.success().result(result).message("Filo Grup Listesi : ");
	}

	//Kullanıcının bilgilerinden kullanıcının bağlı olduğu şirket kontrol edilir ve şirketin tüm bölgelere eklemiş olduğu arabaların listesi döner.
	@Override
	public ServiceResponseModel carList(Long companyId) {
		
		List<CarDto> result = listingServiceDao.carList(companyId);
		return ServiceResponseModel.success().result(result).message("Şirket Araba Listesi : ");
	}
}
