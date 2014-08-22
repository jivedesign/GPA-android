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
	private float course_mark;
	private int cID;
	private int c2s_ID;
	private Course_ListAdapter adapter;
	private ListView course_listview;
	final Context context = this;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.course_activity); 
	    course_listview = (ListView) findViewById(R.id.view_courseActivity);
	    
	    SemesterDataSource sds = new SemesterDataSource(context);
		sds.open();
		sds.createSemester("Sem1", 0);
		sds.close();
	    
	    
	    setup_adapter();
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
		    
		    	CourseDataSource cds = new CourseDataSource(context);
		    	cds.open();
		    	
		    	// If a field is left empty show error message close dialog, otherwise add to DB
		    	if(!coursename_edit.getText().toString().isEmpty()){
		    		course_name = coursename_edit.getText().toString();
		    		cID = cds.getNewID();
		    		c2s_ID = 0; // TODO pass in semester ID
		    		
		    		cds.createCourse(cID, course_name, c2s_ID);
		    	}else{
		    		showInValidInputMessage();
		    	}
	
		    	//Call to update the list view
		    	setup_adapter();
		    	cds.close();
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
		
		CourseDataSource cds = new CourseDataSource(context);
		course_mark = 10; // CHANGE
	
		
		
		cds.open();
		List<Course> courses1_fromDB = cds.getAllCourses(); //Change to get courses from a semester?
		List<Course> courses_fromDB = cds.getCoursesfromSem(0); //Change to get courses from a semester?
		Log.d("database", "size of sem " + Integer.toString(courses_fromDB.size()) );
		Log.d("database", "size of all " + Integer.toString(courses1_fromDB.size()) );

		
		int i = courses_fromDB.size();
		for (int j = 0; j<i; j++){
			
			courses_fromDB.get(j).setMark(course_mark);

		
		}
		
	
		adapter = new Course_ListAdapter(this, R.layout.course_entity, courses_fromDB);
		ListView activity_taskview = course_listview;
		activity_taskview.setAdapter(adapter);
		cds.close();
	}

	
	public void showInValidInputMessage() {
		Context context = getApplicationContext();
		CharSequence text = "Invalid Input";
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		}

}
