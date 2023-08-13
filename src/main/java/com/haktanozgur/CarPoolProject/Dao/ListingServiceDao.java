package com.haktanozgur.CarPoolProject.Dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.haktanozgur.CarPoolProject.Dto.CarDto;
import com.haktanozgur.CarPoolProject.Dto.FiloDto;
import com.haktanozgur.CarPoolProject.Helper.DtoSetterHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
@Repository
public class ListingServiceDao {
	
	
	@PersistenceContext
	EntityManager em;
	
	
	//Bu metod kullanıcının bağlı olduğu şirkete ait tüm filo bölgelerini listeler.
	@SuppressWarnings("unchecked")
	public List<FiloDto> filoList(Long companyId) {
		Query q = null;
		String sql = "select " + "	id, " + "	group_name, " +  " parent_id, " + "company_id " + "from "
				+ "	filo_group "  + " where company_id = :p1 " +  "order by id";

		q = em.createNativeQuery(sql);
		q.setParameter("p1", companyId);
		List<Object[]> results = q.getResultList();
		List<FiloDto> filoDtoList = new ArrayList<FiloDto>();
		for (Object[] a : results) {
			FiloDto filoDto = new FiloDto();
			filoDto.setId(DtoSetterHelper.setLong(a[0]));
			filoDto.setName(DtoSetterHelper.setString(a[1]));
			filoDto.setParent_id(DtoSetterHelper.setLong(a[2]));
			filoDto.setCompany_id(DtoSetterHelper.setLong(a[3]));

			filoDtoList.add(filoDto);
		}
		return filoDtoList;
	}
	
	//Bu metod kullanıcıya yetki atanırken girilen parametreye göre filo bölgelerinin id listesini döner.
	// Girilen parametre değeri bölge id'si ve o bölgeye bağlı tüm alt bölgeleri belirler.
	@SuppressWarnings("unchecked")
	public List<String> filoGroupTreeList(Long filoId) {
		Query q = null;
		String sql = " with recursive FiloGroupTree as ("
				+ "  select id , group_name "
				+ "  from filo_group"
				+ "  where id = :p1"
				+ "  union all"
				+ "  select f.id , f.group_name "
				+ "  from filo_group f"
				+ "  inner join FiloGroupTree fh ON f.parent_id = fh.id )"
				+ "select * "
				+ "from FiloGroupTree;";

		q = em.createNativeQuery(sql);
		q.setParameter("p1", filoId);
		List<Object[]> results = q.getResultList();
		List<String> filoAuthoryDtoList = new ArrayList<String>();
		for (Object[] a : results) {
			
			filoAuthoryDtoList.add(DtoSetterHelper.setString(a[0]));
		}
		return filoAuthoryDtoList;
	}
	
	//Kullanıcının bağlı olduğu şirkete ait tüm araba kayıtlarını listeler.
	@SuppressWarnings("unchecked")
	public List<CarDto> carList(Long companyId) {
		Query q = null;
		String sql = "select c.model, c.brand, c.chassis_number, c.model_year, c.plate_number from cars c where company_id = :p1 order by id ";

		q = em.createNativeQuery(sql);
		q.setParameter("p1", companyId);
		List<Object[]> results = q.getResultList();
		List<CarDto> carDtoList = new ArrayList<CarDto>();
		for (Object[] a : results) {
			CarDto carDto = new CarDto();
			carDto.setModel(DtoSetterHelper.setString(a[0]));
			carDto.setBrand(DtoSetterHelper.setString(a[1]));
			carDto.setChassisNumber(DtoSetterHelper.setString(a[2]));
			carDto.setModelYear(DtoSetterHelper.setInteger(a[3]));
			carDto.setPlateNumber(DtoSetterHelper.setString(a[4]));

			carDtoList.add(carDto);
		}
		return carDtoList;
	}
	
	//Kullanıcının hangi bölgelere yetkisi olup olmadığı kontrol edilir girilen parametre id'si bölge id'sidir ve bu bölge kullanıcının yetki alanında ise true değeri döner.
	@SuppressWarnings("unchecked")
	public boolean authoryCheck (Long filoGroupId, Long userId) {
		Query q = null;
		String sql = "select filo_group_id from filo_authory where CAST(filo_group_id as TEXT) like '%' || CAST( :p1 AS TEXT) || '%'" + 
		" and user_id = :p2";

		q = em.createNativeQuery(sql);
		q.setParameter("p1", filoGroupId);
		q.setParameter("p2", userId);
	    List<String> results = q.getResultList();
	    return !results.isEmpty();
	}

}
