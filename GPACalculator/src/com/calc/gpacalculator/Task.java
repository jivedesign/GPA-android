package com.calc.gpacalculator;

public class Task {

	private String task_name;
	private float task_average;
	private float total_marks;
	private String semester;
	private String class_name;
	private static int ID;
	
	public String getClass_name() {
		return class_name;
	}


	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}


	public Task(String name_of_task, int class_average, int class_total, String sem, String classname, int id){
		
		this.task_name = name_of_task;
		this.task_average = class_average; 
		this.total_marks = class_total;
		this.semester = sem;
		this.class_name = classname;
		this.ID = id;
	}


	public static int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public String getSemester() {
		return semester;
	}


	public void setSemester(String semester) {
		this.semester = semester;
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
