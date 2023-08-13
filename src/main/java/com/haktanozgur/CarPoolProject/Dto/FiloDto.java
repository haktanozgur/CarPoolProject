package com.haktanozgur.CarPoolProject.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiloDto {

	private Long id;
	private String name;
	private Long parent_id;
	private Long company_id;
}
