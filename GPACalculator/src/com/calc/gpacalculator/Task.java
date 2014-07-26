package com.calc.gpacalculator;

public class Task {

	private String name;
	private int average;
	private int total_marks;
	
	
	public Task(String className, int classAverage, int classTotal){
		
		this.name = className;
		this.average = classAverage;
		this.total_marks = classTotal;
		
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAverage() {
		return average;
	}


	public void setAverage(int average) {
		this.average = average;
	}


	public int getTotal_marks() {
		return total_marks;
	}


	public void setTotal_marks(int total_marks) {
		this.total_marks = total_marks;
	}
	
	
	
	
}
