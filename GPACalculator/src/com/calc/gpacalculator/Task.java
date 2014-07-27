package com.calc.gpacalculator;

public class Task {

	private String task_name;
	private float task_average;
	private float total_marks;
	
	
	public Task(String class_name, int class_average, int class_total){
		
		this.task_name = class_name;
		this.task_average = class_average; 
		this.total_marks = class_total;
		
	}


	public String getName() {
		return task_name;
	}


	public void setName(String name) {
		this.task_name = name;
	}


	public float getAverage() {
		return task_average;
	}


	public void setAverage(float average) {
		this.task_average = average;
	}


	public float getTotal_marks() {
		return total_marks;
	}


	public void setTotal_marks(float f) {
		this.total_marks = f;
	}
	
	
	
	
}
