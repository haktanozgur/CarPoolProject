package com.haktanozgur.CarPoolProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haktanozgur.CarPoolProject.Entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
