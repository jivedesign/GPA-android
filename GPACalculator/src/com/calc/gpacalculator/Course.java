package com.calc.gpacalculator;

import java.util.ArrayList;

public class Course {

	private ArrayList <Task> tasks;
	
	
	public Course(){
		
		this.tasks = new ArrayList<Task> ();
		
		
		
	}


	public ArrayList<Task> getTasks() {
		return tasks;
	}


	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}
	
	
}
