package com.haktanozgur.CarPoolProject.Models;

import com.haktanozgur.CarPoolProject.Enum.Roles;

import lombok.Data;

@Data
public class AddUserRequestModel {

	private String username;
	private String password;
	private String identifier;
	private String name;
	private String surname;
	private Roles role;
}
