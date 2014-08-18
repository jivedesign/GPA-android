package com.calc.gpacalculator;

public class Semester {

	private String name;
	private float mark_value;
	private static int ID;
	
	public Semester(String name, float mark_value, int sID) {
		this.name = name;
		this.mark_value = mark_value;
		this.ID = sID;
		
	}

	public String getName() {
		return name;
	}

	public static int getID() {
		return ID;
	}

	public static void setID(int iD) {
		ID = iD;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getMark_value() {
		return mark_value;
	}

	public void setMark_value(float mark_value) {
		this.mark_value = mark_value;
	}
	
	
	
}
