package com.haktanozgur.CarPoolProject.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {

	private String plateNumber;
	private String chassisNumber;
	private String model;
	private String ticket;
	private String brand;
	private Integer modelYear;
	private Long filoGroupId;
}
