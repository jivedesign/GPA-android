package com.calc.gpacalculator;

import java.util.ArrayList;

public class Course {

	private String name;
	private float mark_value;
	private static int ID;
	
	
	public Course(String name, float mark_value){
		this.name = name;
		this.mark_value = mark_value;
		
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public float getMark() {
		return mark_value;
	}


	public void setMark(float mark_value) {
		this.mark_value = mark_value;
	}

}
