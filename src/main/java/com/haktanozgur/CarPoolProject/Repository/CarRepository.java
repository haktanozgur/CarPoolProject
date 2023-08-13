package com.haktanozgur.CarPoolProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.haktanozgur.CarPoolProject.Entity.Car;
import com.haktanozgur.CarPoolProject.Entity.FiloGroup;
import com.haktanozgur.CarPoolProject.Entity.User;

public interface CarRepository extends JpaRepository<Car, Long> {

	Car findByUser (User user);
	Car findByFilogroup(FiloGroup filogroup);

}
