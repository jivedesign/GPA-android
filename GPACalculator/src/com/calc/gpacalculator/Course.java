package com.calc.gpacalculator;

import java.util.ArrayList;

public class Course {

	private String name;
	private float mark_value;
	private static int ID;
	private int sem2course;
	
	
	


	public Course(String name, float mark_value, int cID, int s2c){
		this.name = name;
		this.mark_value = mark_value;
		this.ID = cID;
		this.sem2course = s2c;
		
	}

	public int getSem2course() {
		return sem2course;
	}

	public void setSem2course(int sem2course) {
		this.sem2course = sem2course;
	}

	public static int getID() {
		return ID;
	}

	public static void setID(int iD) {
		ID = iD;
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
