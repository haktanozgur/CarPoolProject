package com.haktanozgur.CarPoolProject.Helper;

public class DtoSetterHelper {
	
	public static String setString(Object str) {
		if (str != null) {
			return str.toString();
		} else {
			return null;
		}
	}
	public static Long setLong(Object str) {
		if (str != null) {
			return Long.valueOf(str.toString());
		} else {
			return null;
		}
	}
	public static Integer setInteger(Object str) {
		if (str != null) {
			return Integer.valueOf(str.toString());
		} else {
			return null;
		}
	}
}
