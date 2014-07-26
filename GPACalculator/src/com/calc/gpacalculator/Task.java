package com.calc.gpacalculator;

public class Task {

	private String task_name;
	private int task_average;
	private int total_marks;
	
	
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


	public int getAverage() {
		return task_average;
	}


	public void setAverage(int average) {
		this.task_average = average;
	}


	public int getTotal_marks() {
		return total_marks;
	}


	public void setTotal_marks(int total_marks) {
		this.total_marks = total_marks;
	}
	
	
	
	
}
