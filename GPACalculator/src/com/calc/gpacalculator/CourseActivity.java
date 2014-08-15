package com.calc.gpacalculator;

import java.util.ArrayList;
import java.util.List;

import controller.Course_ListAdapter;
import controller.Gpa_ListAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class CourseActivity extends ActionBarActivity {

	private String course_name;
	private Course_ListAdapter adapter;
	private ListView course_listview;
	final Context context = this;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.course_activity); 
	}
	
	private void setup_add() {

		LayoutInflater li = LayoutInflater.from(context);
		
		// Get XML file to view
		View promptsView = li.inflate(R.layout.add_course_dialog, null);
		
		final EditText coursename_edit = (EditText) promptsView.findViewById(R.id.coursename_dialog);
		
		//Create a new AlertDialog
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		
		//Link the alertdialog to the XML 
		alertDialogBuilder.setView(promptsView);
		
		//Create the buttons
		alertDialogBuilder
		.setCancelable(false)
		.setPositiveButton("Save",
		  new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog,int id) {
		    
		    	// If a field is left empty show error message close dialog, otherwise add to DB
		    	if(!coursename_edit.getText().toString().isEmpty()){
		    		course_name = coursename_edit.getText().toString();
		  
		    	}else{
		    		showInValidInputMessage();
		    	}
	
		    	//Call to update the list view
		    	setup_adapter();
		    }
		  })
		.setNegativeButton("Cancel",
		  new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog,int id) {
		    	// Do nothing
		    	dialog.cancel();
		    }
		  });
		
		AlertDialog alertDialog = alertDialogBuilder.create();
		
		alertDialog.show();
	}

	
	
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.course_menu, menu);

        return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()){
		
		 case R.id.add_course_menu:
			 setup_add();
			 
		      break;	   
		 default:
	      break;
	    }
		return true;
	}
	
	
private void setup_adapter(){
		
		TaskDataSource tds1 = new TaskDataSource(getApplicationContext());
		
		Course course_obj;
		String course_obj_name;
		float course_obj_mark;
		
		tds1.open();
		List<Task> tasks_fromDB = tds1.getAllTasks();
		
		List<Course> course_list = new ArrayList();
		int i = tasks_fromDB.size();
		for (int j = 0; j<i; j++){
			
			course_obj_name = tasks_fromDB.get(j).getClass_name();
			course_obj_mark = 0; //Change later when calculated
			
			course_obj = new Course(course_obj_name, course_obj_mark);
			course_list.add(course_obj);
			//ls1.add(tasks1.get(j).getID());
			Log.d("database", tasks_fromDB.get(j).getName());
		}
		
		
		adapter = new Course_ListAdapter(this, R.layout.course_entity, course_list);
		ListView activity_taskview = course_listview;
		activity_taskview.setAdapter(adapter);
		tds1.close();
	}

	
	public void showInValidInputMessage() {
		Context context = getApplicationContext();
		CharSequence text = "Invalid Input";
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		}

}