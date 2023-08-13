package com.haktanozgur.CarPoolProject.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haktanozgur.CarPoolProject.Entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional <User> findByUsername(String userName);
}
