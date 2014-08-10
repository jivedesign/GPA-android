package com.calc.gpacalculator;

import java.util.ArrayList;
import java.util.List;

import view.MainActivity;
import controller.Gpa_ListAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import controller.*;

public class TaskActivity extends MainActivity {

	private Button add_task;
	private ListView task_list;
	private Gpa_ListAdapter adapter;
	
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.task_activity);
	    
		add_task = (Button) findViewById(R.id.add_taskButton);
		task_list = (ListView) findViewById(R.id.view_tasklist);
		
		setup_adapter();
		setup_button();
		
	}

	private void setup_adapter(){
		
		List<Task> tasks = tds.getAllTasks();
		
		
		adapter = new Gpa_ListAdapter(this, R.layout.task_entity, tasks);
		ListView activity_taskview = task_list;
		activity_taskview.setAdapter(adapter);
	}
	
	private void setup_button(){
	add_task.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				adapter.insert(new Task("", 0, 0, "", "", (int) Math.random()), 0);
				
			}
			
		});
	}
	
	@Override
	public void onPause() {
	    super.onPause();  // Always call the superclass method first
	    
	}
	
	@Override
	public void onResume() {
	    super.onResume();  // Always call the superclass method first
	   
	   
	}
}
