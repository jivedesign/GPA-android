package com.calc.gpacalculator;

public class Semester {

	private String name;
	private float mark_value;
	private static int sID;
	
	public Semester(String name, int sID) {
		this.name = name;
		this.sID = sID;
		
	}

	public String getName() {
		return name;
	}

	public static int getID() {
		return sID;
	}

	public static void setID(int iD) {
		sID = iD;
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
