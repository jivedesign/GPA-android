package com.calc.gpacalculator;

import java.util.ArrayList;

public class User {

	private String user_name;
	private ArrayList <Course> semester;
	private int gpa;
	
	public User(String user_name, ArrayList semester, int gpa){
		this.user_name = user_name;
		this.semester = semester;
		this.gpa = gpa;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public ArrayList<Course> getSemester() {
		return semester;
	}

	public void setSemester(ArrayList<Course> semester) {
		this.semester = semester;
	}

	public int getGpa() {
		return gpa;
	}

	public void setGpa(int gpa) {
		this.gpa = gpa;
	}
	
	
}



